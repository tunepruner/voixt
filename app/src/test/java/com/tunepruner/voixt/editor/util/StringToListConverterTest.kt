package com.tunepruner.voixt.editor.util

import org.junit.Test

internal class StringToListConverterTest {
    val _sut = StringToListConverter()

    @Test
    fun `returns a non empty list for a non empty string`() {
        for (string in sampleSentences) {
            assert(_sut.convert(string).isNotEmpty())
        }
    }

    @Test
    fun `returns empty list for empty string`() {
        assert(_sut.convert("").isEmpty())
    }

    @Test
    fun `never returns a special character in same string as letter character`() {
        for (string in sampleSentences) {
            val list = _sut.convert(string)
            list.forEach { myString ->
                var hasLetter = false
                var hasSpecialChar = false
                myString.forEach { char ->
                    if (char.isLetter()) {
                        hasLetter = true
                    } else {
                        if (!char.isDigit() && char != ' ') hasSpecialChar = true
                    }
                }
                println(
                    " The string is : ${myString} \nThe result is : ${!(hasSpecialChar && hasLetter)}"
                )
                assert(
                    !(hasSpecialChar && hasLetter)
                )
            }
        }
    }

    @Test
    fun `returns line break as a separate string`() {
        for (string in sampleSentences) {
            val string = "A string, with a \nline break!!"
            val result = _sut.convert(string)
            result.forEach {
                print(it)
            }
            assert(result.size == 10)
        }
    }
}

val sampleSentences = listOf(
    """
            This is a sentence.
        """.trimIndent(),
    """
            $! @#sdfas 09823 fsdn as098%$^# $(Y&* ^{}:">23fj sd 230 dfs jl k320sd flkj 234580dfg dfsgjlk34 f40fer s --sasdf-sadf-sadf-f sdflkj*sdflkj*sdflkj8sdfu8^sdfjkh
        """.trimIndent(),
    """
            THIS SENTENCE IS IN ALL CAPS.
        """.trimIndent(),
    """
            This sentence has no punctuation
        """.trimIndent(),
    """
            First sentence. Second sentence. 
        """.trimIndent(),
    """
            One, two, three, and four are all numbers.
        """.trimIndent(),
    """
            A not-so-great hyphenated example!
        """.trimIndent(),
    """
            Let's try apostrophes!
        """.trimIndent(),
    """
            I*wonder*what's*for*breakfast!
        """.trimIndent(),
    """
            This is a sentence (and these are parens) but it doesn't say anything.
        """.trimIndent(),
    """
            This is a broken( parens )situation.
        """.trimIndent(),
    """
            This are \"quotes\". 
        """.trimIndent(),
    """
            Ummmm...ellipsis!
        """.trimIndent(),
    "A string \nwith\n line bre\naks \n* &\n *\n&"
)