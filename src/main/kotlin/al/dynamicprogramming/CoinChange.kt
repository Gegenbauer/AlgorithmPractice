package al.dynamicprogramming

import kotlin.math.min

/**
 * leetcode-322 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
class CoinChange {

    fun coinChange(coins: IntArray, amount: Int): Int {
        val cache = hashMapOf<Int, Int>() // amount: minCoins
        return process(coins, amount, cache).takeIf { it != INVALID_COUNT } ?: -1
    }

    /**
     * 带备忘录的暴力枚举
     */
    private fun process(coins: IntArray, amount: Int, cache: HashMap<Int, Int>): Int {
        if (cache[amount] != null) return cache[amount]!!

        if (amount == 0) {
            return 0
        }

        var minCoinCount = INVALID_COUNT
        for (coin in coins) {
            if (amount < coin) continue
            minCoinCount = min(minCoinCount, process(coins, amount - coin, cache) + 1)
        }
        cache[amount] = minCoinCount
        return minCoinCount
    }

    fun dp(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { INVALID_COUNT }
        dp[0] = 0
        for (i in 1 until dp.size) {
            for (coin in coins) {
                if (i < coin) continue
                dp[i] = min(dp[i - coin] + 1, dp[i])
            }
        }
        return dp[amount].takeIf { it != INVALID_COUNT } ?: -1
    }

    companion object {
        private const val INVALID_COUNT = 100000
    }

}