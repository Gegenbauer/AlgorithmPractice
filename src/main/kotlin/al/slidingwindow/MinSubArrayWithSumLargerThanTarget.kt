package al.slidingwindow

import kotlin.math.min

/**
 * leetcode-209 长度最小的子数组
 * 不能用归并，因为每一次合并都需要知道两个元素原索引值，来求最小长度
 * 而归并每次合并会
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
 * 子数组
 *  [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 *
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * 这里因为确定都是正数，所以前缀和数组是递增的，因此也可以通过固定左区间，用二分查找找到右区间
 */
class MinSubArrayWithSumLargerThanTarget {

    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var left = 0
        var ans = Int.MAX_VALUE
        var sum = 0
        // 枚举右端点
        for (right in nums.indices) {
            sum += nums[right]
            while (sum >= target) {
                ans = min(ans, right - left + 1)
                sum -= nums[left]
                left++
            }
        }
        return ans.takeIf { it <= nums.size } ?: 0
    }
}