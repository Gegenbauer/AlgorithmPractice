package al.sort

import al.sort.merge.BiggerThanRightTwice
import al.utils.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class BiggerThanTwiceTest {

    @Test
    fun testRandomArrays() {
        repeat(10000) {
            val size = Random.nextInt(1, 1000)
            val arr = generateRandomArray(size)

            val real = BiggerThanRightTwice().biggerTwice(arr.copyOf())
            var expected = 0
            arr.forEachIndexed { index, i ->
                val pairs = arr.filterIndexed { index2, j ->
                    j * 2 < i && index2 > index
                }.count()
                expected += pairs
            }
            assertEquals(expected, real, arr.contentToString())
        }
    }

    @Test
    fun testSpecificArray() {
        val arr = intArrayOf(2, 1, 3, 3, 1)

        val real = BiggerThanRightTwice().biggerTwice(arr.copyOf())
        var expected = 0
        arr.forEachIndexed { index, i ->
            val pairs = arr.filterIndexed { index2, j ->
                j * 2 < i && index2 > index
            }.count()
            expected += pairs
        }
        assertEquals(expected, real, arr.contentToString())
    }
}