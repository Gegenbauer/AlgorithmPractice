package al.binarytree

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 求树中节点最多的一层
 */
class TreeMaxWidth {

    /**
     * leetcode 662 二叉树的最大宽度
     *
     * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
     *
     * 树的 最大宽度 是所有层中最大的 宽度 。
     *
     * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
     *
     * 题目数据保证答案将会在  32 位 带符号整数范围内。
     *
     * 示例 1：
     * 输入：root = [1,3,2,5,3,null,9]
     * 输出：4
     * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
     *
     * 示例 2:
     * 输入：root = [1,3,2,5,null,null,9,6,null,7]
     * 输出：7
     * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
     *
     * 示例 3:
     * 输入：root = [1,3,2,5]
     * 输出：2
     * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
     *
     * 提示：
     *
     * 树中节点的数目范围是 [1, 3000]
     * -100 <= Node.val <= 100
     *
     * 需要记录
     * 1. 当前层的末尾节点
     * 2. 下一层的末尾节点
     * 3. 当前层节点计数
     * 4. 最大宽度
     *
     * 需要注意的是，不能通过计数的方式来编号
     * 因为可能出现中间有一个父节点为空，导致漏算
     * 应该通过 i * 2 + 1 和 i * 2 + 2 来编号
     *
     * 这里引入按层遍历的第二种方式（bfs），不通过队列来实现
     * 而是每层维护一个列表，用于存储下一层的所有节点
     * 这样可以很好的区分层与层之间的节点
     */
    fun maxWidth(root: TreeNode?): Int {
        root ?: return 0

        var arr = mutableListOf<Pair<TreeNode, Int>>()
        arr.add(root to 0)
        var maxWidth = 0
        while (arr.isNotEmpty()) {
            val tmp = mutableListOf<Pair<TreeNode, Int>>()
            val startIndex = arr.first().second // 防止溢出
            for (pair in arr) {
                val currentIndex = pair.second
                val currentNode = pair.first
                if (currentNode.left != null) {
                    tmp.add(currentNode.left!! to (currentIndex * 2 + 1 - startIndex))
                }
                if (currentNode.right != null) {
                    tmp.add(currentNode.right!! to (currentIndex * 2 + 2 - startIndex))
                }
            }

            val currentLevelMaxWidth = arr.last().second - arr.first().second + 1
            maxWidth = max(maxWidth, currentLevelMaxWidth)
            arr = tmp
        }
        return maxWidth
    }

    /**
     * 含有节点最多的层，有多少个节点
     */
    fun maxWidth2(root: TreeNode?): Int {
        root ?: return 0

        val levels = mutableMapOf<TreeNode, Int>()
        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root)
        levels[root] = 0
        var maxWidth = 0
        var curLevelNodes = 0
        var curLevel = 0
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            val level = levels[cur]!!
            if (level == curLevel) {
                curLevelNodes++
            } else if (level > curLevel) {
                maxWidth = max(maxWidth, curLevelNodes)
                curLevel = level
                curLevelNodes = 0
            }
            if (cur.left != null) {
                queue.offer(cur.left)
                levels[cur.left!!] = level + 1
            }
            if (cur.right != null) {
                queue.offer(cur.right)
                levels[cur.right!!] = level + 1
            }
        }
        return maxWidth
    }
}