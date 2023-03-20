package com.tunepruner.voixt.editor.editorscreen.domain

import androidx.lifecycle.ViewModel
import com.tunepruner.voixt.editor.editorscreen.model.DocumentEvent
import com.tunepruner.voixt.editor.editorscreen.model.DomainString
import kotlinx.coroutines.flow.SharedFlow

class EditorScreenViewModel(val editorController: EditorController) : ViewModel() {
//    private val cursorStateViewModel = CursorViewModel()
//    private val scrollStateViewModel = ScrollStateViewModel()
    private val documentHistoryManager = DocumentHistoryManager()
    val documentEvents: SharedFlow<DocumentEvent> = documentHistoryManager.documentEvents

    suspend fun initialize(words: List<DomainString>) = documentHistoryManager.initialize(words)

    suspend fun add(words: List<DomainString>, index: Int? = null) = documentHistoryManager.add(words, index)

    suspend fun remove(index: Int, words: List<DomainString>) =
        documentHistoryManager.remove(index, words)

    suspend fun undo() = documentHistoryManager.undo()

    suspend fun redo() = documentHistoryManager.redo()

    fun compileListFromEditStack(): List<DomainString> = documentHistoryManager.compileListFromEditStack()

}