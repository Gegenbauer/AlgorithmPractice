package al.sort.heap

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

/**
 * leetcode-56 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
class MergeIntervals {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size < 2) return intervals
        val result = mutableListOf<IntArray>()
        val sortedIntervals = PriorityQueue<IntArray> { o1, o2 -> o1[0] - o2[0] }
        intervals.forEach { sortedIntervals.offer(it) }

        var cur = sortedIntervals.poll()
        while (sortedIntervals.isNotEmpty()) {
            val next = sortedIntervals.peek()
            if (next[0] <= cur[1]) {
                // 可以合并
                cur = merge(cur, next)
                sortedIntervals.poll()
            } else {
                // 不可以合并，独立区间
                result.add(cur)
                cur = sortedIntervals.poll()
            }
        }
        result.add(cur)
        return result.toTypedArray()
    }

    private fun merge(interval1: IntArray, interval2: IntArray): IntArray {
        val start = min(interval1[0], interval2[0])
        val end = max(interval1[1], interval2[1])
        return intArrayOf(start, end)
    }
}