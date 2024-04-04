package al.linkedlist

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IsPalindromeLinkedListTest {

    @Test
    fun test1() {
        val arr = intArrayOf(1, 2)
        assertFalse { IsPalindromeLinkedList().isPalindrome(arr.toSingleNodeLinkedList()) }
    }

    @Test
    fun test2() {
        val arr = intArrayOf()
        assertTrue { IsPalindromeLinkedList().isPalindrome(arr.toSingleNodeLinkedList()) }
    }

    @Test
    fun test3() {
        val arr = intArrayOf(1)
        assertTrue { IsPalindromeLinkedList().isPalindrome(arr.toSingleNodeLinkedList()) }
    }

    @Test
    fun test4() {
        val arr = intArrayOf(1, 2, 1)
        assertTrue { IsPalindromeLinkedList().isPalindrome(arr.toSingleNodeLinkedList()) }
    }

    @Test
    fun test5() {
        val arr = intArrayOf(1, 2, 2, 1)
        assertTrue { IsPalindromeLinkedList().isPalindrome(arr.toSingleNodeLinkedList()) }
    }
}