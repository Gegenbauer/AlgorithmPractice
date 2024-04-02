package al.sort

import al.sort.merge.CountOfRangeSum
import kotlin.test.Test
import kotlin.test.assertEquals

class CountOfRangeSumTest {

    @Test
    fun testSample() {
        assertEquals( 3, CountOfRangeSum().countRangeSum(intArrayOf(-2, 5, -1), -2, 2))
        assertEquals(1, CountOfRangeSum().countRangeSum(intArrayOf(0), 0, 0))
        assertEquals(4, CountOfRangeSum().countRangeSum(intArrayOf(2147483647,-2147483648,-1,0), -1, 0))
        assertEquals(3, CountOfRangeSum().countRangeSum(intArrayOf(-2147483647,0,-2147483647,2147483647), -564, 3864))
    }
}