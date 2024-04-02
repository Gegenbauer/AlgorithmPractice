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

    var index1 = start
    var index2 = mid + 1
    var helpIndex = 0

    while (index1 <= mid && index2 <= end) {
        if (arr[index1] < arr[index2]) {
            help[helpIndex++] = arr[index1]
            index1++
        } else {
            help[helpIndex++] = arr[index2]
            index2++
        }
    }

    while (index1 <= mid) {
        help[helpIndex++] = arr[index1++]
    }

    while (index2 <= end) {
        help[helpIndex++] = arr[index2++]
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

    private fun merge(arr: IntArray, l: Int, mid: Int, r: Int) {
        val help = IntArray(r - l + 1)
        var helpIndex = 0
        var leftIndex = l
        var rightIndex = mid + 1

        while (leftIndex <= mid && rightIndex <= r) {
            help[helpIndex++] = if (arr[leftIndex] < arr[rightIndex]) {
                arr[leftIndex++]
            } else {
                arr[rightIndex++]
            }
        }

        while (leftIndex <= mid) {
            help[helpIndex++] = arr[leftIndex++]
        }

        while (rightIndex <= r) {
            help[helpIndex++] = arr[rightIndex++]
        }

        for (i in help.indices) {
            arr[l + i] = help[i]
        }
    }
}