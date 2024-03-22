package al.binarysearch

import al.utils.generateRandomArray
import binarySearch
import java.util.*
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class BinarySearchTest {

    @Test
    fun testBinarySearch() {
        repeat(1000) {
            val arr = generateRandomArray(1000, 500)
            arr.sort()
            val target = Random.nextInt(500)
            val index = binarySearch(arr, target)
            val buildInMethodResult = Arrays.binarySearch(arr, target)

            assertTrue(
                isSearchResultEquals(arr, index, buildInMethodResult),
                "customResult=$index, buildInResult=$buildInMethodResult"
            )
        }
    }

    private fun isSearchResultEquals(arr: IntArray, customResult: Int, buildInResult: Int): Boolean {
        return if (customResult < 0) {
            buildInResult < 0
        } else {
            arr[customResult] == arr[buildInResult]
        }
    }
}