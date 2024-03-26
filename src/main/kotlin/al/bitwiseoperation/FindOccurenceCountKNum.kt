package al.bitwiseoperation

class FindOccurrenceCountKNum {

    /**
     * leetcode-137 只出现一次的数字 II
     *
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,2,3,2]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [0,1,0,1,0,1,99]
     * 输出：99
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     */
    fun singleNumber(nums: IntArray): Int {
        return findOccurrenceCountKNum(nums, 1, 3)
    }

    /**
     * 数组内所有数都出现了 m 次，只有一个数出现了 k 次，且满足 m > 1, k < m
     * 找到出现了 k 次的数
     * 要求 额外空间复杂度 O(1), 时间复杂度 O(N)
     */
    fun findOccurrenceCountKNum(nums: IntArray, k: Int, m: Int): Int {
        val bitCounter = IntArray(32)
        for (i in 0..31) {
            nums.forEach {
                if ((it shr i) and 1 > 0) {
                    bitCounter[i]++
                }
            }
        }

        var result = 0

        bitCounter.forEachIndexed { index, cur ->
            if (cur % m == k) {
                result = result or (1 shl index)
            }
        }

        return if (result == 0) -1 else result
    }

    /**
     * 切记提取有符号整数二进制表示的某一位的值时，不能使用 (a & (1 << n)) > 0 的方式
     * 如果刚好提取的是最高位，则必然得到的是负数，即使这一位提取后是 1
     */
    private fun findOccurrenceCountKNum2(nums: IntArray, k: Int, m: Int): Int {
        val bitCounter = IntArray(32)
        for (i in 0..31) {
            val bitExtractor = 1 shl i
            nums.forEach {
                if (it and bitExtractor > 0) {
                    bitCounter[i]++
                }
            }
        }

        var result = 0

        bitCounter.forEachIndexed { index, cur ->
            if (cur % m == k) {
                result = result or (1 shl index)
            }
        }

        return result
    }
}