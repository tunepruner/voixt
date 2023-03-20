package com.tunepruner.voixt.editor.editorscreen.model

import android.text.SpannableStringBuilder
import com.tunepruner.voixt.editor.util.StringLengthResolver

class DomainString(
    val richString: SpannableStringBuilder,
    val dpWidth: Int = StringLengthResolver.resolveStringToDp(richString.toString()),
    val spaceAfter: Boolean = true /*TODO replace this with real logic*/,
    val filters: List<TextFilter>? = null
)

fun String.toDomainString(): DomainString {
    return DomainString(richString = SpannableStringBuilder(this))
}