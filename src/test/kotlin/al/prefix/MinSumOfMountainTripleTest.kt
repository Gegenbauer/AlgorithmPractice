package al.prefix

import al.util.generateRandomArray
import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals

class MinSumOfMountainTripleTest {

    @Test
    fun test() {
        repeat(100) {
            val arr = generateRandomArray(100, 50, isPositive = true, nonZero = true)
            val real = MinSumOfMountainTriple().minimumSum(arr)

            var expected = Int.MAX_VALUE

            for (mid in 1 until arr.size - 1) {
                for (left in 0 until mid) {
                    for (right in mid + 1 until arr.size) {
                        if (arr[mid] > arr[left] && arr[mid] > arr[right]) {
                            expected = min(expected, arr[mid] + arr[left] + arr[right])
                        }
                    }
                }
            }

            assertEquals(expected, real)
        }
    }

    @Test
    fun testSpecific() {
        val arr = intArrayOf(8, 6, 1, 5, 3)
        val real = MinSumOfMountainTriple().minimumSum(arr)

        var expected = Int.MAX_VALUE

        for (mid in 1 until arr.size - 1) {
            for (left in 0 until mid) {
                for (right in mid + 1 until arr.size) {
                    if (arr[mid] > arr[left] && arr[mid] > arr[right]) {
                        expected = min(expected, arr[mid] + arr[left] + arr[right])
                    }
                }
            }
        }

        assertEquals(expected, real)
    }
}