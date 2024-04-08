package al.binarytree

/**
 * leetcode-199 二叉树的右视图
 *
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 *
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 *
 * 示例 3:
 * 输入: []
 * 输出: []
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
class LeftView {
    fun rightSideView(root: TreeNode?): List<Int> {
        root ?: return emptyList()

        var arr = mutableListOf<TreeNode>()
        val result = mutableListOf<Int>()
        arr.add(root)
        while (arr.isNotEmpty()) {
            val tmp = mutableListOf<TreeNode>()

            for (node in arr) {
                if (node.left != null) {
                    tmp.add(node.left!!)
                }
                if (node.right != null) {
                    tmp.add(node.right!!)
                }
            }

            result.add(arr.last().`val`)
            arr = tmp
        }
        return result
    }
}