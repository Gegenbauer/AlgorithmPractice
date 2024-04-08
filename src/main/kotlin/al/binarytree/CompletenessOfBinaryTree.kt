package al.binarytree

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
class CompletenessOfBinaryTree {
    /**
     * 层序遍历节点，
     * 1. 如果遇到子节点不全的情况，记为遇到了叶子结点
     * 后序如果还遇到了非叶子结点（存在左节点或右节点）则返回 false
     * 2. 如果左节点为空，右节点不为空，则返回 false
     */
    fun isCompleteBinaryTree(root: TreeNode?): Boolean {
        root ?: return false
        var hasVisitedLeaf = false
        var arr = mutableListOf<TreeNode>()
        arr.add(root)
        while (arr.isNotEmpty()) {
            val tmp = mutableListOf<TreeNode>()

            for (node in arr) {
                val left = node.left
                val right = node.right
                if (right != null && left == null) {
                    return false
                }
                if (hasVisitedLeaf && (left != null || right != null)) {
                    return false
                }
                if (right == null || left == null) {
                    hasVisitedLeaf = true
                }
                if (left != null) {
                    tmp.add(left)
                }
                if (right != null) {
                    tmp.add(right)
                }
            }
            arr = tmp
        }
        return true
    }

    /**
     * 判断当前层节点数，与通过最后一个节点编号以及这一层理论上的起始编号计算到的节点数，
     * 两者是否相同
     *
     * 还需要判断如果该层最后一个节点如果与理论最后一个编号不同，不能有下一层
     *
     * 满二叉树第 n 层，第一个节点编号为：2 ^ (n - 1) - 1 - 1 + 1 = 2 ^ (n - 1) - 1
     * 满二叉树第 n 层，最后一个节点编号为：2 ^ n - 1 - 1 = 2 ^ n - 2
     */
    fun isCompleteBinaryTree2(root: TreeNode?): Boolean {
        root ?: return false

        var arr = mutableListOf<Pair<TreeNode, Int>>()
        arr.add(root to 0)
        var level = 1
        while (arr.isNotEmpty()) {
            val tmp = mutableListOf<Pair<TreeNode, Int>>()
            for ((node, index) in arr) {
                if (node.left != null) {
                    tmp.add(node.left!! to 2 * index + 1)
                }
                if (node.right != null) {
                    tmp.add(node.right!! to 2 * index + 2)
                }
            }

            val numberStart = (1 shl (level - 1)) - 1
            val expectedLevelNodeCount = arr.last().second - numberStart + 1
            val fullNodeCount = 1 shl (level - 1)
            if (arr.size != expectedLevelNodeCount) {
                return false
            }
            if (tmp.isNotEmpty() && arr.size != fullNodeCount) {
                return false
            }

            arr = tmp
            level++
        }

        return true
    }
}