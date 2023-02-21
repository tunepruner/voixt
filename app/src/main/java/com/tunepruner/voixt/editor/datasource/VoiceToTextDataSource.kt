package com.tunepruner.voixt.editor.datasource

import kotlinx.coroutines.flow.SharedFlow

interface VoiceToTextDataSource {
    val textFlow: SharedFlow<String>

    suspend fun startFlow()
    suspend fun stopFlow()
}