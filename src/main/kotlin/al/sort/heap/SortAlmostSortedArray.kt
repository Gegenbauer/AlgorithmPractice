package al.sort.heap

import java.util.PriorityQueue

class SortAlmostSortedArray {

    /**
     * 对于一个几乎有序的数组
     * 几乎有序的定义就是，每个元素离排序完它应该在的位置的距离不超过 k
     * 即最小的数一定在前 k 个数内。
     *
     * 通过维护一个 k 个元素的小根堆来实现。
     */
    fun sortAlmostSortedArray(arr: IntArray, k: Int) {
        if (arr.size < 2) return

        val smallHeap = PriorityQueue<Int>(k)
        for (i in 0 until k) {
            smallHeap.add(arr[i])
        }

        for (i in k until arr.size) {
            arr[i - k] = smallHeap.poll()
            smallHeap.add(arr[i])
        }

        for (i in arr.size - k until arr.size) {
            arr[i] = smallHeap.poll()
        }
    }
}