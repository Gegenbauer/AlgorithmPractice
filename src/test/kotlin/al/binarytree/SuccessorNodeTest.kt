package al.binarytree

import kotlin.test.Test
import kotlin.test.assertEquals

class SuccessorNodeTest {

    private fun generateBinaryTree1(): Pair<TreeNode, TreeNode> {
        val node0 = TreeNode(6)
        val node1 = TreeNode(2)
        val node2 = TreeNode(8)
        val node3 = TreeNode(0)
        val node4 = TreeNode(4)
        val node5 = TreeNode(7)
        val node6 = TreeNode(9)
        val node7 = TreeNode(3)
        val node8 = TreeNode(5)

        node0.left = node1
        node0.right = node2
        node1.left = node3
        node1.right = node4
        node2.left = node5
        node2.right = node6
        node4.left = node7
        node4.right = node8
        return node0 to node1
    }

    @Test
    fun test() {
        val tree = generateBinaryTree1()
        val successorNode = SuccessorNodeJ().inorderSuccessor(tree.first, tree.second)
        assertEquals(3, successorNode.`val`)
    }

    @Test
    fun testWithParentNode() {
        val head = TreeNode(6)
        head.parent = null
        head.left = TreeNode(3)
        head.left?.parent = head
        head.left?.left = TreeNode(1)
        head.left?.left?.parent = head.left
        head.left?.left?.right = TreeNode(2)
        head.left?.left?.right?.parent = head.left?.left
        head.left?.right = TreeNode(4)
        head.left?.right?.parent = head.left
        head.left?.right?.right = TreeNode(5)
        head.left?.right?.right?.parent = head.left?.right
        head.right = TreeNode(9)
        head.right?.parent = head
        head.right?.left = TreeNode(8)
        head.right?.left?.parent = head.right
        head.right?.left?.left = TreeNode(7)
        head.right?.left?.left?.parent = head.right?.left
        head.right?.right = TreeNode(10)
        head.right?.right?.parent = head.right

        val test: TreeNode = head.left!!.left!!

        assertEquals(SuccessorNode().successorNode(test), head.left?.left?.right)

        val test2: TreeNode = head.left!!.right!!
        assertEquals(SuccessorNode().successorNode(test2), head.left!!.right!!.right)

        // 测试用例3：给定节点的右子树为空，且其为其父节点的左子节点的情况
        val test3: TreeNode = head.left!!.left!!.right!!
        assertEquals(SuccessorNode().successorNode(test3), head.left!!)

        // 测试用例4：给定节点的右子树为空，且其为其父节点的右子节点的情况
        val test4: TreeNode = head.left!!.right!!.right!!
        assertEquals(SuccessorNode().successorNode(test4), head)
    }
}