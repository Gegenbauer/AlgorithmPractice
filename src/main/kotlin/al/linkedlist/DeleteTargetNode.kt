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

    fun removeElement(head: ListNode?, target: ListNode): ListNode? {
        TODO()
    }

    /**
     * 不给头节点
     * 如果要删除的是末尾节点，是无法做到让其前一个节点指向空的
     * 所以要求不能是删除末尾节点。
     */
    fun removeElement(target: ListNode): ListNode? {
        TODO()
    }
}