package al.backtrack

import java.util.*

/**
 * leetcode-46 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]

 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数互不相同
 */
class Permutation {

    /**
     * 只能用于无重复元素的数组
     * 整个流程时间复杂度为 O(n! * n ^ 2)
     * 额外空间复杂度 O(n)，为栈的深度
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        backtrack(nums, mutableListOf(), result)
        return result
    }

    /**
     * 单次回溯时间复杂度为 O(n ^ 2)
     */
    private fun backtrack(nums: IntArray, permutation: MutableList<Int>, result: MutableList<List<Int>>) {
        if (permutation.size == nums.size) {
            result.add(permutation.toList())
            return
        }
        // 递归层数越深，列表内元素越多
        // 而每层递归，是在遍历列表内第 n 个元素的各种选择
        for (num in nums) { // O(N)
            if (num in permutation) continue // O(N)

            permutation.add(num)
            backtrack(nums, permutation, result)
            permutation.remove(num)
        }
    }

    /**
     * 通过备忘录来记录访问过的元素，避免重复访问
     * 时间复杂度 O(n! * n)
     * 额外空间复杂度 O(n) 为 栈的深度 + 访问记录
     */
    fun permute2(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val visitRecord = BooleanArray(nums.size)
        backtrack2(nums, LinkedList(), visitRecord, result)
        return result
    }

    private fun backtrack2(
        nums: IntArray,
        permutation: LinkedList<Int>,
        visitRecord: BooleanArray,
        result: MutableList<List<Int>>
    ) {
        if (permutation.size == nums.size) {
            result.add(permutation.toList())
            return
        }

        for ((index, num) in nums.withIndex()) {
            if (visitRecord[index]) continue

            permutation.offer(num)
            visitRecord[index] = true
            backtrack2(nums, permutation, visitRecord, result)
            permutation.removeLast()
            visitRecord[index] = false
        }
    }

    /**
     * 如果数组可能存在重复元素
     * 如果不采取措施，选取第 n 个数时，如果两次选取了不同位置但相同的数，就会导致相同的排列
     * 所以需要保证同一个位置，选取数时不能选取重复数字
     *
     * leetcode-47 全排列 II
     */
    fun permuteArrayWithDuplicates(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        nums.sort()
        val visited = BooleanArray(nums.size)
        backtrackWithDuplicates(nums, LinkedList(), visited, result)
        return result
    }

    private fun backtrackWithDuplicates(
        nums: IntArray,
        temp: LinkedList<Int>,
        visited: BooleanArray,
        result: MutableList<List<Int>>
    ) {
        if (temp.size == nums.size) {
            result.add(temp.toList())
            return
        }
        for ((index, num) in nums.withIndex()) {
            // 之前填过的数字直接跳过
            if (visited[index]) continue

            // 保证重复数字只会填一次
            if (index > 0 && !visited[index - 1] && nums[index] == nums[index - 1]) continue
            visited[index] = true
            temp.offer(num)
            backtrackWithDuplicates(nums, temp, visited, result)
            temp.removeLast()
            visited[index] = false
        }
    }

    fun permute4(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = LinkedList<List<Int>>()
        backtrack4(nums, 0, result)
        return result
    }

    private fun backtrack4(nums: IntArray, cur: Int, result: MutableList<List<Int>>) {
        if (cur == nums.size) {
            result.add(nums.toList())
            return
        }
        // 使用过的数不能再使用，避免重复
        val set = hashSetOf<Int>()
        for (i in cur until nums.size) {
            if (nums[i] in set) continue
            set.add(nums[i])
            swap(nums, cur, i)
            backtrack4(nums, cur + 1, result)
            swap(nums, cur, i)
        }
    }

    private fun swap(nums: IntArray, a: Int, b: Int) {
        val temp = nums[a]
        nums[a] = nums[b]
        nums[b] = temp
    }
}