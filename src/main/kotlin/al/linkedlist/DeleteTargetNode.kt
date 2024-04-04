package al.linkedlist

class DeleteTargetNode {

    /**
     * 链表删除节点的操作技巧
     * 增加哑节点
     */
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        head ?: return null

        val dummy = ListNode(0, head)
        var pre: ListNode? = dummy
        var cur: ListNode? = head
        while(cur != null) {
            if (cur.`val` == `val`) {
                pre?.next = cur.next
            } else {
                pre = cur
            }
            cur = cur.next
        }
        return dummy.next
    }
}