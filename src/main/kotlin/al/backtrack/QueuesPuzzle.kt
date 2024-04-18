package al.backtrack

import java.util.*

/**
 * leetcode-51 N 皇后
 *
 * 在 n * n 棋盘上放置 n 个皇后，保证皇后之间彼此不能攻击
 * 皇后会向其同列，同行，同斜线的位置发起进攻
 * 求所有放置方式
 */
class QueuesPuzzle {

    /**
     * 回溯，以每行放置一个为目标
     * 每次放置一个皇后，需要对其他位置进行标记，因为需要回溯标记，所以标记需要变为整型
     */
    fun solveNQueens(n: Int): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val disabled = Array(n) { IntArray(n) }
        backtrack(n, LinkedList(), disabled, result)
        return result
    }

    private fun backtrack(
        n: Int,
        path: LinkedList<String>,
        disabled: Array<IntArray>,
        result: MutableList<List<String>>
    ) {
        if (path.size == n) {
            result.add(path.toList())
            return
        }

        for (i in 0 until n) {
            if (disabled[path.size][i] > 0) continue
            disable(n, path.size, i, disabled, true)
            path.offer(createPath(i, n))
            backtrack(n, path, disabled, result)
            path.removeLast()
            disable(n, path.size, i, disabled, false)
        }
    }

    private fun createPath(queuePos: Int, n: Int): String {
        val result = StringBuilder()
        for (i in 0 until n) {
            result.append(
                if (i == queuePos) {
                    'Q'
                } else {
                    '.'
                }
            )
        }
        return result.toString()
    }

    private fun disable(
        n: Int,
        queueRow: Int,
        queueCol: Int,
        disabled: Array<IntArray>,
        disableOrEnable: Boolean = true
    ) {
        for (row in 0 until n) {
            for (col in 0 until n) {
                val shouldDisable = isAttacked(n, queueRow, queueCol, row, col)
                if (shouldDisable) {
                    if (disableOrEnable) {
                        disabled[row][col]++
                    } else {
                        disabled[row][col]--
                    }
                }
            }
        }
    }

    /**
     * 正斜线，行与列差值相同即可
     * 反斜线，关于中间行对称，满足正斜线即可
     */
    fun isAttacked(n: Int, queueRow: Int, queueCol: Int, targetRow: Int, targetCol: Int): Boolean {
        return if ((queueRow - targetRow) == (queueCol - targetCol)) {
            true
        } else if (((n - 1 - queueRow) - (n - 1 - targetRow)) == (queueCol - targetCol)) {
            true
        } else if (targetRow == queueRow) {
            true
        } else if (targetCol == queueCol) {
            true
        } else {
            false
        }
    }
}