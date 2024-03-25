package al.recursion

import kotlin.math.max

// 所有递归实现可以改为非递归

/**
 * 使用递归求数组最大值
 */
fun getMaxValue(arr: IntArray): Int {
    return getMaxValue(arr, arr.size - 1)
}

private fun getMaxValue(arr: IntArray, endIndex: Int): Int {
    if (endIndex == 0) {
        return arr[0]
    }
    return max(getMaxValue(arr, endIndex - 1), arr[endIndex])
}