package com.tunepruner.voixt.editor.util

fun List<String>.concatenateToOneString(): String {
    val builder = StringBuilder()
    return fold(builder) { acc, string ->
        acc.append("$string ")
    }.toString()
}