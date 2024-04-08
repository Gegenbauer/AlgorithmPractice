package al.binarytree

import al.binarytree.recursive.FullBinaryTreeValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FullBinaryTreeValidatorTest {

    @Test
    fun test1() {
        val tree = Codec2().deserialize("5,4,7,3,-1,null,null,7,2,9,null,null")
        val result = FullBinaryTreeValidator().isFullBinaryTree(tree)
        assertFalse { result }
    }

    @Test
    fun test2() {
        val tree = Codec2().deserialize("null")
        val result = FullBinaryTreeValidator().isFullBinaryTree(tree)
        assertTrue { result }
    }

    @Test
    fun test3() {
        val tree = Codec2().deserialize("1,2,3,null,null,4,null,null,5,6,null,null,7,null,null")
        val result = FullBinaryTreeValidator().isFullBinaryTree(tree)
        assertTrue { result }
    }

    @Test
    fun test4() {
        val tree = Codec2().deserialize("1")
        val result = FullBinaryTreeValidator().isFullBinaryTree(tree)
        assertTrue { result }
    }

    @Test
    fun test5() {
        val tree = Codec2().deserialize("1,2,3,null,null,4,5,null,null")
        val result = FullBinaryTreeValidator().isFullBinaryTree(tree)
        assertFalse { result }
    }
}