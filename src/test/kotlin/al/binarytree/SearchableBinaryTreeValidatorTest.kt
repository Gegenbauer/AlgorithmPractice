package al.binarytree

import al.binarytree.recursive.SearchableBinaryTreeValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchableBinaryTreeValidatorTest {

    private fun generateTree(): TreeNode {
        val node0 = TreeNode(2)
        val node1 = TreeNode(1)
        val node2 = TreeNode(3)
        node0.left = node1
        node0.right = node2
        return node0
    }

    private fun generateTree1(): TreeNode {
        val node0 = TreeNode(5)
        val node1 = TreeNode(1)
        val node2 = TreeNode(4)
        val node5 = TreeNode(3)
        val node6 = TreeNode(6)
        node0.left = node1
        node0.right = node2
        node2.left = node5
        node2.right = node6
        return node0
    }


    @Test
    fun test1() {
        val tree = generateTree()
        assertTrue { SearchableBinaryTreeValidator().isValidBST(tree) }
    }

    @Test
    fun test2() {
        val tree = generateTree1()
        assertFalse { SearchableBinaryTreeValidator().isValidBST(tree) }
    }
}