package com.tunepruner.voixt.editor.util

class StringToListConverter {
    fun convert(stringToConvert: String): List<String> {
        val words = mutableListOf<String>()
        val currentWord = StringBuilder()

        // Go through each char in string to chop it up
        // into words.
        stringToConvert.forEach { char ->
            when {
                char.isSpace() -> {
                    // Whenever we find a space, we call that the
                    // end of the word. However, we include the space
                    // within the word that precedes it, if any.
                    currentWord.append(char)
                    words.add(currentWord.toString())
                    currentWord.clear()
                }
                char == '\n' -> {
                    // We found a line break. Line breaks should always
                    // be in their own "word", by themselves.
                    if (currentWord.isNotEmpty()) words.add(currentWord.toString())
                    words.add(char.toString())
                    currentWord.clear()
                }
                char.isSpecialCharacter() -> {
                    // Whenever we find a special character, we call it the
                    // end of a word, so we conclude whatever word we were
                    // building previously. But we don't add it to the
                    // list of words yet, because it might have a space
                    // after it, which it will need to include.
                    if (currentWord.isNotEmpty()) words.add(currentWord.toString())
                    currentWord.clear()
                    currentWord.append(char)
                }
                else -> {
                    // If we're here, we're working with a letter or number.
                    if (currentWord.containsSpecialCharacter() && currentWord.isNotEmpty()){
                        // This means there's already a word under construction, but it
                        // has a special character it in. We should conclude that word
                        // before continuing.
                        words.add(currentWord.toString())
                        currentWord.clear()
                        currentWord.append(char)
                    } else {
                        // If we're here, we're clear to simply add a letter to the
                        // current word without concluding it.
                        currentWord.append(char)
                    }
                }
            }
        }
        // Finally, since some branches of the logic above don't conclude the word,
        // we need to do so here.
        if (currentWord.isNotEmpty()) words.add(currentWord.toString())

        return words
    }

    private fun Char.isSpace() = this == ' '
    private fun Char.isSpecialCharacter() = !isLetterOrDigit()

    private fun StringBuilder.containsSpecialCharacter(): Boolean {
        return this.toString().any { it.isSpecialCharacter() }
    }
}