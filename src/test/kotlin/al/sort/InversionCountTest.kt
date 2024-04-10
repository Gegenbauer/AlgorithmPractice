package al.sort

import al.sort.merge.InversionCount
import al.util.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class InversionCountTest {

    @Test
    fun testRandomArrays() {
        repeat(10000) {
            val size = Random.nextInt(1, 1000)
            val arr = generateRandomArray(size)

            val real = InversionCount().reversePairs(arr.copyOf())
            var expected = 0
            arr.forEachIndexed { index, i ->
                val pairs = arr.filterIndexed { index2, j ->
                    j < i && index2 > index
                }.count()
                expected += pairs
            }
            assertEquals(expected, real, arr.contentToString())
        }
    }

    @Test
    fun testSpecificArray() {
        val arr = intArrayOf(2, 1, 3, 3, 1)

        val real = InversionCount().reversePairs(arr.copyOf())
        var expected = 0
        arr.forEachIndexed { index, i ->
            val pairs = arr.filterIndexed { index2, j ->
                j < i && index2 > index
            }.count()
            expected += pairs
        }
        assertEquals(expected, real, arr.contentToString())
    }
}