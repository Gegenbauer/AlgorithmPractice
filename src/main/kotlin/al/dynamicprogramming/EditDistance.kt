package al.dynamicprogramming

import kotlin.math.min

/**
 * leetcode-72 编辑距离
 *
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
class EditDistance {
    /**
     * 暴力递归 + 备忘录
     */
    fun minDistance(word1: String, word2: String): Int {
        return process(word1, word2, HashMap())
    }

    private fun process(word1: String, word2: String, cache: HashMap<String, Int>): Int {
        val key = "${word1}_$word2"
        if (cache.containsKey(key)) return cache[key]!!
        if (word2 == word1) {
            return 0
        }

        // 当前两个字符相同
        var result = Int.MAX_VALUE
        if (word1.isNotEmpty() && word2.isNotEmpty() && word1[0] == word2[0]) {
            val distance = process(word1.substring(1), word2.substring(1), cache)
            result = min(result, distance)
        }
        // 剩余多的字符，只能删除
        if (word1.isNotEmpty() && word2.isEmpty()) {
            result = min(result, word1.length)
        }
        // 未剩余字符, 目标字符串还有字符, 只能插入
        if (word1.isEmpty() && word2.isNotEmpty()) {
            result = min(result, word2.length)
        }
        // 当前字符不相同
        if (word1.isNotEmpty() && word2.isNotEmpty() && word1[0] != word2[0]) {
            // 插入
            result = min(result, process(word1, word2.substring(1), cache) + 1)
            // 删除
            result = min(result, process(word1.substring(1), word2, cache) + 1)
            // 更改
            result = min(result, process(word1.substring(1), word2.substring(1), cache) + 1)
        }
        cache[key] = result
        return result
    }
}