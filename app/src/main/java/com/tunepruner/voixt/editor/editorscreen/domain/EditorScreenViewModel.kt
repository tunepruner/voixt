package com.tunepruner.voixt.editor.editorscreen.domain

import androidx.lifecycle.ViewModel
import com.tunepruner.voixt.editor.editorscreen.model.DocumentEvent
import kotlinx.coroutines.flow.*

class EditorScreenViewModel(editorController: EditorController) : ViewModel() {
    private val documentHistoryManager = DocumentHistoryManager(editorController)
    val documentEvents: SharedFlow<DocumentEvent> = documentHistoryManager.documentEvents

    suspend fun initialize(words: List<String>) = documentHistoryManager.initialize(words)

    suspend fun add(words: List<String>, index: Int? = null) = documentHistoryManager.add(words, index)

    suspend fun remove(index: Int, words: List<String>) =
        documentHistoryManager.remove(index, words)

    suspend fun undo() = documentHistoryManager.undo()

    suspend fun redo() = documentHistoryManager.redo()

    fun compileListFromEditStack(): List<String> = documentHistoryManager.compileListFromEditStack()

}