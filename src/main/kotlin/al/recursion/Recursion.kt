package al.recursion

import kotlin.math.max

/**
 * 最好能把调用的过程画出结构图，这有利于分析递归
 * 所有递归实现可以改为非递归
 * 递归底层是通过系统栈来实现的
 *
 * Master 公式
 * 如果一个递归实现的时间复杂度可以表示为
 * T(N) = a * T(N / b) + O(N ^ d)
 * 则可以直接通过 Master 公式来确定时间复杂度
 * 如果 log(b, a) < d, 复杂度为 O(N ^ d)
 * 如果 log(b, a) > d, 复杂度为 O(N ^ log(b, a))
 * 如果 log(b, a) == d, 复杂度为 O(N ^ d * logN)
 */

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