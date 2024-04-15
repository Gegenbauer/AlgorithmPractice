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
 *
 * 递归函数时间复杂度 = 递归函数本身时间复杂度 * 递归函数调用的次数
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

fun getMaxValue2(arr: IntArray): Int {
    return getMaxValue(arr, arr[0], arr.lastIndex)
}

// 尾递归
fun getMaxValue(arr: IntArray, curMax: Int, endIndex: Int): Int {
    if (endIndex == 0) {
        return curMax
    }
    return getMaxValue(arr, max(curMax, arr[endIndex]), endIndex - 1)
}

// 非递归实现
fun getMaxValue3(arr: IntArray): Int {
    var max = arr[0]
    var cur = 1
    while (cur < arr.size) {
        max = max(max, arr[cur])
    }
    return max
}

fun fibonacci(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
}

fun fibonacci2(n: Int): Int {
    return fibonacci(n, 1, 0)
}

// 尾递归
fun fibonacci(n: Int, pre: Int, pre2: Int): Int {
    if (n == 0) return pre2
    if (n == 1) return pre
    return fibonacci(n - 1, pre + pre2, pre)
}

// 非递归实现
fun fibonacci3(n: Int): Int {
    var pre2 = 0
    var pre1 = 1
    var curIndex = 2
    var curValue: Int = if (n == 0) pre2 else pre1
    while (curIndex <= n) {
        curValue = pre1 + pre2
        pre2 = pre1
        pre1 = curValue
        curIndex++
    }
    return curValue
}
