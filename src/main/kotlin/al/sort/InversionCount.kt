package al.sort

/**
 * leetcode LCR-170 交易逆序对的总数
 *
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入：record = [9, 7, 5, 4, 6]
 * 输出：8
 * 解释：交易中的逆序对为 (9, 7), (9, 5), (9, 4), (9, 6), (7, 5), (7, 4), (7, 6), (5, 4)。
 *
 *
 * 限制：
 *
 * 0 <= record.length <= 50000
 */
class InversionCount {

    /**
     * 可以用归并来解的问题：右组有多少个数相对于左组是达标的，或者左组有多少个数相对于右组是达标的
     *
     * 小和： sSum(i) 索引小于 i 且值小于 a(i) 的数的和，再求 sSum(0)...sSum(n) 累加和
     * 逆序对数： iv(i) 索引小于 i 且值大于 a(i) 的数的数目，再求 iv(0)...iv(n) 的累加和
     *
     * 对于这类题统一转化为，对于 a(i), 索引小于 i 且满足一定条件 的某种和，再求累加和
     * 再转化为以左边的数为参照物，每次合并拷贝左边的数时，产生某种和
     * 遍历顺序是从左到右还是从右到左，要看拷贝左侧值时，能否知道右侧有多少值满足要求
     *
     * 另外，也可以先双指针遍历求结果，求完再进行 merge（需要满足的条件不是单纯的大小关系时）
     *
     * 归并的理念就是
     * 1. 将一个大的数组分割成两个数组，直到无法分割，或者可以轻易求出结果
     * 2. 求解两个最小数组的结果，顺便将两个最小数组按大小顺序排序
     * 3. 然后再回归到更大数组的求解，顺便利用已经排好序的子数组
     * 4. 最终求解原数组的结果
     */

    fun reversePairs(record: IntArray): Int {
        if (record.size < 2) return 0
        return reversePairs(record, 0, record.lastIndex)
    }

    private fun reversePairs(arr: IntArray, start: Int, end: Int): Int {
        if (start == end) {
            return 0
        }

        val mid = start + ((end - start) shr 1)

        val leftPairs = reversePairs(arr, start, mid)
        val rightPairs = reversePairs(arr, mid + 1, end)

        return leftPairs + rightPairs + merge(arr, start, mid, end)
    }

    /**
     * 1, 2
     * 3, 1
     * 1
     */
    private fun merge(arr: IntArray, start: Int, mid: Int, end: Int): Int {
        val help = IntArray(end - start + 1)
        var helpIndex = help.lastIndex
        var index1 = mid
        var index2 = end
        var reversePairs = 0

        // 因为如果从左往右拷贝，则左边的数拷贝的时候只有比右边的数小的时候
        // 这样导致不知道右边有多少数比左边小
        // 相等的时候需要拷贝右边的数，因为左边的数拷贝的时候需要知道右边有多少个数比他小
        while (index1 >= start && index2 >= mid + 1) {
            help[helpIndex--] = if (arr[index1] > arr[index2]) {
                reversePairs += (index2 - (mid + 1) + 1)
                arr[index1--]
            } else {
                arr[index2--]
            }
        }

        while (index1 >= start) {
            help[helpIndex--] = arr[index1--]
        }

        while (index2 >= mid + 1) {
            help[helpIndex--] = arr[index2--]
        }

        for (i in help.indices) {
            arr[start + i] = help[i]
        }

        return reversePairs
    }
}