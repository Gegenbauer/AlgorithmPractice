package al.sort.merge

import al.linkedlist.ListNode

/**
 * leetcode-23 合并 K 个升序链表
 */
class MergeKList {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param lists ListNode类一维数组
     * @return ListNode类
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        return merge(lists, 0, lists.size - 1)
    }

    private fun merge(lists: Array<ListNode?>, start: Int, end: Int): ListNode? {
        if (start == end) {
            return lists[start]
        }
        val mid = start + ((end - start) shr 1)
        val left = merge(lists, start, mid)
        val right = merge(lists, mid + 1, end)

        return merge(left, right)
    }

    private fun merge(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        val dummy = ListNode(0)
        var cur: ListNode? = dummy
        var p1: ListNode? = list1
        var p2: ListNode? = list2
        while (p1 != null && p2 != null) {
            val newValue: Int
            if (p1.`val` <= p2.`val`) {
                newValue = p1.`val`
                p1 = p1.next
            } else {
                newValue = p2.`val`
                p2 = p2.next
            }
            cur?.next = ListNode(newValue)
            cur = cur?.next
        }
        while (p1 != null) {
            cur?.next = ListNode(p1.`val`)
            cur = cur?.next
            p1 = p1.next
        }
        while (p2 != null) {
            cur?.next = ListNode(p2.`val`)
            cur = cur?.next
            p2 = p2.next
        }
        return dummy.next
    }
}