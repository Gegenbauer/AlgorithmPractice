package al.stackorqueue

import al.linkedlist.ListNode
import java.util.*

/**
 * leetcode 622 设计循环队列
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *
 *
 * 提示：
 *
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 *
 *
 *
 * Your MyCircularQueue object will be instantiated and called as such:
 * var obj = MyCircularQueue(k)
 * var param_1 = obj.enQueue(value)
 * var param_2 = obj.deQueue()
 * var param_3 = obj.Front()
 * var param_4 = obj.Rear()
 * var param_5 = obj.isEmpty()
 * var param_6 = obj.isFull()
 *
 */
class MyCircularQueue(k: Int) {

    // 使用单链表实现
    private var head: ListNode? = null
    private var tail: ListNode? = null
    private var size = 0
    private val capacity = k

    fun enQueue(value: Int): Boolean {
        if (isFull()) {
            return false
        }

        val newNode = ListNode(value)
        if (head == null) {
            tail = newNode
            head = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) {
            return false
        }

        head = head?.next

        size--
        return true
    }

    fun Front(): Int {
        return head?.`val` ?: -1
    }

    fun Rear(): Int {
        return tail?.`val` ?: -1
    }

    fun isEmpty(): Boolean {
        return head == null
    }

    fun isFull(): Boolean {
        return size >= capacity
    }

}

class MyCircularQueue2(k: Int) {

    private val data = IntArray(k)
    private var begin = 0
    private var end = 0
    private var size = 0
    private val capacity = k

    fun enQueue(value: Int): Boolean {
        if (size >= capacity) return false

        data[end] = value
        end++
        size++
        formalizePointers()
        return true
    }

    private fun formalizePointers() {
        begin = formalizeIndex(begin)
        end = formalizeIndex(end)
    }

    private fun formalizeIndex(index: Int): Int {
        if (index == capacity) {
            return 0
        }
        if (index == -1) {
            return capacity - 1
        }
        return index
    }

    fun deQueue(): Boolean {
        if (size <= 0) {
            return false
        }
        data[begin]
        begin++
        size--
        formalizePointers()
        return true
    }

    fun Front(): Int {
        if (isEmpty()) {
            return -1
        }

        return data[begin]
    }

    fun Rear(): Int {
        if (isEmpty()) {
            return -1
        }

        return data[formalizeIndex(end - 1)]
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun isFull(): Boolean {
        return size == capacity
    }

}

/**
 * leetcode 232 用栈实现队列
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 *
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用 100 次 push、pop、peek 和 empty
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
 *
 *
 * 进阶：
 *
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 *
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 *
 */
class MyQueue() {
    val pushStack = Stack<Int>()
    val popStack = Stack<Int>()

    fun push(x: Int) {
        pushStack.push(x)
    }

    fun pop(): Int {
        ensureNotEmpty()
        if (popStack.isEmpty()) {
            dumpToPopStack()
        }
        return popStack.pop()
    }

    private fun dumpToPopStack() {
        while (pushStack.isNotEmpty()) {
            popStack.push(pushStack.pop())
        }
    }

    fun peek(): Int {
        ensureNotEmpty()
        if (popStack.isEmpty()) {
            dumpToPopStack()
        }
        return popStack.peek()
    }

    private fun ensureNotEmpty() {
        require(pushStack.isNotEmpty() || popStack.isNotEmpty()) {
            "queue is empty"
        }
    }

    fun empty(): Boolean {
        return pushStack.isEmpty() && popStack.isEmpty()
    }

}