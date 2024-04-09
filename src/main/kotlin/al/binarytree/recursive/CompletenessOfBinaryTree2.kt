package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.max

/**
 * leetcode-958 二叉树的完全性检验
 *
 * 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
 *
 * 在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，节点值为 {1} 和 {2,3} 的两层），且最后一层中的所有节点（{4,5,6}）尽可能靠左。
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的节点不满足条件「节点尽可能靠左」。
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 1000
 *
 */
class CompletenessOfBinaryTree2 {

    /**
     * 递归解法
     */
    fun isCompleteBinaryTree(root: TreeNode?): Boolean {
        root ?: return true
        return process(root).isComplete
    }

    private fun process(root: TreeNode?): Info {
        root ?: return Info()

        val left = process(root.left)
        val right = process(root.right)
        val isFull = left.isFull && right.isFull && left.height == right.height
        val height = max(left.height, right.height) + 1

        val isComplete = when {
            left.isFull && right.isFull && (left.height - right.height) in (0..1) -> true
            left.isFull && right.isComplete && left.height == right.height -> true
            left.isComplete && right.isFull && (left.height - right.height) == 1 -> true
            else -> false
        }

        return Info(isComplete, isFull, height)
    }

    private class Info(
        val isComplete: Boolean = true,
        val isFull: Boolean = true,
        val height: Int = 0
    )
}