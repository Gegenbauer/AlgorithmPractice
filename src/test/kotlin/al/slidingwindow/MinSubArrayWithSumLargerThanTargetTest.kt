package al.slidingwindow

import kotlin.test.Test
import kotlin.test.assertEquals

class MinSubArrayWithSumLargerThanTargetTest {

    private val solution = MinSubArrayWithSumLargerThanTarget()

    @Test
    fun test() {
        val arr = intArrayOf(2, 3, 1, 2, 4, 3)
        assertEquals(2, solution.minSubArrayLen(7, arr))
    }
}