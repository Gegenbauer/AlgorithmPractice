package al.table

import kotlin.math.max

/**
 * leetcode-128 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
class LongestConsecutive {

    fun longestConsecutive(nums: IntArray): Int {
        if (nums.size < 2) {
            return nums.size
        }
        val set = mutableSetOf<Int>()
        nums.forEach { set.add(it) }
        var ans = 1
        for (i in nums.indices) {
            if ((nums[i] - 1) in set) continue
            var start = nums[i] + 1
            while (start in set) {
                start++
            }
            ans = max(ans, start - nums[i])
        }
        return ans
    }
}