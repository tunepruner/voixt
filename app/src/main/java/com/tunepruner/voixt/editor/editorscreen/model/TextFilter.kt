package com.tunepruner.voixt.editor.editorscreen.model

sealed class TextFilter {
    class Italics(val reversed: Boolean) : TextFilter()
    class Bold(val reversed: Boolean): TextFilter()
    class Underline(val reversed: Boolean): TextFilter()
    class AllCapitalized(val reversed: Boolean): TextFilter()
    class SentenceCase(val reversed: Boolean): TextFilter()
}