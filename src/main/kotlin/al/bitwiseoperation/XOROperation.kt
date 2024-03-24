package al.bitwiseoperation

/**
 * 0 ^ N = N
 * N ^ N = 0
 * N ^ M = M ^ N 交换律
 * N ^ (M ^ C) = (N ^ M) ^ C 结合律
 * 即：同样一批数，无论按怎样的顺序进行异或，最终结果都相同 （也可以通过无进位相加来推导）
 */
class XOROperation {

    fun swap(arr: IntArray, a: Int, b: Int) {
        require(a != b) { "a must not be equals to b" }
        arr[a] = arr[a] xor arr[b]
        arr[b] = arr[a] xor arr[b]
        arr[a] = arr[a] xor arr[b]
    }

    // leetcode 136. 如果一个数组中，一个数出现奇数次，其余数都出现了偶数次
    fun singleNumber(nums: IntArray): Int {
        return nums.reduce { acc, i ->
            acc xor i
        }
    }

    /**
     * a            = 01101110010000
     * ~a           = 10010001101111
     * ~a + 1       = 10010001110000
     * a & (~a + 1) = 00000000010000
     * 也就是 a & (-a)
     * -a 在计算机中的二进制表示就是 ~a + 1
     * 计算机中是以补码表示二进制数的，正数的补码就是二进制数本身
     * 负数的补码是对应正数的二进制数全部取反，再加一
     */
    fun extractLastNonZeroBit(value: Int): Int {
        return value and (value.inv() + 1)
    }

    /**
     * leetcode 260. 只出现一次的数字 III
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     *
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * 示例 2：
     *
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     * 示例 3：
     *
     * 输入：nums = [0,1]
     * 输出：[1,0]
     *
     *
     * 提示：
     *
     * 2 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
     */
    fun singleTwoNumber(nums: IntArray): IntArray {

        fun getXorAcc(nums: IntArray): Int {
            return nums.reduce { acc: Int, i: Int -> acc xor i }
        }

        val eor = getXorAcc(nums)
        val lastNonZeroBitNum = eor and -eor // a 和 b 的这一位一定不同

        // 根据这一位，将数分为两组，分别异或即可。
        val group1 = nums.filter { it and lastNonZeroBitNum == 0 }.toIntArray()
        val group2 = nums.filter { it and lastNonZeroBitNum != 0 }.toIntArray()

        return intArrayOf(getXorAcc(group1), getXorAcc(group2))
    }
 }