package al.dynamicprogramming

import kotlin.math.max

/**
 * lintcode-125 背包问题（二）
 * 0-1 背包问题
 *
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 * 问最多能装入背包的总价值是多大?
 */
class Knapsack {

    fun maxValueOfBags(wt: IntArray, values: IntArray, w: Int): Int {
        return dp(wt, values, w)
    }

    /**
     * 暴力尝试
     * 从左往右的尝试模型
     * w: w - 0
     * cur: 0 - size
     */
    private fun process(wt: IntArray, values: IntArray, rest: Int, cur: Int): Int {
        // 无效路径
        if (rest < 0) return -1

        if (cur >= wt.size) return 0

        val notChoose = process(wt, values, rest, cur + 1)
        val temp = process(wt, values, rest - wt[cur], cur + 1)
        val choose = if (temp == -1) {
            0
        } else {
            temp + values[cur]
        }
        return max(choose, notChoose)
    }

    /**
     * 由暴力尝试改写成动态规划
     */
    private fun dp(wt: IntArray, values: IntArray, w: Int): Int {
        val dp = Array(w + 1) { IntArray(wt.size + 1) }
        for (rest in 0..w) {
            for (stackIndex in wt.size - 1 downTo 0) {
                val notChoose = dp[rest][stackIndex + 1]
                val choose = if (rest - wt[stackIndex] < 0) {
                    0
                } else {
                    dp[rest - wt[stackIndex]][stackIndex + 1] + values[stackIndex]
                }
                dp[rest][stackIndex] = max(choose, notChoose)
            }
        }
        return dp[w][0]
    }
}