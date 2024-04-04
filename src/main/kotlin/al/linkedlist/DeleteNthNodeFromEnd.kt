package al.linkedlist

/**
 * leetcode-19 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
class DeleteNthNodeFromEnd {

    /**
     * 两个快慢指针，步长一致，快指针先遍历 n+1 个节点，再一起遍历
     * 最终快指针遍历完，慢指针刚好指向需要删除的节点的前一个节点
     *
     * 有一种特殊情况需要注意，如果需要删除的是头节点，则慢指针无法指向其前一个节点
     * 所以需要引入一个哑节点
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var fast: ListNode? = head
        val dummy = ListNode(0, head)
        var slow: ListNode? = dummy
        repeat(n) {
            fast = fast?.next
        }
        while (fast != null) {
            slow = slow?.next
            fast = fast?.next
        }

        slow?.next = slow?.next?.next
        return dummy.next
    }
}