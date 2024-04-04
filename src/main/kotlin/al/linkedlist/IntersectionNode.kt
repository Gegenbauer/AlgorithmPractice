package al.linkedlist

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
 * 如果两个链表可能有环
 * 则不能通过遍历两条链表长度实现
 */
fun getIntersectionNode2(headA: ListNode?, headB: ListNode?): ListNode? {
    TODO()
}