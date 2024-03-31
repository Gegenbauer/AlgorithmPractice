package al.linkedlist

/**
 * leetcode-21 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var q: ListNode? = list1
    var p: ListNode? = list2

    val dummy = ListNode(0)
    var resultCur = dummy

    fun addNode(value: Int) {
        val newNode = ListNode(value)
        resultCur.next = newNode
        resultCur = newNode
    }

    while (q != null && p != null) {
        val newValue = if (q.`val` < p.`val`) {
            q.`val`.also {
                q = q!!.next
            }
        } else {
            p.`val`.also {
                p = p!!.next
            }
        }

        addNode(newValue)
    }

    fun addListToResult(old: ListNode?) {
        var oldCur: ListNode? = old
        while (oldCur != null) {
            val newValue = oldCur.`val`
            addNode(newValue)
            oldCur = oldCur.next
        }
    }

    addListToResult(q)
    addListToResult(p)

    return dummy.next
}
