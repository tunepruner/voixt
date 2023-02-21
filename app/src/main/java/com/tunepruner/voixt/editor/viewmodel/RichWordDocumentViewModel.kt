package com.tunepruner.voixt.editor.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

const val TAG = "12345"

val DUMMY_TEXT = """
    This is the first list of strings that we will use to populate the UI. 
    I hope it will be long enough to fill the who UI. Let's try to repeat 
    as many ideas as possible so that we achieve that objective, and then, 
    in the future, we will find better text to display. 
""".trimIndent()

/** This is the source of truth for the current text body, both in
 * the full-viewing view state and the editing view state. */
class RichWordDocumentViewModel : ViewModel() {
    private val undoStack = ArrayDeque<WordEvent>()
    private val redoStack = ArrayDeque<WordEvent>()

    /** In order to know the current state of the text body, a client must
     * traverse [editStack] in its entirety. This allows supporting intuitive
     * animations for modifications to the text body through undos, redos,
     * deletions, and more. As such, it is important that undo and redo
     * actions maintain the integrity of this stack. */
    private val editStack = ArrayDeque<WordEvent>()

    /** The [backupTextBodyState] will give us something
     * to fall back on in the case of unforeseen bugs, preventing those
     * bugs from deteriorating the user experience too quickly. Even more
     * importantly, it gives us something to test against to ensure those
     * bugs don't happen. */
    private val _backupTextBodyState = MutableStateFlow(ArrayList<String>())
    val backupTextBodyState: StateFlow<List<String>> = _backupTextBodyState
//    private var _documentEvent: MutableStateFlow<WordEvent> = MutableStateFlow()
//    val documentEvent: StateFlow<WordEvent> = _documentEvent

    private val _documentEvent: MutableSharedFlow<WordEvent> = MutableSharedFlow()
    val documentEvent: SharedFlow<WordEvent> = _documentEvent


    suspend fun initialize(words: List<String>) {
        edit(0, words, WordEventType.INITIALIZE)
    }

    suspend fun add(words: List<String>, index: Int? = null) {
        edit(index ?: _backupTextBodyState.value.size, words, WordEventType.ADD)
    }

    suspend fun remove(index: Int, words: List<String>) {
        edit(index, words, WordEventType.REMOVE)
    }

    private suspend fun edit(index: Int, words: List<String>, type: WordEventType) {
        redoStack.clear()
        val newEvent = WordEvent(
            affectedWords = words,
            wordEventType = type,
            index = index,
        )
        undoStack.add(newEvent)
        editStack.add(newEvent)
        _documentEvent.emit(
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
        _documentEvent.emit(
            WordEvent(
                affectedWords = undoItem.affectedWords,
                wordEventType = when (undoItem.wordEventType) {
                    WordEventType.REMOVE -> WordEventType.ADD
                    else -> WordEventType.REMOVE
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
        _documentEvent.emit(
            WordEvent(
                affectedWords = redoItem.affectedWords,
                wordEventType = redoItem.wordEventType,
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
                if (wordEvent.wordEventType == WordEventType.REMOVE) {
                    acc.remove(word.value)
                } else {
                    acc.add((wordEvent.index ?: 0) + word.index, word.value)
                }
            }
            acc
        }
    }
}


fun List<String>.concatenateToOneString(): String {
    val builder = StringBuilder()
    return fold(builder) { acc, string ->
        acc.append("$string ")
    }.toString()
}

class WordEvent(
    val wordEventType: WordEventType,
    val affectedWords: List<String>,
    val index: Int? = null,
)

enum class WordEventType {
    ADD,
    REMOVE,
    INITIALIZE,
}

data class UndoRedoAction(
    val eventToReverse: WordEvent
)