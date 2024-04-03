package al.sort.merge

/**
 * 让数组在 L-M 有序，在
 */

class RecursiveMergeSort {

    fun mergeSort(arr: IntArray) {
        if (arr.size < 2) return
        mergeSort(arr, 0, arr.lastIndex)
    }

    private fun mergeSort(arr: IntArray, start: Int, end: Int) {
        if (start == end) {
            return
        }

        val mid = start + ((end - start) shr 1)

        mergeSort(arr, start, mid)
        mergeSort(arr, mid + 1, end)

        merge(arr, start, mid, end)
    }

}

private fun merge(arr: IntArray, start: Int, mid: Int, end: Int) {
    val help = IntArray(end - start + 1)

    var leftIndex = start
    var rightIndex = mid + 1
    var helpIndex = 0

    while (leftIndex <= mid && rightIndex <= end) {
        // 相等时应该取左边元素，保证排序稳定性
        // 但是 merge 的具体应用时，可能无法保证稳定性
        if (arr[leftIndex] <= arr[rightIndex]) {
            help[helpIndex++] = arr[leftIndex++]
        } else {
            help[helpIndex++] = arr[rightIndex++]
        }
    }

    while (leftIndex <= mid) {
        help[helpIndex++] = arr[leftIndex++]
    }

    while (rightIndex <= end) {
        help[helpIndex++] = arr[rightIndex++]
    }

    for (i in start..end) {
        arr[i] = help[i - start]
    }
}

class IterativeMergeSort {

    /**
     * 步长依次为 1，2，4，8 来
     */
    fun mergeSort(arr: IntArray) {
        if (arr.size < 2) return

        var mergeSize = 1
        while (mergeSize < arr.size) {
            for (l in arr.indices step mergeSize * 2) {
                val r = (l + mergeSize * 2 - 1).coerceAtMost(arr.lastIndex)
                val mid = (l + mergeSize - 1).coerceAtMost(arr.lastIndex) // mid 必须为上一次步长的 r

                merge(arr, l, mid, r)
            }

            mergeSize = mergeSize shl 1
        }
    }
}