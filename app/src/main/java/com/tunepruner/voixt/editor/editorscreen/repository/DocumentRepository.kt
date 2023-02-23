package com.tunepruner.voixt.editor.editorscreen.repository

import com.tunepruner.voixt.editor.editorscreen.datasource.VoiceToTextDataSourceImpl
import kotlinx.coroutines.flow.SharedFlow

class DocumentRepository {
    private val voiceToTextDataSource = VoiceToTextDataSourceImpl()
    // TODO this file should house all the things that are currently in [RichWordDocumentViewModel],
    //  because THAT file is actually where domain logic should exist. Particularly, that file can
    //  house the logic for the , and maintain the selection state, separate
    //  from the repository layer. There should be another view model that holds the domain logic for
    //  the screen with the ControlPanel.

}