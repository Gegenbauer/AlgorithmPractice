package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * leetcode-783 二叉搜索树节点最小距离
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 * 提示：
 *
 * 树中节点的数目范围是 [2, 100]
 * 0 <= Node.val <= 105
 */
class MinDeltaOfBST {
    fun minDiffInBST(root: TreeNode?): Int {
        root ?: return 0

        return process(root).minDelta
    }

    private fun process(root: TreeNode?): Info {
        root ?: return Info(true)

        val left = process(root.left)
        val right = process(root.right)
        var minDelta = Int.MAX_VALUE
        var min = root.`val`
        var max = root.`val`
        if (left.isNull.not()) {
            min = min(min, left.min)
            max = max(max, left.max)
            minDelta = min(minDelta, min(left.minDelta, abs(left.max - root.`val`)))
        }

        if (right.isNull.not()) {
            min = min(min, right.min)
            max = max(max, right.max)
            minDelta = min(minDelta, min(right.minDelta, abs(right.min - root.`val`)))
        }

        return Info(false, max, min ,minDelta)
    }

    private class Info(
        val isNull: Boolean,
        val max: Int = 0,
        val min: Int = 0,
        val minDelta: Int = 0
    )
}