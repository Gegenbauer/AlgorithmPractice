package al.linkedlist

/**
 * leetcode-83 删除排序链表中的重复元素
 *
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 */
fun deleteDuplicates(head: ListNode?): ListNode? {
    var cur = head
    var pre: ListNode? = null

    while (cur != null) {
        while (cur != null && cur.`val` == pre?.`val`) {
            cur = cur.next
        }
        pre?.next = cur
        pre = cur
        cur = cur?.next
    }

    return head
}