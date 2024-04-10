package al.recursion

import al.util.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class TestGetMaxValue {

    @Test
    fun testRandomArray() {
        repeat(10000) {
            val size = Random.nextInt(1, 100)
            val arr = generateRandomArray(size, 200)
            assertEquals(arr.max(), getMaxValue(arr))
        }
    }
}