package al.sort.noncomparison

class CountSort {

    /**
     * 创建一个计数数组，它的索引要能代表原数组元素的值
     * 计数数组元素的取值，代表原数组中有多少个元素值对应计数数组该元素的索引
     * 遍历原数组，构建计数数组
     * 遍历计数数组，依次弹出值到原数组
     */
    fun sort(arr: IntArray) {
        if (arr.size < 2) return

        val max = arr.max()
        val min = arr.min()
        val countArr = IntArray(max - min + 1)
        arr.forEach {
            countArr[it - min]++
        }
        var cur = 0
        countArr.forEachIndexed { index, i ->
            repeat(i) {
                arr[cur++] = index + min
            }
        }
    }
}