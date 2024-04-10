package al.stackorqueue

import al.linkedlist.deleteDuplicates
import al.linkedlist.isLinkedListEquals
import al.linkedlist.toSingleNodeLinkedList
import al.util.generateRandomArray
import kotlin.test.Test
import kotlin.test.assertTrue

class DeleteDuplicatesTest {

    @Test
    fun testRandomArrays() {
        repeat(1000) {
            val arr = generateRandomArray(100, 30, true).sorted()
            val list = arr.toSingleNodeLinkedList()
            deleteDuplicates(list)
            assertTrue(isLinkedListEquals(list, arr.distinct().toSingleNodeLinkedList()))
        }
    }
}