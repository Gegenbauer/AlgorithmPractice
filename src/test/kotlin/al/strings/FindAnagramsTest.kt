package al.strings

import kotlin.test.Test
import kotlin.test.assertEquals

class FindAnagramsTest {

    @Test
    fun test() {
        val s = "cbaebabacd"
        val p = "abc"
        val real = FindAnagrams().findAnagrams(s, p)
        val target = intArrayOf(0, 6)
        assertEquals(target.joinToString(), real.joinToString())
    }

    @Test
    fun test2() {
        val s = "abab"
        val p = "ab"
        val real = FindAnagrams().findAnagrams(s, p)
        val target = intArrayOf(0, 1, 2)
        assertEquals(target.joinToString(), real.joinToString())
    }
}