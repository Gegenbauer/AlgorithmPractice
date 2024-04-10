package al.bitwiseoperation

import al.util.generateKMArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class FindOccurrenceCountKNumTest {

    @Test
    fun testExample1() {

        val nums = intArrayOf(2, 2, 3, 2)
        val result = FindOccurrenceCountKNum().singleNumber(nums)
        assertEquals(3, result)
    }

    @Test
    fun testExample2() {

        val nums = intArrayOf(0, 1, 0, 1, 0, 1, 99)
        val result = FindOccurrenceCountKNum().singleNumber(nums)
        assertEquals(99, result)
    }

    @Test
    fun testExample3() {

        val nums = intArrayOf(-2, -2, 1, 1, 4, 1, 4, 4, -4, -2)
        val result = FindOccurrenceCountKNum().singleNumber(nums)
        assertEquals(-4, result)
    }

    @Test
    fun testSingleNumRandomSamples() {
        repeat(1000) {
            val nums = generateKMArray(1, 3, 100)
            val realResult = FindOccurrenceCountKNum().singleNumber(nums.first)
            assertEquals(nums.second, realResult)
        }
    }

    @Test
    fun testKMRandomSamples() {
        repeat(10000) {
            val randomM = Random.nextInt(2, 11)
            val randomK = Random.nextInt(1, randomM)
            val nums = generateKMArray(randomK, randomM, 100)
            val realResult = FindOccurrenceCountKNum().findOccurrenceCountKNum(nums.first, randomK, randomM)
            assertEquals(nums.second, realResult)
        }
    }

    @Test
    fun testAbnormalKm() {
        val nums = intArrayOf(-2, -2, 1, 1, 4, 1, 4, 4, -4, -4, -2)
        val result = FindOccurrenceCountKNum().singleNumber(nums)
        assertEquals(-1, result)
    }
}