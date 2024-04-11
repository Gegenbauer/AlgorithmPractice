package al.greedy

import java.util.PriorityQueue
import kotlin.math.min

class MinCostOfGoldSplit {

    /**
     * 给定一个整型数组，和一块黄金，总质量为整型数组的累加和
     * 要求将黄金按照整型数组每个元素取值划分成 n 份
     * 每次划分需要划分当前黄金重量的金额
     * 求划分的最小花费
     *
     * 准备一个小根堆，将所有元素放入小根堆
     * 每次弹出两个元素，其和就是代价，然后将总代价放入堆
     *
     * 和哈夫曼编码原理相似
     */
    fun minCost(arr: IntArray): Int {
        val minHeap = PriorityQueue<Int>()
        arr.forEach { minHeap.offer(it) }
        var ans = 0
        while (minHeap.size > 1) {
            val cost = minHeap.poll() + minHeap.poll()
            ans += cost
            minHeap.offer(cost)
        }

        return ans
    }

    /**
     * 暴力枚举
     * 需要将每次分割转化为数组元素之间的合并
     */
    fun bruteForce(arr: IntArray): Int {
        return process(arr, 0)
    }

    private fun process(arr: IntArray, minCost: Int): Int {
        if (arr.size == 1) {
            return minCost
        }

        // result 为第 n 次合并的最佳结果
        // 将会作为结果返回到第 n - 1 次合并
        var result = minCost
        for (i in arr.indices) {
            for (j in i until arr.size) {
                result = min(result, process(mergeAndCopy(arr, i, j), minCost + arr[i] + arr[j]))
            }
        }
        return result
    }

    private fun mergeAndCopy(arr: IntArray, i: Int, j: Int): IntArray {
        val result = IntArray(arr.size - 1)
        var resultIndex = 0
        for (index in arr.indices) {
            if (index == i || index == j) continue
            result[resultIndex++] = arr[index]
        }
        result[resultIndex] = arr[i] + arr[j]
        return result
    }
}