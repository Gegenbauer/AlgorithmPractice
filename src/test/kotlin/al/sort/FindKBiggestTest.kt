package al.sort

import al.sort.heap.FindKBiggest
import kotlin.test.Test
import kotlin.test.assertEquals

class FindKBiggestTest {

    @Test
    fun test() {
        //assertEquals(5, FindKBiggest().findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
        assertEquals(4, FindKBiggest().findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
        //assertEquals(3, FindKBiggest().findKthLargest(intArrayOf(7, 6, 5, 4, 3, 2, 1), 5))
    }
}