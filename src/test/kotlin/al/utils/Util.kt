package al.utils

import kotlin.random.Random

fun isArrayEquals(arr1: IntArray, arr2: IntArray): Boolean {
    if (arr1.size != arr2.size) {
        return false
    }

    for ((index, value) in arr1.withIndex()) {
        if (value != arr2[index]) {
            return false
        }
    }
    return true
}

fun generateRandomArray(size: Int, maxValue: Int = size): IntArray {
    return IntArray(size) {
        Random.nextInt(0, maxValue)
    }
}