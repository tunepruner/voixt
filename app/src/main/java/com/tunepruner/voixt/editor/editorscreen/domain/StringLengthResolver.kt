package com.tunepruner.voixt.editor.editorscreen.domain

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class StringLengthResolver {
    fun resolveStringToDp(string: String): Dp {
        TODO("I don't know how to resolve string length yet!")
        // Since I will be using a monospaced font, this shouldn't be super hard,
        // but it will involve rational for determining when there should be extra
        // space after a string, as well as making sure the measurements ACTUALLY
        // look good.
        return 0.dp
    }
}