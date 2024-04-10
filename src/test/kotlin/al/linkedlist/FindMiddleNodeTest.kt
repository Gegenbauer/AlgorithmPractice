package al.linkedlist

import al.util.generateRandomArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class FindMiddleNodeTest {

    @Test
    fun testFindUpperMiddleNode() {
        repeat(10000) {
            val size = Random.nextInt(1, 2000)
            val arr = generateRandomArray(size, 1000, true).distinct()
            val list = arr.toSingleNodeLinkedList()

            val middleNode = FindMiddleNode().findUpperMiddleNode(list)
            assertEquals(arr[(arr.size - 1) shr 1], middleNode?.`val`)
        }
    }

    @Test
    fun testFindLowerMiddleNode() {
        repeat(10000) {
            val size = Random.nextInt(1, 2000)
            val arr = generateRandomArray(size, 1000, true).distinct()
            val list = arr.toSingleNodeLinkedList()

            val middleNode = FindMiddleNode().findLowerMiddleNode(list)
            assertEquals(arr[arr.size shr 1], middleNode?.`val`)
        }
    }

    @Test
    fun testLastUpperMiddleNode() {
        repeat(10000) {
            val size = Random.nextInt(3, 2000)
            val arr = generateRandomArray(size, 1000, true).distinct()
            val list = arr.toSingleNodeLinkedList()

            val middleNode = FindMiddleNode().findLastUpperMiddleNode(list)
            assertEquals(arr[((arr.size - 1) shr 1) - 1], middleNode?.`val`)
        }
    }

    @Test
    fun testLastLowerMiddleNode() {
        repeat(10000) {
            val size = Random.nextInt(3, 2000)
            val arr = generateRandomArray(size, 1000, true).distinct()
            val list = arr.toSingleNodeLinkedList()

            val middleNode = FindMiddleNode().findLastLowerMiddleNode(list)
            assertEquals(arr[(arr.size shr 1) - 1], middleNode?.`val`)
        }
    }
}