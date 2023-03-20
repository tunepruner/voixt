package com.tunepruner.voixt.editor.util

import android.text.SpannableStringBuilder

fun List<String>.concatenateToOneStringLegacy(): String {
    val builder = StringBuilder()
    return fold(builder) { acc, string ->
        acc.append("$string ")
    }.toString()
}

fun List<SpannableStringBuilder>.concatenateToOneString(): String {
    val builder = StringBuilder()
    return this.map { it.chars() }.fold(builder) { acc, string ->
        acc.append("$string ")
    }.toString()
}