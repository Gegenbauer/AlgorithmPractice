package al.recursion

import java.util.*

class PrintAllSubSequencesOfString {

    /**
     * 打印字符串的所有子序列，不包含重复结果
     * 子序列，不要求连续
     * 与全排列不同，每次挑选的子序列不一定需要包含所有字符
     *
     * 但可以做个转换，通过将子序列映射成一个布尔数组，布尔数组每个元素代表对应字符是否在子序列内
     *
     * 子序列数目为 2 ^ n
     */
    fun substrings(str: String): List<String> {
        val result = LinkedList<String>()
        process(str.toCharArray(), 0, LinkedList(), result)
        return result
    }

    private fun process(
        chars: CharArray,
        index: Int,
        path: LinkedList<Char>,
        result: LinkedList<String>,
    ) {
        if (index == chars.size) {
            result.add(path.joinToString(""))
            return
        }
        process(chars, index + 1, path, result)

        val selectCurCharPath = LinkedList(path).also { it.add(chars[index]) }
        process(chars, index + 1, selectCurCharPath, result)
    }

    /**
     * 要求不能出现重复字符串
     */
    fun substrings2(str: String): List<String> {
        val result = LinkedList<String>()
        process(str.toCharArray(), 0, LinkedList(), result)
        return result.distinct()
    }

    /**
     * 打印全排列(permutation)
     */
    fun substrings3(str: String): List<String> {
        val result = LinkedList<String>()
        process(str.toCharArray(), LinkedList(), result)
        return result
    }

    private fun process(rest: CharArray, path: LinkedList<Char>, result: LinkedList<String>) {
        if (rest.isEmpty()) {
            result.add(path.joinToString(""))
            return
        }

        fun copyArrWithout(arr: CharArray, removed: Int): CharArray {
            val result = CharArray(arr.size - 1)
            var index = 0
            for ((i, char) in arr.withIndex()) {
                if (i == removed) continue
                result[index++] = char
            }
            return result
        }

        for (i in rest.indices) {
            path.add(rest[i])
            process(copyArrWithout(rest, i), path, result)
            // 还原所有现场
            path.removeLast()
        }
    }

    /**
     * 打印全排列，且不能出现重复排列
     */
    fun substrings4(str: String): List<String> {
        val result = LinkedList<String>()
        process(str.toCharArray().sorted().toCharArray(), 0, result)
        return result
    }

    /**
     * 等效于位置 i 选择 与 [i, n] 的位置进行交换
     */
    private fun process(
        chars: CharArray,
        curIndex: Int,
        result: LinkedList<String>,
    ) {
        if (curIndex == chars.size) {
            result.add(String(chars))
            return
        }
        for (i in curIndex until chars.size) {
            // 相同元素保留与自己交换的情况，其他情况舍弃
            if (i > curIndex && chars[i - 1] == chars[i]) continue
            swap(chars, curIndex, i)
            process(chars, curIndex + 1, result)
            swap(chars, i, curIndex)
        }
    }

    private fun swap(chars: CharArray, i: Int, j: Int) {
        val temp = chars[i]
        chars[i] = chars[j]
        chars[j] = temp
    }
}