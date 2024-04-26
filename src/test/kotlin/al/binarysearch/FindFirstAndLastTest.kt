package al.binarysearch

import al.util.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class FindFirstAndLastTest {

    private val solution = SearchRange()

    @Test
    fun test() {
        val arr = intArrayOf(5, 7, 7, 8, 8, 10)
        val real = solution.searchRange(arr, 8)
        val expected = intArrayOf(3, 4)
        assertTrue(
            isArrayEquals(expected, real),
            "expected=${expected.contentToString()}\n" +
                    "real=${real.contentToString()}"
        )
    }

    @Test
    fun test1() {
        val arr = intArrayOf(5, 7, 7, 8, 8, 10)
        val real = solution.searchRange(arr, 6)
        val expected = intArrayOf(-1, -1)
        assertTrue(
            isArrayEquals(expected, real),
            "expected=${expected.contentToString()}\n" +
                    "real=${real.contentToString()}"
        )
    }

    @Test
    fun test2() {
        val arr = intArrayOf()
        val real = solution.searchRange(arr, 6)
        val expected = intArrayOf(-1, -1)
        assertTrue(
            isArrayEquals(expected, real),
            "expected=${expected.contentToString()}\n" +
                    "real=${real.contentToString()}"
        )
    }
}