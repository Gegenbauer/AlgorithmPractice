package al.sort.heap

import java.util.PriorityQueue
import kotlin.random.Random

/**
 * leetcode-215 数组中的第K个最大元素
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
class FindKBiggest {

    /**
     * 最小堆
     * 如果堆的大小没到 k，则添加元素
     * 如果堆的大小到了 k，则检查新的元素是否比堆顶元素大，如果大，则移除堆顶元素，添加当前元素
     * 这样堆顶元素一直是当前第 k 大的元素
     * 时间复杂度 O(N * logN)
     */
    fun findKthLargest2(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>()
        nums.forEach {
            if (minHeap.size < k) {
                minHeap.offer(it)
            } else {
                if (it > minHeap.peek()) {
                    minHeap.poll()
                    minHeap.offer(it)
                }
            }
        }
        return minHeap.peek()
    }

    /**
     * 快速选择，利用随机选取基准的方式，来使得平均时间复杂度到达 O(N)
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        quickSelect(nums, 0, nums.lastIndex, nums.size - k)
        return nums[nums.size - k]
    }

    /**
     * partition 的结果区间如果包含 targetIndex，则停止递归
     */
    private fun quickSelect(nums: IntArray, start: Int, end: Int, targetIndex: Int): Int {
        if (start >= end) {
            return -1
        }

        val partitionResult = partition(nums, start, end)
        if (targetIndex in partitionResult.from..partitionResult.to) {
            return 1
        }
        val left = quickSelect(nums, start, partitionResult.from - 1, targetIndex)
        if (left > 0) {
            return 1
        }
        val right = quickSelect(nums, partitionResult.to + 1, end, targetIndex)
        if (right > 0) {
            return 1
        }
        return -1
    }

    private fun partition(nums: IntArray, start: Int, end: Int): PartitionResult {
        val baseIndex = Random.nextInt(start, end + 1)
        swap(nums, baseIndex, end)
        val baseValue = nums[end]
        var smallerBound = start - 1
        var largerBound = end
        var cur = start
        while (cur < largerBound) {
            if (nums[cur] < baseValue) {
                swap(nums, cur++, ++smallerBound)
            } else if (nums[cur] > baseValue) {
                swap(nums, cur, --largerBound)
            } else {
                cur++
            }
        }
        swap(nums, end, largerBound)
        return PartitionResult(smallerBound + 1, largerBound)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    data class PartitionResult(
        val from: Int,
        val to: Int
    )
}