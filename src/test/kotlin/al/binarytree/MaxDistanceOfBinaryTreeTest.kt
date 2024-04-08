package al.binarytree

import al.binarytree.recursive.MaxDistanceOfBinaryTree
import kotlin.test.Test
import kotlin.test.assertEquals

class MaxDistanceOfBinaryTreeTest {

    @Test
    fun test1() {
        val tree = Codec2().deserialize("5,4,7,3,-1,null,null,7,2,9,null,null")
        val maxDistance = MaxDistanceOfBinaryTree().maxDistance(tree)
        assertEquals(6, maxDistance)
    }

    @Test
    fun test2() {
        val tree = Codec2().deserialize("1,2,null,null,3,null,null")
        val maxDistance = MaxDistanceOfBinaryTree().maxDistance(tree)
        assertEquals(2, maxDistance)
    }

    @Test
    fun test3() {
        val tree = Codec2().deserialize("1,2,null,null,3,null,null")
        val maxDistance = MaxDistanceOfBinaryTree().maxDistance(tree)
        assertEquals(2, maxDistance)
    }
}