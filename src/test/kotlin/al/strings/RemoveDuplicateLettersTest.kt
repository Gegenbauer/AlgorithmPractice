package al.strings

import kotlin.test.Test
import kotlin.test.assertEquals

class RemoveDuplicateLettersTest {

    @Test
    fun test() {
        assertEquals("abc", RemoveDuplicateLetters().removeDuplicateLetters("bcabc"))
    }

    @Test
    fun test2() {
        assertEquals("acdb", RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"))
    }
}