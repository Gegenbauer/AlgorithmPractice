package al.binarytree

import al.binarytree.recursive.FindMaxSubBST
import kotlin.test.Test
import kotlin.test.assertEquals

class FindMaxSubBSTTest {

    @Test
    fun test1() {
        val tree = Codec2().deserialize("2,1,null,null,3,null,null")
        assertEquals(3, FindMaxSubBST().largestBSTSubtree(tree))
    }
}