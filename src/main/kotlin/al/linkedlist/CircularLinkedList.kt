package al.linkedlist

/**
 * leetcode-141 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 快慢指针
 */
fun hasCircle(head: ListNode?): Boolean {
    val dummy = ListNode(0, head)
    var p: ListNode? = dummy
    var q: ListNode? = dummy.next

    while (p != null && q != null) {
        if (p == q) {
            return true
        }

        p = p.next
        q = q.next?.next
    }

    return false
}

/**
 * leetcode-142 环形链表 II
 *
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 * A(非环路径) + B(环一圈路径)
 * 2 * x - x = B
 * x = B, 说明快慢指针第一次相遇时，慢指针走了 B 的长度，快指针走了 2B
 * 如果在入环点相遇，要么指针走了 A, 要么走了 A + n * B, n 为绕环圈数
 * 所以让快指针回到起点开始走，慢指针继续走，两者离入环点的距离都是 A
 * 一定会相遇
 */
fun findEntryOfCircle(head: ListNode?): ListNode? {
    head?.next?.next ?: return null

    var fast: ListNode? = head
    var slow: ListNode? = head
    while (fast != null) {
        fast = fast.next?.next
        slow = slow?.next
        if (fast == slow) {
            fast = head
            while (true) {
                if (fast == slow) {
                    return slow
                }
                fast = fast?.next
                slow = slow?.next
            }
        }
    }
    return null
}