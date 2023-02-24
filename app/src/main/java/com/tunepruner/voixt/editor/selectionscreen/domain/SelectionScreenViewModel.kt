package com.tunepruner.voixt.editor.selectionscreen.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunepruner.voixt.editor.selectionscreen.repository.SelectionScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

typealias SelectionState = Pair<Int, Int>

class SelectionScreenViewModel(val repository: SelectionScreenRepository) : ViewModel() {
    private val _selectionState = MutableStateFlow<SelectionState?>(null)
    val selectionState = _selectionState

    private val _textBody = MutableStateFlow<TextBodyState>(TextBodyState.Loading)
    val textBody = _textBody

    fun setNewSelection(start: Int, end: Int) {
        viewModelScope.launch {
            _selectionState.emit(SelectionState(start, end))
        }
    }
}

sealed class TextBodyState {
    object Error: TextBodyState()
    object Loading: TextBodyState()
    object Empty: TextBodyState()
    class ShowingText(val listOfString: List<String>): TextBodyState()
}