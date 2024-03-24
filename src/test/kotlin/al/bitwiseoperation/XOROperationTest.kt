package al.bitwiseoperation

import kotlin.test.Test
import kotlin.test.assertEquals

class XOROperationTest {

    @Test
    fun testSingleNumber() {
        val arr = intArrayOf(2, 2, 1)
        val singleNumber = XOROperation().singleNumber(arr)
        assertEquals(singleNumber, 1)
    }

    @Test
    fun testSingleTwoNumber() {
        val arr = intArrayOf(0, 1)
        val singleTwoNum = XOROperation().singleTwoNumber(arr)
        println(singleTwoNum.contentToString())
    }
}