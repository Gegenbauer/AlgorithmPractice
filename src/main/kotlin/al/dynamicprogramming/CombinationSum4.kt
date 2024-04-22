package al.dynamicprogramming

import java.util.*

/**
 * leetcode-377 组合总和 Ⅳ
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 */
class CombinationSum4 {

    fun combinationSum4(nums: IntArray, target: Int): Int {
        return dp(nums, target)
    }

    private fun process(nums: IntArray, rest: Int): Int {
        // 前面决定不正确，无效组合
        if (rest < 0) {
            return 0
        }
        // 前面决定正确，认为是一种组合
        if (rest == 0) {
            return 1
        }
        var ans = 0
        for (num in nums) {
            if (rest < num) continue
            ans += process(nums, rest - num)
        }
        return ans
    }

    private fun dp(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1
        for (rest in 1..target) {
            for (num in nums) {
                if (num > rest) continue
                dp[rest] += dp[rest - num]
            }
        }
        return dp[target]
    }
}