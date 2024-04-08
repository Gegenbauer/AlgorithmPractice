package al.binarytree

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CompleteBinaryTreeCheck {

    private fun generateTree(): TreeNode {
        val node0 = TreeNode(1)
        val node2 = TreeNode(2)
        node0.right = node2
        return node0
    }

    private fun generateTree1(): TreeNode {
        val node0 = TreeNode(1)
        val node1 = TreeNode(2)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(5)
        val node5 = TreeNode(6)
        node0.left = node1
        node0.right = node2
        node1.left = node3
        node1.right = node4
        node2.left = node5
        return node0
    }

    private fun generateTree2(): TreeNode {
        val node0 = TreeNode(1)
        val node1 = TreeNode(2)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(5)
        val node5 = TreeNode(6)
        node0.left = node1
        node0.right = node2
        node1.left = node3
        node1.right = node4
        node2.right = node5
        return node0
    }

    private fun generateTree3(): TreeNode {
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
        node3.left = node6
        return node0
    }

    @Test
    fun test11() {
        val tree = generateTree()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree2(tree) }
    }

    @Test
    fun test12() {
        val tree = generateTree1()
        assertTrue { CompletenessOfBinaryTree().isCompleteBinaryTree2(tree) }
    }

    @Test
    fun test13() {
        val tree = generateTree2()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree2(tree) }
    }

    @Test
    fun test14() {
        val tree = generateTree3()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree2(tree) }
    }

    @Test
    fun test21() {
        val tree = generateTree()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree(tree) }
    }

    @Test
    fun test22() {
        val tree = generateTree1()
        assertTrue { CompletenessOfBinaryTree().isCompleteBinaryTree(tree) }
    }

    @Test
    fun test23() {
        val tree = generateTree2()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree(tree) }
    }

    @Test
    fun test24() {
        val tree = generateTree3()
        assertFalse { CompletenessOfBinaryTree().isCompleteBinaryTree(tree) }
    }
}