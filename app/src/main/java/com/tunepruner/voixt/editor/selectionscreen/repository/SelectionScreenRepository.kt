package com.tunepruner.voixt.editor.selectionscreen.repository

import kotlinx.coroutines.flow.MutableStateFlow

class SelectionScreenRepository {
    private val _textBody =
        MutableStateFlow<RepositoryResult>(RepositoryResult.Data(""))
    val textBody = _textBody
}

sealed class RepositoryResult {
    object Error: RepositoryResult()
    class Data(data: String): RepositoryResult()
}