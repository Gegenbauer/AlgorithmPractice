package al.linkedlist

import al.utils.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class ReverseLinkedListTest {

    @Test
    fun testReverseSingleNodeLinkedList() {

        repeat(10000) {
            val size = Random.nextInt(0, 1000)
            val arr = generateRandomArray(size, size)
            val head = arr.toSingleNodeLinkedList()
            val real = reverseSingleNodeLinkedList(head)
            arr.reverse()
            val expected = arr.toSingleNodeLinkedList()
            assertTrue { isLinkedListEquals(expected, real) }
        }
    }

    @Test
    fun testReverseDoubleNodeLinkedList() {

        repeat(10000) {
            val size = Random.nextInt(0, 1000)
            val arr = generateRandomArray(size, size)
            val head = arr.toDoubleNodeLinkedList()
            val real = reverseDoubleNodeLinkedList(head)
            arr.reverse()
            val expected = arr.toDoubleNodeLinkedList()
            assertTrue { isLinkedListEquals(expected, real) }
        }
    }
}