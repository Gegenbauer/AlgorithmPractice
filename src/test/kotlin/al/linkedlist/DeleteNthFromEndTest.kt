package al.linkedlist

import kotlin.test.Test
import kotlin.test.assertTrue

class DeleteNthFromEndTest {

    @Test
    fun `should return list with target node deleted when delete middle node`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val targetArr = intArrayOf(1, 2, 3, 5)
        val real = DeleteNthNodeFromEnd().removeNthFromEnd(arr.toSingleNodeLinkedList(), 2)
        assertTrue(isLinkedListEquals(real, targetArr.toSingleNodeLinkedList()))
    }

    @Test
    fun `should return list with head node deleted when delete head node`() {
        val arr = intArrayOf(1, 2)
        val targetArr = intArrayOf(2)
        val real = DeleteNthNodeFromEnd().removeNthFromEnd(arr.toSingleNodeLinkedList(), 2)
        assertTrue(isLinkedListEquals(real, targetArr.toSingleNodeLinkedList()))
    }

    @Test
    fun `should return null list when delete the only node`() {
        val arr = intArrayOf(1)
        val targetArr = intArrayOf()
        val real = DeleteNthNodeFromEnd().removeNthFromEnd(arr.toSingleNodeLinkedList(), 1)
        assertTrue(isLinkedListEquals(real, targetArr.toSingleNodeLinkedList()))
    }

    @Test
    fun `should return list with last node deleted when delete last node`() {
        val arr = intArrayOf(1, 2)
        val targetArr = intArrayOf(1)
        val real = DeleteNthNodeFromEnd().removeNthFromEnd(arr.toSingleNodeLinkedList(), 1)
        assertTrue(isLinkedListEquals(real, targetArr.toSingleNodeLinkedList()))
    }
}