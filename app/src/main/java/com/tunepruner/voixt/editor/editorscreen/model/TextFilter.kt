package com.tunepruner.voixt.editor.editorscreen.model

sealed class TextFilter {
    class Italics(val reversed: Boolean) : TextFilter()
    class Bold(val reversed: Boolean): TextFilter()
    class Underline(val reversed: Boolean): TextFilter()
    class AllCapitalized(val reversed: Boolean): TextFilter()
    class AllLowercase(val reversed: Boolean): TextFilter()
    class Capitalized(val reversed: Boolean): TextFilter()
    class ToUppercase(val reversed: Boolean): TextFilter()
}