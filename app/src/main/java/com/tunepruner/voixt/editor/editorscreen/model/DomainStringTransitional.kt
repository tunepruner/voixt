package com.tunepruner.voixt.editor.editorscreen.model

import android.text.SpannableStringBuilder


class DomainStringTransitional(
    val richString: SpannableStringBuilder,
    val dpWidth: Int = 10 /*TODO replace this with a real algorithm*/,
    val spaceAfter: Boolean = true /*TODO replace this with real logic*/,
    val filters: List<TextFilter>? = null
)

fun String.toDomainStringTransitional(): DomainStringTransitional {
    return DomainStringTransitional(richString = SpannableStringBuilder(this))
}