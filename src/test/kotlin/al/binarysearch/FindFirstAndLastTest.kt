package al.binarysearch

import al.utils.generateRandomArray
import kotlin.random.Random
import kotlin.test.assertEquals

class FindFirstAndLastTest {

    fun test() {
        repeat(1000) {
            val arr = generateRandomArray(1000, 400).sorted().toIntArray()
            val target = Random.nextInt(400)
            val expectValue = arr.withIndex().filter { it.value == target }.run {
                if (isEmpty()) {
                    arrayOf(-1, -1)
                } else if (size == 1) {
                    arrayOf(get(0), get(0))
                } else {
                    arrayOf(first(), last())
                }
            }

            val realValue = searchRange(arr, target)

            assertEquals(realValue.first(), expectValue.first())
            assertEquals(realValue.last(), expectValue.last())
        }
    }
}