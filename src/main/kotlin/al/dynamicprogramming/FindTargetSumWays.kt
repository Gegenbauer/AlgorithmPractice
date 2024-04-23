package al.dynamicprogramming

/**
 * leetcode-494 目标和
 *
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
class FindTargetSumWays {

    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        // 如果 sum 和 target 奇偶不同则无解
        if ((sum and 1) xor (target and 1) > 0) return 0
        if (sum < target) return 0
        val target2 = (sum - target) / 2
        return dp(nums, target2)
    }

    /**
     * 暴力尝试，从左往右
     */
    private fun process(nums: IntArray, rest: Int, curIndex: Int): Int {
        if (curIndex == nums.size) {
            return if (rest == 0) {
                1
            } else {
                0
            }
        }
        val select = process(nums, rest - nums[curIndex], curIndex + 1)
        val notSelect = process(nums, rest, curIndex + 1)
        return select + notSelect
    }

    /**
     * 直接转化成 dp 数组，会出现负数索引，所以需要做转化
     * 将负数的和作为一个整体
     * positives - negatives = target
     * positives + negatives = sum
     * positive = (sum - target) / 2
     * 可以转化为求和为 (sum - target) / 2 的组合数，其余元素改为负号
     */
    private fun dp(nums: IntArray, target: Int): Int {

        val dp = Array(nums.size + 1) { IntArray(target + 1) }
        for (rest in 0..target) {
            dp[nums.size][rest] = if (rest == 0) {
                1
            } else {
                0
            }
        }

        for (rest in 0..target) {
            for (curIndex in nums.size - 1 downTo 0) {
                val select = if (nums[curIndex] > rest) {
                    0
                } else {
                    dp[curIndex + 1][rest - nums[curIndex]]
                }
                val notSelect = dp[curIndex + 1][rest]
                dp[curIndex][rest] = select + notSelect
            }
        }

        return dp[0][target]
    }
}