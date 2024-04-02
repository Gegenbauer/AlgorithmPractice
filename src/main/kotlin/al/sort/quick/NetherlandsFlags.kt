package al.sort.quick

import al.sort.simple.swap

/**
 * 荷兰国旗问题
 * 有两种类型
 * 1) 以 x 划分数组，小于等于 x 的放左边，大于 x 的放右边 (不要求有序)
 * => 单指针，维护小于目标值的区域
 *
 * 2) 以 x 划分数组，小于 x 的放左边，等于 x 的放中间，大于 x 的放右边 (不要求有序)
 * => 双指针，分别维护小于和大于目标值的区域
 *
 * **切记 大于目标值的数与大于区交换时，当前值不变，因为新交换的值还没检查。
 * 而小于目标值的数与大于区交换时，当前值可以加一，因为新交换的值是已经检查过的。
 *
 * 要求不用辅助数组，时间复杂度 O(N)
 */
class NetherlandsFlags {

    /**
     * leetcode-75 颜色分类
     *
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * 示例 2：
     *
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     *
     *
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] 为 0、1 或 2
     *
     *
     * 进阶：
     *
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
     *
     *
     * 注意点，左侧指针指向小于区域的最后一个值，右侧指针指向大于区域的第一个值
     * 交换前，小于区域需要先后移，大于区域需要先前移
     */
    fun sortColors(nums: IntArray): Unit {
        if (nums.size < 2) return

        var smallBound = -1
        var largeBound = nums.size
        var cur = 0

        while (cur < largeBound) {
            if (nums[cur] < 1) {
                swap(nums, cur++, ++smallBound)
            } else if (nums[cur] > 1) {
                swap(nums, cur, --largeBound)
            } else {
                cur++
            }
        }
    }
}