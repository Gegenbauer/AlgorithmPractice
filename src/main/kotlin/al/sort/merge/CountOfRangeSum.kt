package al.sort.merge

class CountOfRangeSum {

    /**
     * leetcode-327 区间和的个数
     *
     * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，
     * 值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     *
     * 示例 1：
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     *
     * 示例 2：
     * 输入：nums = [0], lower = 0, upper = 0
     * 输出：1
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * -10^5 <= lower <= upper <= 10^5
     * 题目数据保证答案是一个 32 位 的整数
     *
     * 问题可以转化为，记以第 i 个数结尾的区间和为 S(i)
     * 则如果 S(j) 的取值范围在 [S(i) - lower, S(i) - upper]，则区间 [j+1, i] 的区间和满足要求
     * 也就是需要求对于每个 S(i)，有多少个 S(j) 的取值范围在 [S(i) - lower, S(i) - upper]，j <= i
     */
    fun countRangeSum(nums: IntArray, lower: Int, upper: Int): Int {
        val prefixSums = getPrefixSums(nums)

        return countRangeSum(prefixSums, 0, prefixSums.lastIndex, lower, upper)
    }

    private fun countRangeSum(nums: LongArray, start: Int, end: Int, lower: Int, upper: Int): Int {
        if (start == end) {
            return if (nums[start] in lower..upper) 1 else 0
        }

        val mid = start + ((end - start) shr 1)
        val left = countRangeSum(nums, start, mid, lower, upper)
        val right = countRangeSum(nums, mid + 1, end, lower, upper)

        return left + right + merge(nums, start, mid, end, lower, upper)
    }

    /**
     * # 滑动窗口的方式
     * 1、常是先固定左边界，扩大窗口，直到满足条件
     * 2、然后固定右边界，缩小窗口，直到条件刚好不满足
     * 关键是窗口的更新，和维护。因为往往在维护和更新窗口的时候，都需要借助其他数据结构和算法思想来操作
     *
     * # 窗口表达法有两种，通常使用第二种表示方式，因为这样可以通过 [L, L) 来表示区间内无满足条件的值，边界条件更好处理
     * [L, R] 或 [L, R)
     */
    private fun merge(nums: LongArray, start: Int, mid: Int, end: Int, lower: Int, upper: Int): Int {
        var ans = 0
        var windowL = start
        var windowR = start

        // 窗口不回退，所以时间复杂度仍然是 O(N)
        // TODO 左闭右开结果正确，但是左闭右闭结果有问题，原因暂时未知
        for (i in (mid + 1)..end) {
            val min = nums[i] - upper
            val max = nums[i] - lower
            // 求一个有序数组中，值在指定区间内的数量
            while (windowL <= mid && nums[windowL] < min) {
                windowL++
            }
            while (windowR <= mid && nums[windowR] <= max) {
                windowR++
            }

            ans += windowR - windowL
        }

        val help = LongArray(end - start + 1)
        var helpIndex = 0

        var right = mid + 1
        var left = start

        while (right <= end && left <= mid) {
            help[helpIndex++] = if (nums[left] < nums[right]) {
                nums[left++]
            } else {
                nums[right++]
            }
        }

        while (right <= end) {
            help[helpIndex++] = nums[right++]
        }

        while (left <= mid) {
            help[helpIndex++] = nums[left++]
        }

        for (i in help.indices) {
            nums[i + start] = help[i]
        }

        return ans
    }

    private fun getPrefixSums(nums: IntArray): LongArray {
        val result = LongArray(nums.size)
        result[0] = nums[0].toLong()

        for (i in 1 until result.size) {
            result[i] = result[i - 1] + nums[i]
        }

        return result
    }
}