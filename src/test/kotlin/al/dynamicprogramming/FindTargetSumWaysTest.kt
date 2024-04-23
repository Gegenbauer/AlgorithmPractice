package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class FindTargetSumWaysTest {

    @Test
    fun test() {
        val nums = intArrayOf(1, 1, 1, 1, 1)
        val target = 3
        val real = FindTargetSumWays().findTargetSumWays(nums, target)
        assertEquals(5, real)
    }

    @Test
    fun test2() {
        val nums = intArrayOf(1)
        val target = 1
        val real = FindTargetSumWays().findTargetSumWays(nums, target)
        assertEquals(1, real)
    }
}