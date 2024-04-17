package al.others

import java.util.*
import kotlin.math.abs
import kotlin.math.min

/**
 * leetcode-15 三数之和
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
class SumOfThreeNumbers {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()
        Arrays.sort(nums)
        val result = hashMapOf<String, List<Int>>()
        for (cur in nums.indices) {
            if (cur > 0 && nums[cur] == nums[cur - 1]) continue
            var l = 0
            if (l == cur) l++
            var r = nums.lastIndex
            if (r == cur) r--
            while (l < r) {
                val sum = nums[l] + nums[r] + nums[cur]
                if (sum == 0) {
                    val triple = listOf(nums[l], nums[r], nums[cur]).sorted()
                    result[triple.joinToString()] = triple
                    // 考虑到不能取重复的组合，所以 l 和 r 都需要更新
                    l++
                    if (l == cur) l++
                    r--
                    if (r == cur) r--
                } else if (sum > 0) {
                    r--
                    if (r == cur) r--
                } else {
                    l++
                    if (l == cur) l++
                }
            }
        }
        return result.values.toList()
    }

    /**
     * leetcode-16 最接近的三数之和
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()

        fun getL(target: Int, cur: Int): Int {
            return if (target == cur) {
                target + 1
            } else {
                target
            }
        }

        fun getR(target: Int, cur: Int): Int {
            return if (target == cur) {
                target - 1
            } else {
                target
            }
        }

        var result = Int.MAX_VALUE
        for (cur in nums.indices) {
            var l = getL(0, cur)
            var r = getR(nums.lastIndex, cur)
            while (l < r) {
                val sum = nums[l] + nums[cur] + nums[r]
                result = if (abs(sum - target) < abs(result - target)) {
                    sum
                } else {
                    result
                }
                if (sum < target) {
                    l = getL(l + 1, cur)
                } else if (sum > target) {
                    r = getR(r - 1, cur)
                } else {
                    return target
                }
            }
        }
        return result
    }
}

fun main() {
    println(SumOfThreeNumbers().threeSumClosest(intArrayOf(0, 1, 2), 3))
}