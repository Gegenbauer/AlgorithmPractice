package al.utils

import al.util.generateRandomArray
import al.util.swap1
import al.util.swap2
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class SwapArrayElementsTest {

    @Test
    fun testSwap1() {
        val arr = generateRandomArray(200)
        repeat(1000) {
            val index1 = Random.nextInt(arr.size - 1)
            val index2 = Random.nextInt(arr.size - 1)
            val oldValueAtIndex1 = arr[index1]
            val oldValueAtIndex2 = arr[index2]
            swap1(index1, index2, arr)
            assertTrue { arr[index1] == oldValueAtIndex2 && arr[index2] == oldValueAtIndex1 }
        }
    }

    @Test
    fun testSwap2() {
        val arr = generateRandomArray(200)
        repeat(1000) {
            val index1 = Random.nextInt(arr.size - 1)
            val index2 = Random.nextInt(arr.size - 1)
            val oldValueAtIndex1 = arr[index1]
            val oldValueAtIndex2 = arr[index2]
            swap2(index1, index2, arr)
            assertTrue { arr[index1] == oldValueAtIndex2 && arr[index2] == oldValueAtIndex1 }
        }
    }
}