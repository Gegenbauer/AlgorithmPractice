package al.linkedlist

import al.sort.simple.swap

class LinkedListPartition {

    fun partition(head: ListNode?, target: Int) {
        if (head == null) return
        threePointers(head, target)
    }

    private fun bruteForce(head: ListNode?, target: Int) {
        val arr = ArrayList<Int>()
        var cur = head
        while (cur != null) {
            arr.add(cur.`val`)
            cur = cur.next
        }

        partition(arr, target)
        cur = head
        for (i in 0 until arr.size) {
            cur?.`val` = arr[i]
            cur = cur?.next
        }
    }

    private fun partition(arr: MutableList<Int>, target: Int) {
        var smallBound = -1
        var largeBound = arr.size

        var cur = 0
        while (cur < largeBound) {
            if (arr[cur] > target) {
                swap(arr, cur, --largeBound)
            } else if (arr[cur] < target) {
                swap(arr, cur++, ++smallBound)
            } else {
                cur++
            }
        }
    }

    private fun threePointers(head: ListNode?, target: Int) {
        val small = ListNode(0)
        val equal = ListNode(0)
        val large = ListNode(0)

        var smallCur: ListNode? = small
        var equalCur: ListNode? = equal
        var largeCur: ListNode? = large
        var cur = head
        while (cur != null) {
            if (cur.`val` < target) {
                smallCur?.next = cur
                smallCur = smallCur?.next
                cur = cur.next
                smallCur?.next = null
            } else if (cur.`val` > target) {
                largeCur?.next = cur
                largeCur = largeCur?.next
                cur = cur.next
                largeCur?.next = null
            } else {
                equalCur?.next = cur
                equalCur = equalCur?.next
                cur = cur.next
                equalCur?.next = null
            }
        }

        if (equalCur != equal) {
            smallCur?.next = equal.next
            equalCur?.next = large.next
        } else {
            smallCur?.next = large.next
        }

        head?.`val` = small.next?.`val` ?: 0
        head?.next = small.next?.next
    }

    /**
     * leetcode-86 分隔链表
     *
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     *
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     *
     * 示例 1：
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     *
     * 示例 2：
     * 输入：head = [2,1], x = 2
     * 输出：[1,2]
     *
     * 提示：
     *
     * 链表中节点的数目在范围 [0, 200] 内
     * -100 <= Node.val <= 100
     * -200 <= x <= 200
     */
    private fun twoPointers(head: ListNode?, target: Int) {
        val small = ListNode(0)
        val large = ListNode(0)

        var smallCur: ListNode? = small
        var largeCur: ListNode? = large
        var cur = head
        while (cur != null) {
            if (cur.`val` < target) {
                smallCur?.next = cur
                smallCur = smallCur?.next
                cur = cur.next
                smallCur?.next = null
            }  else {
                largeCur?.next = cur
                largeCur = largeCur?.next
                cur = cur.next
                largeCur?.next = null
            }
        }

        smallCur?.next = large.next

        head?.`val` = small.next?.`val` ?: 0
        head?.next = small.next?.next
    }
}