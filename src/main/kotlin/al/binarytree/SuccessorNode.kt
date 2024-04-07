package al.binarytree

/**
 * 获取后继节点
 *
 * x 节点的后继节点，在中序遍历中，x 的后面的一个节点
 *
 * 如果二叉树的节点内包含了父节点信息
 * 求 x 的后继节点 y，要求时间复杂度为 O(|hx - hy|)，即只跟高度差有关
 *
 * 分情况讨论
 * 1）如果 x 有右树，则 x 的后继节点一定是右树上的最左节点
 * 2）如果 x 无右树，如果 x 是父节点的右孩子，则继续往上遍历，
 * 直到遍历到一个父节点是一个右孩子，则该节点为 x 的后继节点
 * 也就是保证 x 为左树上的最右节点，它下一个节点就为该左树的父节点
 */
class SuccessorNode {

    fun successorNode(node: TreeNode): TreeNode? {
        if (node.right != null) return getLeftMostChild(node.right!!)!!
        val firstLeftChildAncestor = getFirstLeftChildAncestor(node)
        return firstLeftChildAncestor?.parent
    }

    private fun getLeftMostChild(node: TreeNode): TreeNode? {
        var cur: TreeNode? = node
        while (cur != null) {
            if (cur.left != null) {
                cur = cur.left
            } else {
                return cur
            }
        }
        return null
    }

    private fun getFirstLeftChildAncestor(node: TreeNode): TreeNode? {
        var cur: TreeNode? = node
        var parent = node.parent
        while (parent != null && parent.right == cur) {
            cur = parent
            parent = cur.parent
        }
        return cur
    }
}