package com.tunepruner.voixt.editor.editorscreen.model

class DocumentEvent(
    val documentEventType: DocumentEventType,
    val affectedWords: List<String>,
    val index: Int? = null,
)

class DocumentEventTransitional(
    val documentEventType: DocumentEventType,
    val affectedWords: List<DomainString>,
    val index: Int? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DocumentEventTransitional

        if (documentEventType != other.documentEventType) return false
        if (index != other.index) return false
        if (affectedWords.size != other.affectedWords.size) return false
        affectedWords.forEachIndexed { index, domainString ->
            if (!domainString.string.contentEquals(other.affectedWords[index].string)) return false
        }
        return true
    }
}