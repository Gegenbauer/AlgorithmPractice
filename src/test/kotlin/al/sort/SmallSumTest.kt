package al.sort

import al.sort.merge.SmallSum
import al.utils.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class SmallSumTest {

    @Test
    fun testRandom() {
        repeat(10000) {
            val size = Random.nextInt(1, 100)
            val arr = generateRandomArray(size)
            val real = SmallSum().smallSum(arr.copyOf())

            var sum = 0
            arr.forEachIndexed { index, i ->
                val smallSum = arr.filterIndexed { inde2, j ->
                    inde2 < index && j < i
                }.sum()
                sum += smallSum
            }

            assertEquals(sum, real, "arr=${arr.contentToString()}")
        }
    }

    @Test
    fun testSpecific() {
        val arr = intArrayOf(-10, -30, -30, 0)
        val real = SmallSum().smallSum(arr.copyOf())

        var sum = 0
        arr.forEachIndexed { index, i ->
            val smallSum = arr.filterIndexed { inde2, j ->
                inde2 < index && j < i
            }.sum()
            sum += smallSum
        }

        assertEquals(sum, real)
    }
}