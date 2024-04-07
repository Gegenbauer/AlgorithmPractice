package al.binarytree

import java.util.*


const val NULL_NODE_FLAG = "#"

/**
 * 将一棵数转化为字符串和按照字符串生成一棵树
 *
 * 要记录空节点，所以要将空节点也入栈，并访问
 *
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 */
class IterativeSerializer {

    fun preOrder(root: TreeNode?): Queue<String> {
        val result: Queue<String> = LinkedList()
        val stack = Stack<TreeNode?>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val cur = stack.pop()
            if (cur == null) {
                result.offer(NULL_NODE_FLAG)
            } else {
                result.offer(cur.`val`.toString())
                stack.push(cur.right)
                stack.push(cur.left)
            }
        }
        return result
    }
}

class RecursiveSerializer {

    fun preOrder(root: TreeNode?): Queue<String> {
        val result: Queue<String> = LinkedList()
        preOrder(root, result)
        return result
    }

    private fun preOrder(root: TreeNode?, result: Queue<String>) {
        if (root == null) {
            result.offer(NULL_NODE_FLAG)
            return
        } else {
            result.offer(root.`val`.toString())
            preOrder(root.left, result)
            preOrder(root.right, result)
        }
    }
}

class IterativeDeserializer {

    fun preOrder(nodes: Queue<String>): TreeNode? {
        if (nodes.isEmpty()) return null
        val rootStr = nodes.poll()
        if (rootStr == NULL_NODE_FLAG) return null
        val rootNode = TreeNode(rootStr.toInt())
        val stack = Stack<TreeNode>()
        stack.push(rootNode)
        var curNode: TreeNode?

        fun getNode(str: String): TreeNode {
            return if (str == NULL_NODE_FLAG) {
                EMPTY_NODE
            } else {
                TreeNode(str.toInt())
            }
        }

        while (nodes.isNotEmpty()) {
            val nextStr = nodes.poll()
            curNode = getNode(nextStr)
            val parent = stack.peek()
            if (parent.left == null) {
                parent.left = curNode
            } else {
                parent.right = curNode
                stack.pop()
            }
            if (curNode != EMPTY_NODE) {
                stack.push(curNode)
            }
        }
        setNullNode(rootNode)
        return rootNode
    }

    private fun setNullNode(root: TreeNode?) {
        root ?: return
        if (root.left == EMPTY_NODE) {
            root.left = null
        } else {
            setNullNode(root.left)
        }
        if (root.right == EMPTY_NODE) {
            root.right = null
        } else {
            setNullNode(root.right)
        }
    }
}

val EMPTY_NODE = TreeNode(0)

class RecursiveDeserializer {
    fun preOrder(nodes: Queue<String>): TreeNode? {
        if (nodes.isEmpty()) return null
        val curStr = nodes.poll()
        return if (curStr == NULL_NODE_FLAG) {
            null
        } else {
            TreeNode(curStr.toInt()).apply {
                left = preOrder(nodes)
                right = preOrder(nodes)
            }
        }
    }
}

/**
 * leetcode-297 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val result = StringBuilder()
        serialize(root, result)
        return result.trim(SPLITTER).toString()
    }

    private fun serialize(root: TreeNode?, result: StringBuilder) {
        if (root == null) {
            result.append(NULL_FLAG).append(SPLITTER)
            return
        } else {
            result.append(root.`val`.toString()).append(SPLITTER)
            serialize(root.left, result)
            serialize(root.right, result)
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val strings = data.split(SPLITTER)
        val strQueue = LinkedList<String>().apply {
            strings.forEach { add(it) }
        }
        return deserialize(strQueue)
    }

    private fun deserialize(strings: Queue<String>): TreeNode? {
        val nodeStr = strings.poll()
        if (nodeStr == NULL_FLAG) {
            return null
        } else {
            val node = TreeNode(nodeStr.toInt())
            node.left = deserialize(strings)
            node.right = deserialize(strings)
            return node
        }
    }

    companion object {
        private const val NULL_FLAG = "#"
        private const val SPLITTER = ','
    }
}

/**
 * 非递归方式
 * 测试效率更低
 */
class Codec2() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val result = StringBuilder()
        serialize(root, result)
        return result.trim(SPLITTER).toString()
    }

    private fun serialize(root: TreeNode?, result: StringBuilder) {
        val stack = Stack<TreeNode>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val cur = stack.pop()
            if (cur == null) {
                result.append(NULL_FLAG).append(SPLITTER)
                continue
            } else {
                result.append(cur.`val`.toString()).append(SPLITTER)
            }
            stack.push(cur.right)
            stack.push(cur.left)
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val strings = data.split(SPLITTER)
        val strQueue = LinkedList<String>().apply {
            strings.forEach { add(it) }
        }
        return deserialize(strQueue)
    }

    private fun deserialize(strings: Queue<String>): TreeNode? {
        if (strings.isEmpty()) return null
        val rootStr = strings.poll()
        if (rootStr == NULL_FLAG) return null
        val rootNode = TreeNode(rootStr.toInt())
        val stack = Stack<TreeNode>()
        stack.push(rootNode)
        fun getNode(str: String): TreeNode {
            return if (str == NULL_FLAG) {
                EMPTY_NODE
            } else {
                TreeNode(str.toInt())
            }
        }

        while (strings.isNotEmpty()) {
            val node = getNode(strings.poll())
            val parent = stack.peek()
            if (parent.left == null) {
                parent.left = node
            } else {
                parent.right = node
                stack.pop()
            }
            if (node != EMPTY_NODE) {
                stack.push(node)
            }
        }

        setNullNode(rootNode)
        return rootNode
    }

    private fun setNullNode(root: TreeNode?) {
        root ?: return
        if (root.left == EMPTY_NODE) {
            root.left = null
        } else {
            setNullNode(root.left)
        }
        if (root.right == EMPTY_NODE) {
            root.right = null
        } else {
            setNullNode(root.right)
        }
    }

    companion object {
        val EMPTY_NODE = TreeNode(0)
        private const val NULL_FLAG = "#"
        private const val SPLITTER = ','
    }
}