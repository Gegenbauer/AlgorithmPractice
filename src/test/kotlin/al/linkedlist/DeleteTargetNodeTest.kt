package al.linkedlist

import kotlin.test.Test
import kotlin.test.assertTrue

class DeleteTargetNodeTest {

    @Test
    fun testExample1() {
        val arr = intArrayOf(1, 2, 6, 3, 4, 5, 6)
        val target = 6
        val list = arr.toSingleNodeLinkedList()
        assertTrue {
            isLinkedListEquals(
                DeleteTargetNode().removeElements(list, target),
                arr.toMutableList().filter { it != target }.toSingleNodeLinkedList()
            )
        }
    }

    @Test
    fun testExample2() {
        val arr = intArrayOf(1, 2, 6, 3, 4, 5, 6)
        val target = 1
        val list = arr.toSingleNodeLinkedList()
        assertTrue {
            isLinkedListEquals(
                DeleteTargetNode().removeElements(list, target),
                arr.toMutableList().filter { it != target }.toSingleNodeLinkedList()
            )
        }
    }
}