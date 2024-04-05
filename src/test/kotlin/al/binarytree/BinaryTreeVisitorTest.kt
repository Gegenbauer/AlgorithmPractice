package al.binarytree

import al.utils.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class BinaryTreeVisitorTest {

    private fun generateTree(): TreeNode {
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

    @Test
    fun testRecursiveVisitor() {
        val tree = generateTree()
        val traversalList = mutableListOf<Int>()
        val visitor = RecursiveTreeVisitor()
        visitor.preOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(1, 2, 4, 5, 3 ,6, 7))
        }
        traversalList.clear()
        visitor.inOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(4, 2, 5, 1, 6, 3, 7))
        }
        traversalList.clear()
        visitor.postOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(4, 5, 2, 6, 7, 3, 1))
        }
        traversalList.clear()
        visitor.layerOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun testIterativeVisitor() {
        val tree = generateTree()
        val traversalList = mutableListOf<Int>()
        val visitor = IterativeTreeVisitor()
        visitor.preOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(1, 2, 4, 5, 3 ,6, 7))
        }
        traversalList.clear()
        visitor.inOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(4, 2, 5, 1, 6, 3, 7))
        }
        traversalList.clear()
        visitor.postOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(4, 5, 2, 6, 7, 3, 1))
        }
        traversalList.clear()
        visitor.layerOrder(tree) { node ->
            traversalList.add(node.`val`)
            true
        }
        assertTrue {
            isArrayEquals(traversalList.toIntArray(), intArrayOf(1, 2, 3, 4, 5, 6, 7))
        }
    }
}