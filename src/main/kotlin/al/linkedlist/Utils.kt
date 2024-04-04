package al.linkedlist


/**
 * 尾插法
 */
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

fun List<Int>.toSingleNodeLinkedList(): ListNode? {
    return toIntArray().toSingleNodeLinkedList()
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
    TODO()
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
        // 为了从尾部重新遍历
        if (p.next == null) {
            tailP = p
        }
        if (q.next == null) {
            tailQ = q
        }
        p = p.next as? DoubleNode
        q = q.next as? DoubleNode
    }

    // p 或者 q 有一个仍然没有遍历到最后一个节点
    if (p != null || q != null) {
        return false
    }

    p = tailP
    q = tailQ

    while (p != null && q != null) {
        if (p.`val` != q.`val`) {
            return false
        }

        p = p.last
        q = q.last
    }

    return p?.`val` == q?.`val`
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