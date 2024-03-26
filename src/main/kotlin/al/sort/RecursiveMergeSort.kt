package al.sort

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

    fun mergeSort(arr: IntArray) {

    }
}