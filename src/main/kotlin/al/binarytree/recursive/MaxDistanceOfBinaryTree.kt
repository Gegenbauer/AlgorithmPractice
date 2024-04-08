package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.max

/**
 * 获取二叉树距离最远两个节点的距离
 */
class MaxDistanceOfBinaryTree {

    fun maxDistance(root: TreeNode?): Int {
        root ?: return 0
        val left = process(root.left)
        val right = process(root.right)
        var result = 0
        // 要加上与根节点的距离
        if (left.isNull.not()) {
            result += left.maxDistance + 1
        }
        if (right.isNull.not()) {
            result += right.maxDistance + 1
        }
        return result
    }

    private fun process(root: TreeNode?): Info {
        root ?: return Info(true)

        val left = process(root.left)
        val right = process(root.right)
        var maxDistance = 0
        if (left.isNull.not()) {
            maxDistance = max(maxDistance, left.maxDistance + 1)
        }
        if (right.isNull.not()) {
            maxDistance = max(maxDistance, right.maxDistance + 1)
        }
        return Info(false, maxDistance)
    }

    private class Info(
        val isNull: Boolean = false,
        val maxDistance: Int = 0
    )
}