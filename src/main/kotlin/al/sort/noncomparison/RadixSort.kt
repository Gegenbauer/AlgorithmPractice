package al.sort.noncomparison

import java.util.Arrays

class RadixSort {

    /**
     * 建立十个桶，表示数字 0-9
     * 从最低位开始，依次将该位对应的数字放进对应的桶中
     * 依次遍历所有桶，并按照入桶顺序倒出桶中数字
     *
     * 即数组会先按照最低位排序，再到倒数第二位，按照最高位排好序后，整体排序完成
     * 整个过程不会改变原数组中相等元素的顺序-稳定
     */
    fun sort(arr: IntArray) {
        if (arr.size < 2) return
        radixSort(arr, 0, arr.lastIndex, maxBits(arr))
    }

    fun sort2(arr: IntArray) {
        if (arr.size < 2) return
        radixSort2(arr, 0, arr.lastIndex, maxBits(arr))
    }

    /**
     * 对于数组中索引从 [l, r] 的元素，从第 [bits] 位开始排序
     */
    private fun radixSort(arr: IntArray, l: Int, r: Int, bits: Int) {
        val bitBucket = ArrayList<ArrayList<Int>>(BASE)
        repeat(BASE) {
            bitBucket.add(ArrayList())
        }

        for (i in 0 until bits) {
            for (cur in l..r) {
                val bit = getBits(arr[cur], i)
                bitBucket[bit].add(arr[cur])
            }
            var currentIndex = 0
            bitBucket.forEach { bucket ->
                bucket.forEach { value ->
                    arr[currentIndex++] = value
                }
                bucket.clear()
            }
        }
    }

    private fun maxBits(arr: IntArray): Int {
        val max = arr.max()
        var result = 0
        var cur = max
        while (cur > 0) {
            result++
            cur /= BASE
        }
        return result
    }

    private fun getBits(value: Int, index: Int): Int {
        var currentBitIndex = 0
        var cur = value
        while (currentBitIndex < index) {
            cur /= BASE
            currentBitIndex++
        }
        return cur % BASE
    }

    private fun radixSort2(arr: IntArray, l: Int, r: Int, bits: Int) {
        for (bitIndex in 0 until bits) {
            Arrays.fill(bitCounts, 0)
            for (arrIndex in l..r) {
                // 获取指定位的值可以优化，不过影响不大
                val bit = getBits(arr[arrIndex], bitIndex)
                bitCounts[bit]++
            }
            // 通过出现次数的前缀后数组，
            // 可以知道对于每个数 i，小于等于 i 的数有多少个，则可以知道这个数排序后的索引。
            prefixSum(bitCounts)
            for (arrIndex in r downTo l) { // 逆序遍历是为了保证排序稳定性
                val targetIndex = bitCounts[getBits(arr[arrIndex], bitIndex)]--
                help[targetIndex - 1] = arr[arrIndex]
            }
            for (i in 0 until (r - l + 1)) {
                arr[l + i] = help[i]
            }
        }
    }

    private fun prefixSum(arr: IntArray) {
        for (i in 1..arr.lastIndex) {
            arr[i] = arr[i - 1] + arr[i]
        }
    }

    companion object {
        private const val MAX_DATA_COUNT = 1000 // 根据测试数据量调整
        private const val BASE = 10 // 进制

        private val help = IntArray(MAX_DATA_COUNT)
        private val bitCounts = IntArray(BASE)
    }
}