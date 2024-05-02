package al.slidingwindow

/**
 * leetcode-560 和为 K 的子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
class SubArrSum {

    fun subarraySum(nums: IntArray, k: Int): Int {
        val prefixArray = IntArray(nums.size + 1)
        prefixArray[0] = 0
        for (i in nums.indices) {
            prefixArray[i + 1] = prefixArray[i] + nums[i]
        }
        // 两数之差
        var ans = 0
        for (start in prefixArray.indices) {
            for (end in (start + 1) until prefixArray.size) {
                if (prefixArray[end] - prefixArray[start] == k) {
                    ans++
                }
            }
        }
        return ans
    }

    fun subarraySum2(nums: IntArray, k: Int): Int {
        val prefixArray = IntArray(nums.size + 1)
        prefixArray[0] = 0
        for (i in nums.indices) {
            prefixArray[i + 1] = prefixArray[i] + nums[i]
        }
        // 两数之差
        // value : count
        // 遍历的时候记录该值出现的次数，这样能避免将当前索引的值也计算入内
        val map = hashMapOf<Int, Int>()
        var ans = 0
        for (end in prefixArray.indices) {
            val prefixEnd = prefixArray[end]
            val prefixStart = prefixEnd - k
            ans += map[prefixStart] ?: 0
            map[prefixEnd] = (map[prefixEnd] ?: 0) + 1
        }
        return ans
    }
}