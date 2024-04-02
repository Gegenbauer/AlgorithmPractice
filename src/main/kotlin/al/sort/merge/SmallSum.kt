package al.sort.merge

/**
 * 小和的定义，就是每个数左边比它小的数累加的和，整体再计算累加和。
 */
class SmallSum {

    /**
     * f(n) = f (n - 1) + smallSum(N)
     */
    fun smallSum(arr: IntArray): Int {
        if (arr.size < 2) {
            return 0
        }
        return smallSum(arr, 0, arr.lastIndex)
    }

    private fun smallSum(arr: IntArray, l: Int, r: Int): Int {

        if (l == r) {
            return 0
        }

        val mid = l + ((r - l) shr 1)

        val leftSmallSum = smallSum(arr, l, mid)
        val rightSmallSum = smallSum(arr, mid + 1, r)

        return leftSmallSum + rightSmallSum + mergeWithSmallSum(arr, l, mid, r)
    }

    /**
     * merge 的同时返回左边区间相对于右边区间的小和
     * 即对于左边区间的 arr[i] * 右边区间大于他的数的数目
     */
    private fun mergeWithSmallSum(arr: IntArray, l: Int, mid: Int, r: Int): Int {
        val help = IntArray(r - l + 1)
        var helpIndex = 0
        var index1 = l
        var index2 = mid + 1
        var smallSum = 0

        // 如果是左边的数
        while (index1 <= mid && index2 <= r) {
            // 如果右边数等于左边数，先拷贝右边的
            // 如果拷贝左边的，你不知道右边有多少个数比这个数大
            help[helpIndex++] = if (arr[index1] < arr[index2]) {
                smallSum += arr[index1] * (r - index2 + 1)
                arr[index1++]
            } else {
                arr[index2++]
            }
        }

        while (index1 <= mid) {
            smallSum += arr[index1] * (r - index2 + 1)
            help[helpIndex++] = arr[index1++]
        }

        while (index2 <= r) {
            help[helpIndex++] = arr[index2++]
        }

        for (i in help.indices) {
            arr[l + i] = help[i]
        }

        return smallSum
    }
}