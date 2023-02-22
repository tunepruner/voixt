package com.tunepruner.voixt.editor.model

class DocumentEvent(
    val documentEventType: DocumentEventType,
    val affectedWords: List<String>,
    val index: Int? = null,
)