package al.binarytree

import java.util.*

class TreeNode(
    @JvmField var `val`: Int,
    @JvmField var left: TreeNode? = null,
    @JvmField var right: TreeNode? = null,
    var parent: TreeNode? = null // 少数题目用到
)

fun isTreeEquals(root1: TreeNode?, root2: TreeNode?): Boolean {
    if (root1 == null && root2 == null) {
        return true
    }
    if (root1 == null || root2 == null) {
        return false
    }
    if (root1.`val` != root2.`val`) {
        return false
    }
    return isTreeEquals(root1.left, root2.left) && isTreeEquals(root1.right, root2.right)
}

/**
 * 按层遍历属于宽度遍历（广度优先），其余属于深度遍历（深度优先）
 */
interface TreeVisitor {

    fun preOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean)

    fun inOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean)

    fun postOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean)

    fun layerOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean)
}

/**
 * 递归通过系统栈来保存现场
 * 可以通过向 stack 和 queue 内放入空值，来减少判空次数
 */
class RecursiveTreeVisitor : TreeVisitor {
    private var shouldStop = false

    override fun preOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        if (root == null || shouldStop) return

        if (!visitor(root)) {
            shouldStop = true
            return
        }
        preOrder(root.left, visitor)
        preOrder(root.right, visitor)
    }

    override fun inOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        if (root == null || shouldStop) return

        inOrder(root.left, visitor)
        if (!visitor(root)) {
            shouldStop = true
            return
        }
        inOrder(root.right, visitor)
    }

    override fun postOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        if (root == null || shouldStop) return

        postOrder(root.left, visitor)
        postOrder(root.right, visitor)
        if (!visitor(root)) {
            shouldStop = true
            return
        }
    }

    override fun layerOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        root ?: return
        val result = mutableListOf<MutableList<TreeNode>>()
        layoutOrder(root, 0, result)
        for (layer in result) {
            var stopped = false
            for (node in layer) {
                if (!visitor(node)) {
                    stopped = true
                    break
                }
            }
            if (stopped) break
        }
    }

    /**
     * 通过一个变量记录深度，和一个列表记录每个层次遍历的节点
     */
    private fun layoutOrder(node: TreeNode?, depth: Int, result: MutableList<MutableList<TreeNode>>) {
        if (node == null) return

        // ** 如果 result 中的列表数小于当前深度，则添加一个新的空列表
        if (depth >= result.size) {
            result.add(mutableListOf())
        }

        // 将当前节点的值添加到对应深度的列表中
        result[depth].add(node)

        // 递归处理左右子树，深度加一
        layoutOrder(node.left, depth + 1, result)
        layoutOrder(node.right, depth + 1, result)
    }
}

/**
 * 通过压栈实现
 * 工程中基本不能出现递归，很容易栈内存溢出
 * 任何递归函数都能改成非递归
 */
class IterativeTreeVisitor : TreeVisitor {
    /**
     * 压入根节点
     *
     * 1. 先从栈中弹出
     * 2. 访问
     * 3. 压入右节点
     * 4. 压入左节点
     *
     * 重复 1-4 直到栈为空
     *
     * 以上访问顺序是 头左右
     * 如果先压入左，再押入右，可以实现 头右左
     */
    override fun preOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        root ?: return
        val stack = Stack<TreeNode>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val cur = stack.pop() ?: continue
            if (!visitor(cur)) break
            stack.push(cur.right)
            stack.push(cur.left)
        }
    }

    /**
     * 需要一个 cur 记录当前遍历到的节点
     * 1. 一直往下遍历左节点，并压入栈中
     * 2. 如果没有左节点，则弹出栈中节点，访问，并遍历右节点
     * 重复 1-2
     */
    override fun inOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        var cur: TreeNode? = root // 表示当前遍历节点
        val stack = Stack<TreeNode>() // stack 用于存放遍历时遇到的根节点
        while (stack.isNotEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur)
                cur = cur.left
            } else {
                cur = stack.pop()
                if (!visitor(cur)) break
                cur = cur.right
            }
        }
    }

    /**
     * 实现头右左
     * 但访问的时候不访问，而是压入另一个栈
     * 遍历完后，从另一个栈全部弹出并访问，相当于头右左的逆序，所以是左右头，即后序遍历
     */
    override fun postOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        root ?: return
        val stack = Stack<TreeNode>()
        val reverseStack = Stack<TreeNode>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val cur = stack.pop() ?: continue
            reverseStack.push(cur)
            stack.push(cur.left)
            stack.push(cur.right)
        }
        while (reverseStack.isNotEmpty()) {
            if (!visitor(reverseStack.pop())) break
        }
    }

    /**
     * 通过队列实现
     * 放入头节点
     * 1. 从队头取出
     * 2. 访问
     * 3. 放入左节点
     * 4. 放入右节点
     *
     * 可以通过设置 flag 的方式来确定某一层是否遍历结束
     */
    override fun layerOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
        root ?: return

        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: return
            if (!visitor(node)) break
            queue.offer(node.left)
            queue.offer(node.right)
        }
    }
}

fun inorderTraversal(root: TreeNode?): List<Int> {
    root ?: return emptyList()
    val result = mutableListOf<Int>()
    val stack = Stack<TreeNode>()
    var cur: TreeNode? = root
    while (stack.isNotEmpty() || cur != null) {
        if (cur != null) {
            stack.push(cur)
            cur = cur.left
        } else {
            val node = stack.pop()
            result.add(node.`val`)
            cur = node.right
        }
    }

    return result
}

/**
 * leetcode-102 二叉树的层序遍历
 *
 * 通过一个 map 记录每个节点所在层
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    root ?: return emptyList()
    val queue: Queue<TreeNode> = LinkedList()
    val levels = mutableMapOf<TreeNode, Int>()
    levels[root] = 0
    queue.offer(root)
    val result = mutableListOf<MutableList<Int>>()
    while (queue.isNotEmpty()) {
        val cur = queue.poll() ?: continue
        val level = levels[cur]!!
        if (level >= result.size) {
            result.add(mutableListOf())
        }
        result[level].add(cur.`val`)
        if (cur.left != null) {
            queue.offer(cur.left)
            levels[cur.left!!] = level + 1
        }
        if (cur.right != null) {
            queue.offer(cur.right)
            levels[cur.right!!] = level + 1
        }
    }
    return result
}