package com.tunepruner.voixt.editor.editorscreen.model

sealed class DocumentEvent(
    val startingIndex: Int? = null,
    val strings: List<DomainString>? = null,
) {

    class Initialization(
        val stringsToAdd: List<DomainString>,
    ) : DocumentEvent(startingIndex = 0, strings = stringsToAdd)

    class Insertion(
        val insertionIndex: Int,
        val stringsToAdd: List<DomainString>,
    ) : DocumentEvent(startingIndex = insertionIndex, strings = stringsToAdd)

    class Deletion(
        val deletionStartIndex: Int,
        val stringsToRemove: List<DomainString>
    ) : DocumentEvent(startingIndex = deletionStartIndex, strings = stringsToRemove)

    class FilterAddition(
        val filterStartIndex: Int,
        val stringsToReceiveFilter: List<DomainString>,
        val filterToApply: TextFilter,
    ) : DocumentEvent(startingIndex = filterStartIndex, strings = stringsToReceiveFilter)

    /** This event should be triggered whenever one or more of the DomainStrings
     * being passed in already has a given filter. Otherwise, [FilterAddition] should
     * be triggered. */
    class FilterRemoval(
        val filterStartIndex: Int,
        val stringsToRemoveFilterFrom: List<DomainString>,
        val filterToRemove: TextFilter
    ) : DocumentEvent(startingIndex = filterStartIndex, strings = stringsToRemoveFilterFrom)

    /**This event should be triggered for events that require multiple events at once.
     *
     * Example: Hyphenated text (here's a not-so-great example) requires initially comes
     * in as separate words, but applying the hyphenation requires removing those \
     * words (They'll be DomainString objects), and then putting them back,
     * but with a "-" added in between each pair, as a DomainString. */
    class Composite(val listOfEvents: List<DocumentEvent>) : DocumentEvent()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DocumentEvent

        if (this.javaClass != other.javaClass) return false
        if (this.startingIndex != other.startingIndex) return false
        if (this.strings?.size != other.strings?.size) return false
        this.strings?.forEachIndexed { index, domainString ->
            if (other.strings?.get(index).let { otherString ->
                    domainString.richString.contentEquals(otherString?.richString)
                }) return false
        } ?: run {
            if (other.strings != null) return false
        }
        return true
    }
}