package com.tunepruner.voixt.editor.editorscreen.domain

import com.tunepruner.voixt.editor.editorscreen.model.DocumentEvent
import com.tunepruner.voixt.editor.editorscreen.model.DocumentEventType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

const val TAG = "12345"

//val DUMMY_TEXT = """
//    This is the first list of strings that we will use to populate the UI.
//    I hope it will be long enough to fill the who UI. Let's try to repeat
//    as many ideas as possible so that we achieve that objective, and then,
//    in the future, we will find better text to display.
//""".trimIndent()

class DocumentHistoryManager() {
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
    private val _backupTextBodyState = MutableStateFlow(ArrayList<String>())
    val backupTextBodyState: StateFlow<List<String>> = _backupTextBodyState

    private val _documentEvents: MutableSharedFlow<DocumentEvent> =
        MutableSharedFlow(replay = Int.MAX_VALUE)
    val documentEvents: SharedFlow<DocumentEvent> = _documentEvents

    suspend fun initialize(words: List<String>) {
        edit(0, words, DocumentEventType.INITIALIZE)
    }

    suspend fun add(words: List<String>, index: Int? = null) {
        edit(index ?: _backupTextBodyState.value.size, words, DocumentEventType.ADD)
    }

    suspend fun remove(index: Int, words: List<String>) {
        edit(index, words, DocumentEventType.REMOVE)
    }

    suspend fun hyphenate(from: Int, words: List<String>) {
        edit(from, words, DocumentEventType.COMPOSITE)
    }

    private suspend fun edit(index: Int, words: List<String>, type: DocumentEventType) {
        redoStack.clear()
        val newEvent = DocumentEvent(
            affectedWords = words,
            documentEventType = type,
            index = index,
        )
        undoStack.add(newEvent)
        editStack.add(newEvent)
        _documentEvents.emit(
            newEvent
        )
        _backupTextBodyState.emit(
            ArrayList(compileListFromEditStack())
        )
    }

    suspend fun undo() {
        val undoItem = undoStack.last()
        undoStack.removeLast()
        redoStack.add(undoItem)
        editStack.remove(undoItem)
        _documentEvents.emit(
            DocumentEvent(
                affectedWords = undoItem.affectedWords,
                documentEventType = when (undoItem.documentEventType) {
                    DocumentEventType.REMOVE -> DocumentEventType.ADD
                    else -> DocumentEventType.REMOVE
                },
                index = undoItem.index,
            )
        )
        _backupTextBodyState.emit(
            ArrayList(compileListFromEditStack())
        )
    }

    suspend fun redo() {
        val redoItem = redoStack.last()
        redoStack.removeLast()
        undoStack.add(redoItem)
        editStack.add(redoItem)
        _documentEvents.emit(
            DocumentEvent(
                affectedWords = redoItem.affectedWords,
                documentEventType = redoItem.documentEventType,
                index = redoItem.index,
            )
        )
        _backupTextBodyState.emit(
            ArrayList(compileListFromEditStack())
        )
    }

    fun compileListFromEditStack(): List<String> {
        return editStack.fold(ArrayList()) { acc, wordEvent ->
            for (word in wordEvent.affectedWords.withIndex()) {
                when (wordEvent.documentEventType) {
                    DocumentEventType.ADD, DocumentEventType.INITIALIZE -> {
                        acc.add((wordEvent.index ?: 0) + word.index, word.value)
                    }
                    DocumentEventType.REMOVE -> {
                        acc.remove(word.value)
                    }
                    DocumentEventType.COMPOSITE -> {
                        TODO()
                    }
                }
            }
            acc
        }
    }
}