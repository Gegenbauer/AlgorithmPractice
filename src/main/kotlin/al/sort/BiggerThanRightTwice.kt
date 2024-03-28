package al.sort

class BiggerThanRightTwice {

    fun biggerTwice(arr: IntArray): Int {
        return biggerTwice(arr, 0, arr.lastIndex)
    }

    private fun biggerTwice(arr: IntArray, start: Int, end: Int): Int {
        if (start == end) {
            return 0
        }

        val mid = start + ((end - start) shr 1)
        val left = biggerTwice(arr, start, mid)
        val right = biggerTwice(arr, mid + 1, end)

        return left + right + merge(arr, start, mid, end)
    }

    private fun merge(arr: IntArray, start: Int, mid: Int, end: Int): Int {

        var count = 0
        // 计算对于左侧每个元素，右侧元素中有多少满足条件的
        // 以左侧元素为参照物，所以外层是遍历左侧元素
        var j = mid + 1
        for (i in start..mid) {
            while (j <= end && (arr[i] > arr[j] shl 1)) {
                j++
            }
            count += (j - 1 - mid - 1 + 1).coerceAtLeast(0)
        }

        val help = IntArray(end - start + 1)
        var helpIndex = 0
        var index1 = start
        var index2 = mid + 1

        while (index1 <= mid && index2 <= end) {
            help[helpIndex++] = if (arr[index1] < arr[index2]) {
                arr[index1++]
            } else {
                arr[index2++]
            }
        }

        while (index1 <= mid) {
            help[helpIndex++] = arr[index1++]
        }

        while (index2 <= end) {
            help[helpIndex++] = arr[index2++]
        }

        for (i in help.indices) {
            arr[i + start] = help[i]
        }
        return count
    }
}