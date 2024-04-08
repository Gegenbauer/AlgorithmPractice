package al.binarytree.recursive

import al.binarytree.TreeNode
import kotlin.math.max
import kotlin.math.min

/**
 * leetcode-98 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左
 * 子树
 * 只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。

 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 10^4] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * 二叉搜索树 BST
 * 对于每一个节点左树的值都比当前节点小，右树的值都比当前节点大
 *
 * 对于中序遍历，整个序列为上升序列
 */
class SearchableBinaryTreeValidator {

    fun isValidBST(root: TreeNode?): Boolean {
        val treeInfo = process(root)
        return treeInfo.isBST
    }

    /**
     * 返回当前子树的最大值，最小值，是否是 BST
     */
    private fun process(root: TreeNode?): Info {
        root ?: return Info(true)

        val left = process(root.left)
        val right = process(root.right)
        var max = root.`val`
        var min = root.`val`
        var isBST = true
        if (!left.isNull) {
            if (left.isBST.not()) return Info(false, isBST = false)
            max = max(max, left.max)
            min = min(min, left.min)
            isBST = isBST and left.isBST and (left.max < root.`val`)
        }
        if (!right.isNull) {
            if (left.isBST.not()) return Info(false, isBST = false)
            max = max(max, right.max)
            min = min(min, right.min)
            isBST = isBST and right.isBST and (right.min > root.`val`)
        }

        return Info(false, max, min, isBST)
    }

    /**
     * 递归信息体
     * 所有节点返回的信息要一致
     */
    private class Info(
        val isNull: Boolean = false,
        val max: Int = 0,
        val min: Int = 0,
        val isBST: Boolean = true
    )
}