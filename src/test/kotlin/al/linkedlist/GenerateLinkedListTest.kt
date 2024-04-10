package al.linkedlist

import al.util.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GenerateLinkedListTest {

    // single node
    @Test
    fun `should return null single node when generate with empty arr`() {
        val real = emptyArray<Int>().toIntArray().toSingleNodeLinkedList()

        assertEquals(null, real)
    }

    @Test
    fun `should return correct single node linked list when generate with non arr`() {
        repeat(1000) {
            val arr = generateRandomArray(1000, 1000)
            val head = arr.toSingleNodeLinkedList()

            var cur = head
            for (i in arr.indices) {
                assertTrue(cur != null)
                assertEquals(cur.`val`, arr[i])
                cur = cur.next
            }
        }
    }


    @Test
    fun `should return true for single node when compare with two list with same values`() {
        repeat(10000) {
            val size = Random.nextInt(0, 1000)
            val arr = generateRandomArray(size, size)
            val linkedList1 = arr.toSingleNodeLinkedList()
            val linkedList2 = arr.toSingleNodeLinkedList()

            assertTrue { isLinkedListEquals(linkedList1, linkedList2) }
        }
    }

    @Test
    fun `should return false for single node when compare with two list with different size`() {
        repeat(10000) {
            val size = Random.nextInt(1, 100)
            val arr = generateRandomArray(size, size)
            val linkedList1 = arr.toSingleNodeLinkedList()
            val linkedList2 = arr.dropLast(1).toSingleNodeLinkedList()

            assertFalse(isLinkedListEquals(linkedList1, linkedList2), "arr=${arr.contentToString()}")
        }
    }

    @Test
    fun `should return false for single node when compare with two list with same size and different value`() {
        repeat(10000) {
            val size = Random.nextInt(1, 1000)
            val arr = generateRandomArray(size, size, isPositive = true)
            val linkedList1 = arr.toSingleNodeLinkedList()
            arr[arr.lastIndex] = -1
            val linkedList2 = arr.toSingleNodeLinkedList()

            assertFalse(isLinkedListEquals(linkedList1, linkedList2), "arr=${arr.contentToString()}")
        }
    }

    // double node
    @Test
    fun `should return null double node when generate with empty arr`() {
        val real = emptyArray<Int>().toIntArray().toDoubleNodeLinkedList()

        assertEquals(null, real)
    }

    @Test
    fun `should return correct double node linked list when generate with non arr`() {
        repeat(1000) {
            val arr = generateRandomArray(1000, 1000)
            val head = arr.toDoubleNodeLinkedList()

            var cur = head
            var tail: DoubleNode? = null
            for (i in arr.indices) {
                assertTrue(cur != null)
                assertEquals(cur.`val`, arr[i])
                if (cur.next == null) {
                    tail = cur
                }
                cur = cur.next as? DoubleNode
            }

            val reversed = arr.reversed()
            cur = tail
            for (i in reversed.indices) {
                assertTrue(cur != null)
                assertEquals(cur.`val`, reversed[i])
                cur = cur.last
            }
        }
    }


    @Test
    fun `should return true for double node when compare with two list with same values`() {
        repeat(10000) {
            val size = Random.nextInt(0, 1000)
            val arr = generateRandomArray(size, size)
            val linkedList1 = arr.toDoubleNodeLinkedList()
            val linkedList2 = arr.toDoubleNodeLinkedList()

            assertTrue { isLinkedListEquals(linkedList1, linkedList2) }
        }
    }

    @Test
    fun `should return false for double node when compare with two list with different size`() {
        repeat(10000) {
            val size = Random.nextInt(1, 10)
            //val arr = generateRandomArray(size, size)
            val arr = intArrayOf(-1, -1, -2, -3)
            val linkedList1 = arr.toDoubleNodeLinkedList()
            val linkedList2 = arr.dropLast(1).toIntArray().toDoubleNodeLinkedList()

            assertFalse(isLinkedListEquals(linkedList1, linkedList2), "arr=${arr.contentToString()}")
        }
    }

    @Test
    fun `should return false for double node when compare with two list with same size and different value`() {
        repeat(10000) {
            val size = Random.nextInt(1, 1000)
            val arr = generateRandomArray(size, size, isPositive = true)
            val linkedList1 = arr.toDoubleNodeLinkedList()
            arr[arr.lastIndex] = -1
            val linkedList2 = arr.toDoubleNodeLinkedList()

            assertFalse(isLinkedListEquals(linkedList1, linkedList2), "arr=${arr.contentToString()}")
        }
    }
}