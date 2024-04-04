package al.linkedlist

import al.utils.generateRandomArray
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MergeTwoAscendListTest {

    @Test
    fun `should return empty list when merge two empty list`() {
        val real = mergeTwoLists(null, null)
        assertEquals(null, real)
    }

    @Test
    fun `should return correct list when one list is empty`() {
        val listNotEmpty = generateRandomArray(100, 100).sorted().toSingleNodeLinkedList()
        assertTrue(
            isLinkedListEquals(
                mergeTwoLists(listNotEmpty, IntArray(0).toSingleNodeLinkedList()),
                listNotEmpty
            )
        )
    }

    @Test
    fun `should return correct list when two list are not empty`() {
        val list1 = generateRandomArray(100, 100).sorted().toSingleNodeLinkedList()
        val list2 = generateRandomArray(100, 100).sorted().toSingleNodeLinkedList()
        val real = (list1.toIntArray() + list2.toIntArray()).sorted().toSingleNodeLinkedList()

        assertTrue(isLinkedListEquals(mergeTwoLists(list1 , list2), real))
    }
}