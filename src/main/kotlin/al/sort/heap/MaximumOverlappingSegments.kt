package al.sort.heap

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 最大线段重合数
 *
 * 给定很多线段，每个线段都有两个数 [start, end], 表示线段开始位置和结束位置
 * 左右都是闭区间
 * 规定：
 * 1) 线段的开始和结束位置一定都是整数值
 * 2) 线段重合区域的长度必须 >= 1，只重合一个点不满足要求
 * 返回线段最多重合区域中，包含了几条线段
 *
 * [1, 2] 和 [2, 4] 没有重合区域
 *
 * 暴力解法
 * 1. 求出所有线段中的最小值，和最大值。
 * 2. 遍历 min + 0.5, min + 1 + 0.5, 直到到 max
 * 3. 计算 包含以上点的线段数，则最大的值就是结果。
 * 时间复杂度 O((max - min) * N)
 *
 * 堆解法：
 * 1. 将线段按起始点排序
 * 2. 维护一个小根堆，遍历每个线段
 * 弹出堆中小于等于线段左边界的值，并将线段右边界放入堆
 * 3. 此时堆中剩余的值的数量，就表示以这个线段左边界为界，有多少条线段穿过这个边界
 * 也就是包含这个线段左边界的重合线段数
 *
 */
class MaximumOverlappingSegments {

    /**
     * 线段数量 n，线段最大
     * 整体时间复杂度 O(N * logN)
     */
    fun maxCover(lines: Array<Array<Int>>): Int {
        lines.sortedBy { it[0] } // 排序：O(N * logN)

        var ans = 0
        val minHeap = PriorityQueue<Int>()
        lines.forEach { // N
            while (minHeap.isNotEmpty() && minHeap.peek() <= it[0]) {
                minHeap.poll() // logN 整个过程最多执行 N 次
            }
            minHeap.add(it[1]) // logN
            ans = max(ans, minHeap.size)
        }
        return 0
    }
}