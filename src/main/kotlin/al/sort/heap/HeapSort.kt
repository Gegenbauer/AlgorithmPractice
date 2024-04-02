package al.sort.heap

import al.sort.simple.swap

class HeapSort {

    /**
     * 1. 首先构建大根堆
     * 2. 将根节点与最后一个节点交换，即最大值放到了数组最后。然后根大小减一
     * 3. 检查堆的有效性
     *
     * 时间复杂度 O(N * logN)
     * 额外空间复杂度 O(1)
     *
     * 建堆有两种方法
     * 1. 从上往下建堆，O(N * logN)
     * 2. 从下往上建堆，O(N)
     *
     *
     * 堆排序对于一个几乎有序的数组，效率更高
     */
    fun sort(arr: IntArray) {
        if (arr.size < 2) return

        // O(N * logN)
        for (i in arr.indices) { // O(N)
            heapInsert(arr, i) // O(logN)
        }

        var size = arr.size
        // O(N * logN)
        while (size > 0) { // O(N)
            swap(0, (size--) - 1, arr) // O(1)
            checkChildren(arr, 0, size) // O(logN)
        }
    }

    fun sort2(arr: IntArray) {
        if (arr.size < 2) return

        // O(N * logN)
        for (i in arr.lastIndex downTo 0) { // O(N)
            checkChildren(arr, i, arr.size) // O(logN)
        }

        var size = arr.size
        // O(N * logN)
        while (size > 0) { // O(N)
            swap(0, (size--) - 1, arr) // O(1)
            checkChildren(arr, 0, size) // O(logN)
        }
    }

    private fun heapInsert(arr: IntArray, size: Int) {
        checkParent(arr, size)
    }

    private fun checkParent(arr: IntArray, node: Int) {
        var cur = node

        while (cur > 0) {
            val parent = (cur - 1) / 2
            if (arr[cur] > arr[parent]) {
                swap(cur, parent, arr)
                cur = parent
            } else {
                break
            }
        }
    }

    private fun checkChildren(arr: IntArray, node: Int, size: Int) {
        var cur = node

        while (cur < size) {
            val left = cur * 2 + 1
            if (left >= size) break
            val right = left + 1
            val maxChild = if (right >= size) { // 选出较大的子节点
                left
            } else if (arr[right] > arr[left]) {
                right
            } else {
                left
            }
            if (arr[maxChild] > arr[cur]) { // 如果子节点更大，则交换
                swap(maxChild, cur, arr)
                cur = maxChild
            } else {
                break
            }
        }
    }
}