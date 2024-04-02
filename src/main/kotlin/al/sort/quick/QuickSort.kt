package al.sort.quick

import al.sort.simple.swap
import java.util.*
import kotlin.random.Random

class QuickSort {

    /**
     * 时间复杂度最差情况是 O(N ^ 2)--排好序，且无重复数字，最好情况是 O(N * logN)
     * 额外空间复杂度是 O(logN)，每次递归需要记录相等元素的区间范围。
     */
    fun sort(arr: IntArray) {
        if (arr.size < 2) return

        sort(arr, 0, arr.lastIndex)
    }

    private fun sort(arr: IntArray, start: Int, end: Int) {

        if (start >= end) {
            return
        }

        val interval = partition(arr, start, end)
        sort(arr, start, interval.first - 1)
        sort(arr, interval.second + 1, end)
    }

    // 返回等于目标值的区间
    private fun partition(arr: IntArray, start: Int, end: Int): Pair<Int, Int> {
        var smallBound = start - 1
        var largeBound = end
        val pivot = arr[end]
        var cur = start

        while (cur < largeBound) {
            if (arr[cur] < pivot) {
                swap(arr, ++smallBound, cur++)
            } else if (arr[cur] > pivot) {
                swap(arr, --largeBound, cur)
            } else {
                cur++
            }
        }

        swap(arr, largeBound, end) // 要记得把最后一个数交换到大于区域的第一个值
        return smallBound + 1 to largeBound
    }

    /**
     * 随机取基准点，避免最差情况
     */
    fun sort2(arr: IntArray) {
        if (arr.size < 2) return

        sort2(arr, 0, arr.lastIndex)
    }

    private fun sort2(arr: IntArray, start: Int, end: Int) {

        if (start >= end) {
            return
        }

        swap(arr, Random.nextInt(start, end), end)
        val interval = partition(arr, start, end)
        sort(arr, start, interval.first - 1)
        sort(arr, interval.second + 1, end)
    }

    /**
     * 迭代版本
     */
    fun sort3(arr: IntArray) {
        if (arr.size < 2) return

        val ops = Stack<Op>()
        swap(arr, Random.nextInt(0, arr.lastIndex), arr.lastIndex)
        val initialInterval = partition(arr, 0, arr.lastIndex)
        ops.push(Op(0, initialInterval.first - 1))
        ops.push(Op(initialInterval.second + 1, arr.lastIndex))

        while (ops.isNotEmpty()) {
            val op = ops.pop()
            if (op.start >= op.end) continue
            swap(arr, Random.nextInt(op.start, op.end), op.end)
            val interval = partition(arr, op.start, op.end)
            if (interval.second > interval.first) {
                ops.push(Op(op.start, interval.first - 1))
                ops.push(Op(interval.second + 1, op.end))
            }
        }
    }

    class Op (
        val start: Int,
        val end: Int
    )
}