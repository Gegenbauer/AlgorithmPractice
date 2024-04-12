package al.slidingwindow

import kotlin.test.Test
import kotlin.test.assertEquals

class SubArrayCountWithProductSmallThanTargetTest {

    private val solution = SubArrayCountWithProductSmallThanTarget()

    @Test
    fun test() {
        val arr = intArrayOf(10, 5, 2, 6)
        assertEquals(8, solution.numSubarrayProductLessThanK(arr, 100))
    }
}