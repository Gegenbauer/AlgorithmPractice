package al.other

import al.others.Solution
import al.util.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class TwoSumTest {

    @Test
    fun example1() {
        assertTrue(isArrayEquals(intArrayOf(0, 1), Solution().twoSum(intArrayOf(2, 7, 11, 15), 9)))
    }

    @Test
    fun example2() {
        assertTrue(isArrayEquals(intArrayOf(1, 2), Solution().twoSum(intArrayOf(3, 2, 4), 6)))
    }

    @Test
    fun example3() {
        assertTrue(isArrayEquals(intArrayOf(0, 1), Solution().twoSum(intArrayOf(3, 3), 6)))
    }


}