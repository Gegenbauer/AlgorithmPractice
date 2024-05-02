package al.slidingwindow

import kotlin.test.Test
import kotlin.test.assertEquals

class SubArraySumTest {

    @Test
    fun test() {
        val arr = intArrayOf(1, 1, 1)
        val real = SubArrSum().subarraySum(arr, 2)
        assertEquals(2, real)
    }

    @Test
    fun test2() {
        val arr = intArrayOf(1, 2, 3)
        val real = SubArrSum().subarraySum(arr, 3)
        assertEquals(2, real)
    }

    @Test
    fun test3() {
        val arr = intArrayOf(1)
        val real = SubArrSum().subarraySum(arr, 0)
        assertEquals(0, real)
    }
}