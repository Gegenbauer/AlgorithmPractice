package al.table

import kotlin.test.Test
import kotlin.test.assertEquals

class LongestConsecutiveTest {

    @Test
    fun test() {
        val nums = intArrayOf(100, 4, 200, 1, 3, 2)
        val real = LongestConsecutive().longestConsecutive(nums)
        assertEquals(4, real)
    }

    @Test
    fun test2() {
        val nums = intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)
        val real = LongestConsecutive().longestConsecutive(nums)
        assertEquals(9, real)
    }
}