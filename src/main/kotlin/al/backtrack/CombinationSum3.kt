package al.backtrack

import java.util.*
import kotlin.collections.HashSet

/**
 * leetcode-216 组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 *
 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 * 提示:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
class CombinationSum3 {
    /**
     * 解法一，按照回溯的思路递归
     */
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = LinkedList<List<Int>>()
        process(k, n, LinkedList(), result, 1)
        return result
    }

    private fun process(k: Int, n: Int, path: LinkedList<Int>, result: LinkedList<List<Int>>, start: Int) {
        if (n == 0 && k == 0) {
            result.add(path.toList())
            return
        }
        if (n <= 0 || k <= 0) {
            return
        }
        for (i in start..9) {
            if (n < i) return
            path.offer(i)
            process(k - 1, n - i, path, result, i + 1)
            path.removeLast()
        }
    }

    fun combinationSum32(k: Int, n: Int): List<List<Int>> {
        val result = LinkedList<List<Int>>()
        process2(k, n, 0, result, HashSet())
        return result
    }

    /**
     * 解法二
     * 由于数的个数确定，且数目较少，可以用一个正数的二进制形式来对重复组合数去重
     */
    private fun process2(k: Int, n: Int, used: Int, result: LinkedList<List<Int>>, set: HashSet<Int>) {
        if (n == 0 && k == 0) {
            if (set.contains(used)) return
            if (used == 0) return
            val combination = createCombination(used)
            set.add(used)
            result.offer(combination)
            return
        }
        if (n <= 0 || k <= 0) return

        for (i in 1..9) {
            if ((used and (1 shl i)) > 0) continue
            if (n < i) return
            process2(k - 1, n - i, used or (1 shl i), result, set)
        }
    }

    private fun createCombination(used: Int): List<Int> {
        val result = LinkedList<Int>()
        for (i in 1 until 10) {
            val mask = 1 shl i
            if ((used and mask) > 0) {
                result.offer(i)
            }
        }
        return result
    }

    private val result = LinkedList<List<Int>>()
    private val path = LinkedList<Int>()

    /**
     * 解法三
     * 组合推荐这种递归形式，排列推荐回溯的递归形式
     * dfs，减少递归层数，按照一个数 选择｜不选择 进行递归
     */
    fun combinationSum33(k: Int, n: Int): List<List<Int>> {
        process3(k, n, 1)
        return result
    }

    private fun process3(k: Int, n: Int, cur: Int) {
        if (k == 0 && n == 0) {
            result.add(path.toList())
            return
        }
        if (k <= 0 || n <= 0) {
            return
        }
        if (cur > 9) return

        path.offer(cur)
        process3(k - 1, n - cur, cur + 1)
        path.removeLast()
        process3(k, n, cur + 1)
    }
}