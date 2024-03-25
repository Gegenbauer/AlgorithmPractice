package al.linkedlist

import jdk.jshell.spi.ExecutionControl.NotImplementedException


fun IntArray.toSingleNodeLinkedList(): ListNode? {
    if (isEmpty()) return null
    val head = ListNode(get(0))

    var cur: ListNode? = head
    for (i in 1 until size) {
        val node = ListNode(get(i))
        cur?.next = node
        cur = node
    }
    return head
}

fun IntArray.toDoubleNodeLinkedList(): DoubleNode? {
    if (isEmpty()) return null

    var cur: DoubleNode? = null
    var head: DoubleNode? = null
    for (i in indices) {
        val newNode = DoubleNode(get(i))
        if (head == null) head = newNode
        cur?.next = newNode
        newNode.last = cur
        cur = newNode
    }
    return head
}

fun Array<String>.toSingleNodeLinkedList(): ListNode? {
    throw NotImplementedException("")
}

fun isLinkedListEquals(node1: ListNode?, node2: ListNode?): Boolean {
    var p = node1
    var q = node2
    while (p != null && q != null) {
        if (p.`val` != q.`val`) {
            return false
        }
        p = p.next
        q = q.next
    }

    return p?.`val` == q?.`val`
}

fun isLinkedListEquals(node1: DoubleNode?, node2: DoubleNode?): Boolean {
    var p = node1
    var q = node2

    var tailP: DoubleNode? = null
    var tailQ: DoubleNode? = null
    while (p != null && q != null) {
        if (p.`val` != q.`val`) {
            return false
        }
        if (p.next == null) {
            tailP = p
        }
        if (q.next == null) {
            tailQ = q
        }
        p = p.next as? DoubleNode
        q = q.next as? DoubleNode
    }

    while (p != null || q != null) {
        if (p != null && p.next == null) {
            tailP = p
        }
        if (q != null && q.next == null) {
            tailQ = q
        }
        p = p?.next as? DoubleNode
        q = q?.next as? DoubleNode
    }

    if (p?.`val` == q?.`val`) {
        p = tailP
        q = tailQ

        while (p != null && q != null) {
            if (p.`val` != q.`val`) {
                return false
            }

            p = p.last
            q = q.last
        }

        if (p?.`val` != q?.`val`) {
            return false
        }
    }

    return true
}

fun ListNode?.toIntArray(): IntArray {
    if (this == null) return IntArray(0)

    val arr = IntArray(size)

    var cur = this
    for (i in arr.indices) {
        arr[i] = cur!!.`val`
        cur = cur.next
    }

    return arr
}

inline val ListNode?.size: Int
    get() {
        var cur = this
        var len = 0
        while (cur != null) {
            len++
            cur = cur.next
        }
        return len
    }