package al.binarytree

import java.util.Stack

/**
 * leetcode-114 二叉树展开为链表
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
class FlattenTree {

    /**
     * 借助容器
     */
    fun flatten(root: TreeNode?): Unit {
        root ?: return
        var cur: TreeNode? = root
        val stack = Stack<TreeNode>()
        val traversalResult = mutableListOf<TreeNode>()
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                traversalResult.add(cur)
                stack.push(cur)
                cur = cur.left
            }
            val node = stack.pop()
            cur = node.right
        }

        for (i in traversalResult.indices) {
            if (i != traversalResult.lastIndex) {
                traversalResult[i].right = traversalResult[i + 1]
                traversalResult[i].left = null
            }
        }
        traversalResult.last().left = null
        traversalResult.last().right = null
    }

    /**
     * 不借助容器
     *
     * 思路就是把所有的左子树插入到根节点后，然后再连接右子树
     * 继续往右遍历，保证把所有左子树都连接到右子树上
     */
    fun flatten2(root: TreeNode?): Unit {
        root ?: return
        var cur = root
        while (cur != null) {
            if (cur.left == null) {
                // 无左子树，继续往右遍历
                cur = cur.right
            } else {
                // 找到左子树的最右节点
                var leftTreeRightMost: TreeNode? = cur.left
                while (leftTreeRightMost?.right != null) {
                    leftTreeRightMost = leftTreeRightMost.right
                }
                leftTreeRightMost?.right = cur.right
                cur.right = cur.left
                // 当前节点左子树处理完毕
                cur.left = null

                cur = cur.right
            }
        }
    }
}