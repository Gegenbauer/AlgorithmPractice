package al.linkedlist

import kotlin.test.Test
import kotlin.test.assertTrue

class LinkedListPartitionTest {

    @Test
    fun testSimpleArr() {
        val arr = intArrayOf(2, 2, 3, 4, 6, 5, 4)
        val target = 4
        val list = arr.toSingleNodeLinkedList()
        LinkedListPartition().partition(list, target)
        list.foreachIndex { index, node ->
            if (index < 3) {
                assertTrue { node.`val` < target }
            } else if (index == 3 || index == 4) {
                assertTrue { node.`val` == target }
            } else {
                assertTrue { node.`val` > target }
            }
        }
    }

    @Test
    fun testSimpleArr2() {
        val arr = intArrayOf(1, 4, 3, 2, 5, 2)
        val target = 3
        val list = arr.toSingleNodeLinkedList()
        LinkedListPartition().partition(list, target)
        list.foreachIndex { index, node ->
            if (index < 3) {
                assertTrue { node.`val` < target }
            } else if (index == 3) {
                assertTrue { node.`val` == target }
            } else {
                assertTrue { node.`val` > target }
            }
        }
    }

    fun testRandomArr() {

    }
}