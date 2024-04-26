package al.backtrack

import java.util.LinkedList

/**
 * leetcode-40 组合总和 II
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
class CombinationSum2 {

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        val result = LinkedList<List<Int>>()
        process(candidates, target, 0, LinkedList(), result)
        return result
    }

    private fun process(
        candidates: IntArray,
        target: Int,
        cur: Int,
        path: LinkedList<Int>,
        result: LinkedList<List<Int>>
    ) {
        if (target == 0) {
            result.add(path.toList())
            return
        }
        val used = hashSetOf<Int>()
        for (i in cur until candidates.size) {
            if (target < candidates[i]) return
            if (candidates[i] in used) continue
            used.add(candidates[i])
            path.offer(candidates[i])
            process(candidates, target - candidates[i], i + 1, path, result)
            path.removeLast()
        }
    }
}