package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.max

/**
 * 通过递归判断一棵树是否是完全二叉树
 * 判断整棵树的节点数是否满足要求即可
 */
class FullBinaryTreeValidator {

    fun isFullBinaryTree(root: TreeNode?): Boolean {
        val info = process(root)
        return info.isNull || info.nodeCount == ((1 shl info.height) - 1)
    }

    private fun process(root: TreeNode?): Info {
        root ?: return Info(true)

        val left = process(root.left)
        val right = process(root.right)
        var height = 0
        var count = 0
        if (left.isNull.not()) {
            count += left.nodeCount
            height = max(height, left.height)
        }
        if (right.isNull.not()) {
            count += right.nodeCount
            height = max(height, right.height)
        }

        return Info(false, height + 1, count + 1)
    }

    private class Info(
        val isNull: Boolean = false,
        val height: Int = 0,
        val nodeCount: Int = 0
    )
}