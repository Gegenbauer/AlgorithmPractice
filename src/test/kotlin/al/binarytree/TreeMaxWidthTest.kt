package al.binarytree

import kotlin.test.Test
import kotlin.test.assertEquals

class TreeMaxWidthTest {

    private fun generateBinaryTree1(): TreeNode {
        val node0 = TreeNode(1)
        val node1 = TreeNode(2)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(5)
        val node5 = TreeNode(6)
        val node6 = TreeNode(7)

        node0.left = node1
        node0.right = node2
        node1.left = node3
        node1.right = node4
        node2.left = node5
        node2.right = node6
        return node0
    }

    private fun generateBinaryTree2(): TreeNode {
        val node0 = TreeNode(1)
        val node1 = TreeNode(2)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(5)
        val node5 = TreeNode(6)
        val node6 = TreeNode(7)

        node0.left = node1
        node0.right = node2
        node1.left = node3
        node2.left = node4
        node2.right = node5
        node5.right = node6
        return node0
    }

    private fun generateBinaryTree3(): TreeNode {
        val node0 = TreeNode(1)
        val node1 = TreeNode(2)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(5)
        val node6 = TreeNode(7)

        node0.left = node1
        node0.right = node2
        node1.left = node3
        node1.right = node4
        node2.right = node6
        return node0
    }

    @Test
    fun test() {
        //val tree1 = generateBinaryTree1()
        //assertEquals(4, TreeMaxWidth().maxWidth(tree1))
        val tree2 = generateBinaryTree2()
        assertEquals(4, TreeMaxWidth().maxWidth(tree2))
        //val tree3 = generateBinaryTree3()
        //assertEquals(4, TreeMaxWidth().maxWidth(tree3))
    }
}