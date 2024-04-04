package al.linkedlist

/**
 * 从链表中找到中间节点
 * 如果为奇数，则链表只有一个中间节点，如果为偶数，则有两个中间节点
 *
 */
class FindMiddleNode {

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * a -> b -> c -> null
     * p1   p2   p3
     * q1        q2
     *
     * a -> b -> c -> d -> null
     * p1   p2   p3
     * q1        q2
     */
    fun findUpperMiddleNode(head: ListNode?): ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * a -> b -> c -> null
     * p1   p2   p3
     * q1        q2
     *
     * a -> b -> c -> d
     * p1   p2   p3
     * q1        q2   q3
     */
    fun findLowerMiddleNode(head: ListNode?): ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 链表长度至少为 3
     */
    fun findLastUpperMiddleNode(head: ListNode?): ListNode? {
        val dummy = ListNode(0, head)
        var slow: ListNode? = dummy
        var fast: ListNode? = head

        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 链表长度至少为 3
     */
    fun findLastLowerMiddleNode(head: ListNode?): ListNode? {
        val dummy = ListNode(0, head)
        var slow: ListNode? = dummy
        var fast: ListNode? = head

        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        return slow
    }
}