package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class CombinationSum4Test {

    @Test
    fun test1() {
        val nums = intArrayOf(1, 2, 3)
        val target = 4
        val real = CombinationSum4().combinationSum4(nums, target)
        assertEquals(7, real)
    }

    @Test
    fun test2() {
        val nums = intArrayOf(9)
        val target = 3
        val real = CombinationSum4().combinationSum4(nums, target)
        assertEquals(0, real)
    }
}