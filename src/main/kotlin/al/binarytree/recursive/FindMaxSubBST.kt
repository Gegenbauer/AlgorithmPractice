package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.max
import kotlin.math.min

/**
 * https://leetcode.cn/problems/largest-bst-subtree/description/ (vip..)
 * lintcode-910 最大二叉搜索子树
 *
 * 描述
 * 给定一棵二叉树，找到是二叉搜索树的最大子树，最大子树定义为子树节点最多。
 * 你找到的子树必须是一棵二叉搜索树（BST）。
 *
 * 样例
 * 样例 1:
 * 输入:
 * {10,5,15,1,8,#,7}
 * 输出：
 * 3
 *
 * 解释:
 *     10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 * 二叉搜索树的最大子树为 :
 *    5
 *   / \
 *  1   8.
 * 返回子树大小3.
 *
 * 样例 2:
 * 输入:
 * {1}
 * 输出：
 * 1
 *
 * 你能在O(n)的时间复杂度下解决这个问题吗？
 */
class FindMaxSubBST {
    fun largestBSTSubtree(root: TreeNode?): Int {
        root ?: return 0
        val info = process(root)
        return info.maxBSTNodeCount
    }

    private fun process(root: TreeNode?): Info {
        root ?: return Info(true)

        val left = process(root.left)
        val right = process(root.right)
        var max = root.`val`
        var min = root.`val`
        var nodeCount = 1
        var maxBSTNodeCount = 0
        var isBST = true
        if (left.isNull.not()) {
            max = max(max, max(root.`val`, left.maxValue))
            min = min(min, min(root.`val`, left.minValue))
            isBST = isBST and (left.maxBSTNodeCount == left.nodeCount) && (root.`val` > left.maxValue)
            nodeCount += left.nodeCount
            maxBSTNodeCount = max(maxBSTNodeCount, left.maxBSTNodeCount)
        }
        if (right.isNull.not()) {
            max = max(max, max(root.`val`, right.maxValue))
            min = min(min, min(root.`val`, right.minValue))
            isBST = isBST and (right.maxBSTNodeCount == right.nodeCount) && (root.`val` < right.minValue)
            nodeCount += right.nodeCount
            maxBSTNodeCount = max(maxBSTNodeCount, right.maxBSTNodeCount)
        }
        if (isBST) {
            maxBSTNodeCount = 0
            if (left.isNull.not()) {
                maxBSTNodeCount += left.nodeCount
            }
            if (right.isNull.not()) {
                maxBSTNodeCount += right.nodeCount
            }
            maxBSTNodeCount += 1
        }
        return Info(false, nodeCount, maxBSTNodeCount, max, min)
    }

    private class Info(
        val isNull: Boolean = false,
        val nodeCount: Int = 0,
        val maxBSTNodeCount: Int = 0,
        val maxValue: Int = 0,
        val minValue: Int = 0
    )
}