package al.backtrack

import java.util.*

/**
 * leetcode-17 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
class LetterCombinations {

    private val letters = hashMapOf(
        2 to "abc",
        3 to "def",
        4 to "ghi",
        5 to "jkl",
        6 to "mno",
        7 to "pqrs",
        8 to "tuv",
        9 to "wxyz",
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        val numbers = digits.map { it - '0' }.toIntArray()
        val result = LinkedList<String>()
        backtrack(numbers, LinkedList(), result, 0)
        return result
    }

    private fun backtrack(
        numbers: IntArray,
        path: LinkedList<Char>,
        result: LinkedList<String>,
        currentNumberIndex: Int
    ) {
        if (currentNumberIndex == numbers.size) {
            result.add(path.joinToString(""))
            return
        }

        val strs = letters[numbers[currentNumberIndex]]!!
        for (char in strs) {
            path.offer(char)
            backtrack(numbers, path, result, currentNumberIndex + 1)
            path.removeLast()
        }
    }
}