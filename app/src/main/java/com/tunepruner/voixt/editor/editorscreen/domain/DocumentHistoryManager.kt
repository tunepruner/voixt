package com.tunepruner.voixt.editor.editorscreen.domain

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.UnderlineSpan
import com.tunepruner.voixt.editor.editorscreen.model.DocumentEvent
import com.tunepruner.voixt.editor.editorscreen.model.DomainString
import com.tunepruner.voixt.editor.editorscreen.model.TextFilter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

val DUMMY_TEXT = """
    This is the first list of strings that we will use to populate the UI. 
    I hope it will be long enough to fill the who UI. Let's try to repeat 
    as many ideas as possible so that we achieve that objective, and then, 
    in the future, we will find better text to display. 
""".trimIndent()

class DocumentHistoryManager {
    private val undoStack = ArrayDeque<DocumentEvent>()
    private val redoStack = ArrayDeque<DocumentEvent>()

    /** In order to know the current state of the text body, a client must
     * traverse [editStack] in its entirety. This allows supporting intuitive
     * animations for modifications to the text body through undos, redos,
     * deletions, and more. As such, it is important that undo and redo
     * actions maintain the integrity of this stack. */
    private val editStack = ArrayDeque<DocumentEvent>()

    /** The [backupTextBodyState] will give us something
     * to fall back on in the case of unforeseen bugs, preventing those
     * bugs from deteriorating the user experience too quickly. Even more
     * importantly, it gives us something to test against to ensure those
     * bugs don't happen. */
    private val _backupTextBodyState = MutableStateFlow(ArrayList<DomainString>())
    val backupTextBodyState: StateFlow<List<DomainString>> = _backupTextBodyState

    private val _documentEvents: MutableSharedFlow<DocumentEvent> =
        MutableSharedFlow(replay = Int.MAX_VALUE)
    val documentEvents: SharedFlow<DocumentEvent> = _documentEvents

    suspend fun initialize(words: List<DomainString>) {
        sendEditEvent(DocumentEvent.Initialization(words))
    }

    suspend fun add(words: List<DomainString>, index: Int? = null) {
        sendEditEvent(
            DocumentEvent.Insertion(
                stringsToAdd = words,
                insertionIndex = index ?: backupTextBodyState.value.size
            )
        )
    }

    suspend fun remove(index: Int, words: List<DomainString>) {
        sendEditEvent(
            DocumentEvent.Deletion(
                stringsToRemove = words, deletionStartIndex = index
            )
        )
    }

    suspend fun applyFilter(
        filter: TextFilter,
        startingIndex: Int,
        words: List<DomainString>
    ) {
        sendEditEvent(
            DocumentEvent.FilterAddition(
                filterStartIndex = startingIndex,
                stringsToReceiveFilter = words,
                filterToApply = filter
            )
        )
    }

    suspend fun removeFilter(filter: TextFilter, from: Int, words: List<DomainString>) {
        sendEditEvent(
            DocumentEvent.FilterRemoval(
                filterStartIndex = from,
                stringsToRemoveFilterFrom = words,
                filterToRemove = filter
            )
        )
    }
/*
    suspend fun applyParentheses(from: Int, words: List<String>) {
        TODO()
        sendEditEvent(
            DocumentEvent.Composite(

            )
        )
    }

    suspend fun applyQuotationMarks(from: Int, words: List<String>) {
        TODO()
        sendEditEvent(
            DocumentEvent.Composite(

            )
        )
    }

    suspend fun applyAllCaps(from: Int, words: List<String>) {
        TODO()
        sendEditEvent(
            DocumentEvent.Composite(

            )
        )
    }

    suspend fun applySentenceCaps(from: Int, words: List<String>) {
        TODO()
        sendEditEvent(
            DocumentEvent.Composite(

            )
        )
    }

    suspend fun applyHyphenation(from: Int, words: List<String>) {
        TODO()
        sendEditEvent(
            DocumentEvent.Composite(

            )
        )
    }

*/

    private suspend fun sendEditEvent(event: DocumentEvent) {
        redoStack.clear()
        val newEvent = event
        undoStack.add(newEvent)
        editStack.add(newEvent)
        _documentEvents.emit(newEvent)
        _backupTextBodyState.emit(ArrayList(compileListFromEditStack()))
    }

