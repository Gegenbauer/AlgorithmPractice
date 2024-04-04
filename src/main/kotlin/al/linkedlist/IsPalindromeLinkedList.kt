package al.linkedlist

import java.util.Stack

/**
 * leetcode-面试题 02.06 回文链表
 *
 * 编写一个函数，检查输入的链表是否是回文的。
 *
 *
 *
 * 示例 1：
 *
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 *
 * 输入： 1->2->2->1
 * 输出： true
 *
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
class IsPalindromeLinkedList {

    private fun bruteForce(head: ListNode?): Boolean {
        val stack = Stack<Int>()
        var cur = head
        while (cur != null) {
            stack.push(cur.`val`)
            cur = cur.next
        }

        cur = head
        while (stack.isNotEmpty()) {
            if (stack.pop() != cur?.`val`) {
                return false
            }
            cur = cur?.next
        }
        return true
    }

    fun isPalindrome(head: ListNode?): Boolean {
        return doublePointers(head)
    }

    private fun doublePointers(head: ListNode?): Boolean {
        head ?: return true
        head.next ?: return true
        // 先找到中间节点，奇数为中间点，偶数为上中点
        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        val middleNode = slow
        // 从中间链表开始反转
        var pre: ListNode? = middleNode
        var cur: ListNode? = pre?.next
        middleNode?.next = null // 将中点作为前半段链表的末尾
        pre = null
        while (cur != null) {
            val temp = cur.next
            cur.next = pre
            pre = cur
            cur = temp
        }
        // pre 为末尾节点
        // 从两端开始校验
        var back: ListNode? = pre
        var front: ListNode? = head
        // 1 -> 2 -> 3 -> 4 => 1 -> 2 -> 3 <- 4
        var result = true
        while (back != null && front != null) { // 一定会同时遍历完
            if (back.`val` != front.`val`) {
                result = false
                break
            }
            back = back.next
            front = front.next
        }

        // 从中间链表开始反转
        cur = pre
        pre = null
        while (cur != null) {
            val temp = cur.next
            cur.next = pre
            pre = cur
            cur = temp
        }
        middleNode?.next = pre // 衔接两段链表
        // 注意边界条件
        return result
    }
}