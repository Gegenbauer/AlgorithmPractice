package al.dynamicprogramming

/**
 * leetcode-518 零钱兑换 II
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
class CoinChange2 {
    fun change(amount: Int, coins: IntArray): Int {
        return dp(amount, coins)
    }

    /**
     * 从左往右尝试，需要去重，要有一个 start 防止出现重复组合
     */
    private fun process(rest: Int, coins: IntArray, start: Int): Int {
        if (rest == 0) {
            return 1
        } else if (rest < 0) {
            return 0
        }
        var compositions = 0
        for (i in start until coins.size) {
            if (coins[i] > rest) continue
            compositions += process(rest - coins[i], coins, i)
        }
        return compositions
    }

    private fun dp(target: Int, coins: IntArray): Int {
        val dp = Array(target + 1) { IntArray(coins.size + 1) }
        for (start in 0..coins.size) {
            dp[0][start] = 1
        }
        for (rest in 1..target) {
            for (start in coins.size downTo 0) {
                var compositions = 0
                for (i in start until coins.size) {
                    if (coins[i] > rest) continue
                    compositions += dp[rest - coins[i]][i]
                }
                dp[rest][start] = compositions
            }
        }
        return dp[target][0]
    }
}