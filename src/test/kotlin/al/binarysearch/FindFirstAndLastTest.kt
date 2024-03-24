package al.binarysearch

import al.utils.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class FindFirstAndLastTest {

    @Test
    fun test() {
        repeat(1000) {
            val arr = generateRandomArray(100, 50).sorted().toIntArray()
            val target = Random.nextInt(60)
            val expectValue = arr.withIndex().filter { it.value == target }.run {
                if (isEmpty()) {
                    arrayOf(-1, -1)
                } else if (size == 1) {
                    arrayOf(get(0).index, get(0).index)
                } else {
                    arrayOf(first().index, last().index)
                }
            }

            val realValue = searchRange(arr, target)

            assertTrue(
                realValue.first() == expectValue.first()
                        && realValue.last() == expectValue.last(),
                "arr=${arr.contentToString()}, " +
                        "expectedValue=${expectValue.contentToString()}" +
                ", realValue=${realValue.contentToString()}"
            )
        }
    }
}