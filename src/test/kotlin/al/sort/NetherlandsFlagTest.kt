package al.sort

import al.sort.quick.NetherlandsFlags
import al.utils.generateRandomArray
import al.utils.isArrayEquals
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class NetherlandsFlagTest {

    @Test
    fun testRandom() {
        repeat(10000) {
            val size = Random.nextInt(0, 100)
            val arr = generateRandomArray(size + 1, 2, isPositive = true)
            val copy = arr.copyOf()
            NetherlandsFlags().sortColors(copy)
            assertTrue { isArrayEquals(arr.sorted().toIntArray(), copy) }
        }
    }
}