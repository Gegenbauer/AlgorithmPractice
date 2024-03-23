package al.binarysearch

import al.utils.generateRandomArray
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FindPeakValueTest {

    @Test
    fun testRandomSize() {
        repeat(10000) {
            val arr = generateRandomArray(200, 100).distinct().toIntArray()
            val peakValueIndex = FindPeakValue().findPeakElement(arr)
            val isPeakValue = if (peakValueIndex == 0) {
                arr[0] > arr[1]
            } else if (peakValueIndex == arr.lastIndex) {
                arr[peakValueIndex] > arr[peakValueIndex - 1]
            } else {
                arr[peakValueIndex] > arr[peakValueIndex + 1] && arr[peakValueIndex] > arr[peakValueIndex - 1]
            }
            assertTrue(isPeakValue, "arr=${arr.contentToString()}, peakValueIndex=$peakValueIndex")
        }
    }

    fun testSizeEqualsOne() {
        val arr = intArrayOf(10)
        val peakValueIndex = FindPeakValue().findPeakElement(arr)
        assertEquals(peakValueIndex, 0)
    }
}