package al.backtrack

import java.util.LinkedList
import java.util.Stack

/**
 * leetcode-22 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 */
class GenerateParenthesis {

    fun generateParenthesis(n: Int): List<String> {
        val result = LinkedList<String>()
        backtrack(n, LinkedList(), Stack(), result)
        return result
    }

    private fun backtrack(
        rest: Int,
        path: LinkedList<Char>,
        stack: Stack<Char>,
        result: LinkedList<String>
    ) {
        if (rest == 0 && stack.isEmpty()) {
            result.add(path.joinToString(""))
            return
        }

        // 新增右括号
        if (stack.isNotEmpty() && stack.peek() == '(') {
            path.add(')')
            stack.pop()
            backtrack(rest, path, stack, result)
            path.removeLast()
            stack.push('(')
        }
        // 新增左括号
        if (rest > 0) {
            path.add('(')
            stack.push('(')
            backtrack(rest - 1, path, stack, result)
            path.removeLast()
            stack.pop()
        }
    }
}