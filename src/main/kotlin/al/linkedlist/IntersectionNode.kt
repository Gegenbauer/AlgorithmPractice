package al.linkedlist

import kotlin.math.abs

/**
 * leetcode-160 相交链表
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 *
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 *
 * q 走 A + B
 * p 走 B + A
 * 因为有交点，所以一定会相遇，如果不相遇，则是一起走到终点
 *
 * 假设链表 A 未相交段的长度为 A1，链表 B 未相交段长度为 B1，相交段长度为 C
 * 则 A 链表长度 A = A1 + C
 *    B 链表长度 B = B1 + C
 * A + B = A1 + B1 + 2 * C
 * 两者一定会在 A1 + B1 + C 处相遇，因为他们会同时走到终点，所以也会在相交处相遇
 */
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {

    if (headA == null || headB == null) return null

    var p = headA
    var q = headB

    listOf(1, 2)

    while (!(p == null && q == null)) {
        if (p == null) {
            p = headB
        }
        if (q == null) {
            q = headA
        }
        if (p == q) {
            return p
        }
        p = p.next
        q = q.next
    }
    return null
}

/**
 * 先遍历一遍，得到长度差值，根据长度差值找到相交点
 */
fun getIntersectionNode2(headA: ListNode?, headB: ListNode?): ListNode? {
    fun ListNode?.foreach(block: (ListNode) -> Unit) {
        var cur = this
        while (cur != null) {
            block(cur)
            cur = cur.next
        }
    }

    var n = 0
    headA.foreach { n++ }
    headB.foreach { n-- }
    // 此时 n 的绝对值即链表 A 与 链表 B 的长度差值
    // 长链表先走差值
    var long = if (n > 0) headA else headB
    var short = if (n > 0) headB else headA
    repeat(abs(n)) {
        long = long?.next
    }
    while (long != null) {
        if (short == long) return long
        short = short?.next
        long = long?.next
    }
    return null
}

/**
 * 如果两个链表可能有环
 * 只可能两个链表都有环，不可能只有一个链表有环，这种情况不可能相交
 * 则不能通过遍历两条链表长度实现
 *
 * 情况分析
 * 1. 两者都有环，且不相交，（两者入环点不同，且一个链表遍历完一圈都无法遇到另一个链表的入环点）
 * 2. 两者都有环，且在入环前相交（入环节点是同一个），可以转化为求无环链表相交。
 * 3. 两者都有环，但入环节点不是同一个，两者分别指向环上不同节点（两者入环点不同，
 * 一个链表遍历一圈的过程中会遇到另一个链表的入环点）
 *
 * 情况 3 有两个相交节点，两个入环点都属于相交节点
 * 只可能有以上三种情况
 */
fun getIntersectionNodeWithCircle(headA: ListNode?, headB: ListNode?): ListNode? {
    TODO()
}