    suspend fun undo() {
        val undoItem = undoStack.last()
        undoStack.removeLast()
        redoStack.add(undoItem)
        editStack.remove(undoItem)

        // We create a reversed version of whatever
        // event the user wants undone.
        // We do not put this reversal in the edit
        // stack, but we DO emit it to the UI as if it
        // were a normal event so that animations can
        // work as expected.
        _documentEvents.emit(undoItem.createReverseEvent())
        _backupTextBodyState.emit(ArrayList(compileListFromEditStack()))
    }

    suspend fun redo() {
        val redoItem = redoStack.last()
        redoStack.removeLast()
        undoStack.add(redoItem)
        editStack.add(redoItem)
        _documentEvents.emit(redoItem)
        _backupTextBodyState.emit(ArrayList(compileListFromEditStack()))
    }

    fun compileListFromEditStack(): List<DomainString> {
        return editStack.fold(ArrayList()) { acc, event ->
            event.strings?.map { it.richString }?.let { eventStrings ->
                val firstIndex = event.startingIndex ?: 0
                val lastIndex = firstIndex + event.strings.lastIndex
                for (eventStringMember in eventStrings.withIndex()) {
                    val editIndex = firstIndex + eventStringMember.index
                    when (event) {
                        is DocumentEvent.Insertion, is DocumentEvent.Initialization -> {
                            acc.add(
                                editIndex,
                                DomainString(eventStringMember.value)
                            )
                        }
                        is DocumentEvent.Deletion -> {
                            acc.removeAt(editIndex)
                        }
                        is DocumentEvent.FilterAddition -> {
                            val builder = acc[editIndex].richString
                            when (event.filterToApply) {
                                is TextFilter.Italics -> builder.setSpan(
                                    Typeface.ITALIC,
                                    firstIndex,
                                    lastIndex,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                                is TextFilter.Bold -> builder.setSpan(
                                    Typeface.BOLD,
                                    firstIndex,
                                    lastIndex,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                                is TextFilter.Underline -> builder.setSpan(
                                    UnderlineSpan(),
                                    firstIndex,
                                    lastIndex,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                                is TextFilter.AllCapitalized ->
                                    builder.forEachIndexed { replaceIndex, char ->
                                        builder.replace(
                                            replaceIndex,
                                            replaceIndex,
                                            { char.uppercase() }.toString()
                                        )
                                    }
                                is TextFilter.SentenceCase -> builder.toString()
                                    .replaceFirstChar { it.uppercase() }
                            }
                        }
                        is DocumentEvent.FilterRemoval -> {
                            TODO()
                        }
                        is DocumentEvent.Composite -> {
                            TODO()
                        }
                    }
                }
            }
            acc
        }
    }
}

fun DocumentEvent.createReverseEvent(): DocumentEvent {
    return when (this) {
        is DocumentEvent.Initialization -> {
            this
        }
        is DocumentEvent.FilterAddition -> {
            DocumentEvent.FilterRemoval(
                filterStartIndex = this.filterStartIndex,
                stringsToRemoveFilterFrom = this.stringsToReceiveFilter,
                filterToRemove = this.filterToApply,
            )
        }
        is DocumentEvent.FilterRemoval -> {
            DocumentEvent.FilterAddition(
                filterStartIndex = this.filterStartIndex,
                stringsToReceiveFilter = this.stringsToRemoveFilterFrom,
                filterToApply = this.filterToRemove,
            )
        }
        is DocumentEvent.Insertion -> {
            DocumentEvent.Deletion(
                deletionStartIndex = this.insertionIndex,
                stringsToRemove = this.stringsToAdd,
            )
        }
        is DocumentEvent.Deletion -> {
            DocumentEvent.Insertion(
                insertionIndex = this.deletionStartIndex,
                stringsToAdd = this.stringsToRemove,
            )
        }
        is DocumentEvent.Composite -> {
            DocumentEvent.Composite(listOfEvents = this.listOfEvents.map { it.createReverseEvent() })
        }
    }
}