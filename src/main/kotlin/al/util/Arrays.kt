package al.util

import kotlin.random.Random

fun swap1(index1: Int, index2: Int, arr: IntArray) {
    val temp = arr[index1]
    arr[index1] = arr[index2]
    arr[index2] = temp
}

// i == j 时会出错
fun swap2(index1: Int, index2: Int, arr: IntArray) {
    arr[index1] = arr[index1] xor arr[index2]
    arr[index2] = arr[index1] xor arr[index2]
    arr[index1] = arr[index1] xor arr[index2]
}

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

fun generateRandomArray(
    size: Int,
    maxValue: Int = size,
    isPositive: Boolean = false,
    nonZero: Boolean = false
): IntArray {
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

fun createMatrix(matrixStr: String): Array<IntArray> {
    val rowStrs = matrixStr.subSequence(1, matrixStr.length - 1).split("],[")
    val rowSize = rowStrs.first().let { it.substring(1, it.length - 1).split(",").size }
    val result = Array(rowStrs.size) { IntArray(rowSize) }
    for ((rowIndex, rowStr) in rowStrs.withIndex()) {
        val rowStrWithoutBrackets = rowStr.removePrefix("[").removeSuffix("]").split(",")
        for ((index, valueStr) in rowStrWithoutBrackets.withIndex()) {
            result[rowIndex][index] = valueStr.toInt()
        }
    }
    return result
}