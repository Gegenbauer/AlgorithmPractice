package al.linkedlist

/**
 * 链表相关的问题几乎都是 coding 问题
 * 1) 单链表和双链表如何反转
 * 2) 删除指定值
 * ...
 */

open class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class DoubleNode(
    value: Int,
    next: DoubleNode? = null,
    var last: DoubleNode? = null
) : ListNode(value, next)

/**
 * 切记要返回 pre，不能直接使用传入的 node 作为反转后链表的头节点
 */
fun reverseSingleNodeLinkedList(node: ListNode?): ListNode? {
    var pre: ListNode? = null
    var cur: ListNode? = node

    while (cur != null) {
        val temp = cur.next
        cur.next = pre
        pre = cur
        cur = temp
    }

    return pre
}

fun reverseDoubleNodeLinkedList(node: DoubleNode?): DoubleNode? {
    var cur: DoubleNode? = node
    var head: DoubleNode? = null
    while (cur != null) {
        val temp = cur.last
        cur.last = (cur.next as? DoubleNode)
        cur.next = temp

        if (cur.last == null) {
            head = cur
        }
        cur = cur.last
    }

    return head
}
