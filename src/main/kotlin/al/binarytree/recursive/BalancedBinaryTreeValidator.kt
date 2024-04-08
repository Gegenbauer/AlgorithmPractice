package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.abs
import kotlin.math.max

/**
 * leetcode-110 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是
 * 平衡二叉树

 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 *
 * 平衡二叉树即任何两棵子树的高度差不超过 1
 */
class BalancedBinaryTreeValidator {

    /**
     * 1) x 左树为平衡二叉树
     * 2) x 右树为平衡二叉树
     * 3) x 左右树高度 < 2
     */
    fun isBalanced(root: TreeNode?): Boolean {
        root ?: return true
        val left = process(root.left)
        val right = process(root.right)
        return left.second && right.second && (abs(right.first - left.first) < 2)
    }

    /**
     * 假设能从子问题获取到需要的信息
     * 本身也能根据子问题获取到的信息向上提供相同信息
     */
    private fun process(root: TreeNode?): Pair<Int, Boolean> {
        root ?: return 0 to true

        val left = process(root.left)
        val right = process(root.right)
        val isBalancedTree = (left.second && right.second && (abs(right.first - left.first) < 2))
        return (max(left.first, right.first) + 1) to isBalancedTree
    }
}