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

fun generateRandomArray(size: Int, maxValue: Int = size, isPositive: Boolean = false, nonZero: Boolean = false): IntArray {
    return IntArray(size) {
        val start = when {
            isPositive && nonZero -> 1
            isPositive -> 0
            else -> -maxValue
        }
        Random.nextInt(start, maxValue)
    }
}

/**
 * 产生一个随机数组，其中有一个数出现 k 次，其他数都出现 m 次
 * m > 1, k < m
 * [valueCount] 指其他数的值的数目
 * return Pair(数组, target)
 */
fun generateKMArray(k: Int, m: Int, valueCount: Int): Pair<IntArray, Int> {
    val otherValues = mutableSetOf<Int>()
    var generatedValueCount = 0
    while (generatedValueCount < valueCount) {
        val value = Random.nextInt(0, valueCount * 2)
        if (value !in otherValues) {
            otherValues.add(value)
            generatedValueCount++
        }
    }

    var targetValue: Int

    while (true) {
        targetValue = Random.nextInt(0, valueCount * 2)
        if (targetValue !in otherValues) {
            break
        }
    }

    val result = mutableListOf<Int>()
    repeat(k) {
        result.add(targetValue)
    }
    repeat(m) {
        otherValues.forEach {
            result.add(it)
        }
    }
    result.shuffle()

    return Pair(result.toIntArray(), targetValue)
}