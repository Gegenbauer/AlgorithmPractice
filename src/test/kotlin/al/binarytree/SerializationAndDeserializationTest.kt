package al.binarytree

import java.util.LinkedList
import java.util.Queue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationAndDeserializationTest {

    private fun generateBinaryTree1(): TreeNode? {
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

    private fun generateBinaryTree2(): TreeNode? {
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

    private fun generateBinaryTree3(): TreeNode? {
        return null
    }

    @Test
    fun testPreOrder1() {
        val recursiveSerializer = RecursiveSerializer()
        val recursiveDeserializer = RecursiveDeserializer()
        val iterativeSerializer = IterativeSerializer()
        val iterativeDeserializer = IterativeDeserializer()

        val tree = generateBinaryTree1()
        val expectSerializeResult = "1,2,4,null,null,5,null,null,3,6,null,null,7,null,null"
        val recursiveSerializeResult = recursiveSerializer.preOrder(tree)
        val iterativeSerializeResult = iterativeSerializer.preOrder(tree)
        assertEquals(iterativeSerializeResult.joinToString(","), expectSerializeResult)
        assertEquals(recursiveSerializeResult.joinToString(","), expectSerializeResult)

        val recursiveDeserializeResult = recursiveDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        val iterativeDeserializeResult = iterativeDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        assertTrue(isTreeEquals(recursiveDeserializeResult, tree))
        assertTrue(isTreeEquals(iterativeDeserializeResult, tree))
    }

    private fun serializeResultToQueue(result: String): Queue<String> {
        return  LinkedList<String>().apply {
            result.split(",").forEach {
                add(it)
            }
        }
    }

    @Test
    fun testPreOrder2() {
        val recursiveSerializer = RecursiveSerializer()
        val recursiveDeserializer = RecursiveDeserializer()
        val iterativeSerializer = IterativeSerializer()
        val iterativeDeserializer = IterativeDeserializer()

        val tree = generateBinaryTree2()
        val expectSerializeResult = "1,2,4,null,null,null,3,5,null,null,6,null,7,null,null"
        val recursiveSerializeResult = recursiveSerializer.preOrder(tree)
        val iterativeSerializeResult = iterativeSerializer.preOrder(tree)
        assertEquals(iterativeSerializeResult.joinToString(","), expectSerializeResult)
        assertEquals(recursiveSerializeResult.joinToString(","), expectSerializeResult)

        val recursiveDeserializeResult = recursiveDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        val iterativeDeserializeResult = iterativeDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        assertTrue(isTreeEquals(recursiveDeserializeResult, tree))
        assertTrue(isTreeEquals(iterativeDeserializeResult, tree))
    }

    @Test
    fun testPreOrder3() {
        val recursiveSerializer = RecursiveSerializer()
        val recursiveDeserializer = RecursiveDeserializer()
        val iterativeSerializer = IterativeSerializer()
        val iterativeDeserializer = IterativeDeserializer()

        val tree = generateBinaryTree3()
        val expectSerializeResult = "null"
        val recursiveSerializeResult = recursiveSerializer.preOrder(tree)
        val iterativeSerializeResult = iterativeSerializer.preOrder(tree)
        assertEquals(iterativeSerializeResult.joinToString(","), expectSerializeResult)
        assertEquals(recursiveSerializeResult.joinToString(","), expectSerializeResult)

        val recursiveDeserializeResult = recursiveDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        val iterativeDeserializeResult = iterativeDeserializer.preOrder(serializeResultToQueue(expectSerializeResult))
        assertTrue(isTreeEquals(recursiveDeserializeResult, tree))
        assertTrue(isTreeEquals(iterativeDeserializeResult, tree))
    }
}