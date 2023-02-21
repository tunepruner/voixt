package com.tunepruner.voixt.editor.datasource

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MockVoiceToTextDataSourceImpl : VoiceToTextDataSource {
    private val _textFlow = MutableSharedFlow<String>()
    override val textFlow: SharedFlow<String> = _textFlow

    override suspend fun startFlow() {
        TODO("Not yet implemented")
    }

    override suspend fun stopFlow() {
        TODO("Not yet implemented")
    }
}