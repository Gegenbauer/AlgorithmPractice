package al.binarysearch

/**
 * leetcode-34 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    val biggestValueSmallerThan = findBiggestValueSmallerThan(nums, target)
    val smallestValueLargerThan = findSmallestValueLargerThan(nums, target)

    if (biggestValueSmallerThan < 0 || smallestValueLargerThan < 0) {
        return intArrayOf(-1, -1)
    }

    val start = if (nums[biggestValueSmallerThan] == target) {
        biggestValueSmallerThan
    } else {
        biggestValueSmallerThan + 1
    }

    val end = if (nums[smallestValueLargerThan] == target) {
        smallestValueLargerThan
    } else {
        smallestValueLargerThan - 1
    }

    if (start > end) return intArrayOf(-1, -1)

    return intArrayOf(start, end)
}

fun findBiggestValueSmallerThan(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1
    var mid: Int
    var ans: Int = -1
    while (left <= right) {
        mid = left + ((right - left) shr 1)

        if (arr[mid] > target) {
            right = mid - 1
        } else if (arr[mid] < target) {
            ans = mid
            left = mid + 1
        } else {
            ans = mid
            right = mid - 1
        }
    }
    return ans
}

fun findSmallestValueLargerThan(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1
    var mid: Int
    var ans: Int = -1
    while (left <= right) {
        mid = left + ((right - left) shr 1)

        if (arr[mid] > target) {
            ans = mid
            right = mid - 1
        } else if (arr[mid] < target) {
            left = mid + 1
        } else {
            ans = mid
            left = mid + 1
        }
    }
    return ans
}