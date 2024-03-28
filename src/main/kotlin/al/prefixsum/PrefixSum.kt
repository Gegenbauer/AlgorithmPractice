package al.prefixsum

/**
 * 前缀和，是一种常用的算法技巧，用来计算数组或者列表中的区间和问题
 * 基本思想：
 * 是预处理出一个数组，
 * 使得每个位置上的元素值等于原数组中从第一个元素到当前位置的元素之和。
 * 这样，如果我们想要得到原数组中任意一个区间的和，只需要用一个简单的减法操作就可以得到。
 *
 * 例如，假设我们有一个数组 arr = [1, 2, 3, 4, 5]，
 * 我们可以计算出其前缀和数组 prefixSum = [1, 3, 6, 10, 15]。
 * 现在，如果我们想要得到原数组中从第2个元素到第4个元素的和，
 * 我们只需要计算 prefixSum[4] - prefixSum[1] = 15 - 3 = 12，就可以得到结果。
 *
 * 这里常遇到两种子数组的定义
 * 连续子数组，和子集（包含不联系）
 * 连续子数组的数目为，n + n - 1 + n - 2 + ... + 1 = n * (n + 1) / 2 (列举一个数、两个数、等的情况)
 * 子集的数目为，2 ^ n (每个数，存在或不存在)
 */
class PrefixSum {
}

/**
 * leetcode-303 区域和检索 - 数组不可变
 *
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 *
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *
 *
 * Your NumArray object will be instantiated and called as such:
 * var obj = NumArray(nums)
 * var param_1 = obj.sumRange(left,right)
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 */
class NumArray(nums: IntArray) {
    private val prefixSums = IntArray(nums.size + 1)

    init {
        for (i in 1 until prefixSums.size) {
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1]
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return prefixSums[right + 1] - prefixSums[left]
    }

}