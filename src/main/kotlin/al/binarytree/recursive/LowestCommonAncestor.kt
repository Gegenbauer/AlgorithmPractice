package al.binarytree.recursive

import al.binarytree.TreeNode

/**
 * leetcode-236 最低公共祖先
 * 最低公共祖先，也叫最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */
class LowestCommonAncestor {

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val info = process(root, p!!, q!!)
        return info.targetAncestor
    }

    private fun process(root: TreeNode?, p: TreeNode, q: TreeNode): Info {
        root ?: return Info()

        var foundP = root == p
        var foundQ = root == q
        val left = process(root.left, p, q)
        val right = process(root.right, p, q)

        if (left.targetAncestor != null) return Info(foundP = true, foundQ = true, targetAncestor = left.targetAncestor)
        if (right.targetAncestor != null) return Info(foundP = true, foundQ = true, targetAncestor = right.targetAncestor)

        foundP = foundP or left.foundP or right.foundP
        foundQ = foundQ or left.foundQ or right.foundQ
        return Info(foundP, foundQ, root.takeIf { foundQ && foundP })
    }

    private class Info(
        val foundP: Boolean = false,
        val foundQ: Boolean = false,
        val targetAncestor: TreeNode? = null
    )
}