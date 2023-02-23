package com.tunepruner.voixt.editor.util

class StringToListConverter {
    fun convert(string: String): List<String> {
        val delimiters = Regex("(?<=\\W)|(?=\\W)|\\s+|\\n")
        val substrings = string.split(delimiters)
        val result = mutableListOf<String>()

        for (i in substrings.indices) {
            if (substrings[i].isNotEmpty()) {
                if (substrings[i].contentEquals(" ")) {
                    val lastSubstringWithAddedSpace = "${result.last()} "
                    result.removeLast()
                    result.add(lastSubstringWithAddedSpace)
                } else {
                    if (i % 2 == 0) {
                        // add the current substring to the result list
                        result.add(substrings[i])
                    } else {
                        // add the current delimiter to the result list
                        result.add(substrings[i])
                    }
                }
            }
        }
        return result
    }
}