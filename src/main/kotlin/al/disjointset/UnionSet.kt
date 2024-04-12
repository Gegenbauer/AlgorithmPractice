package al.disjointset

import java.util.Stack

class Node<T>(val value: T)

/**
 * 初始传入的每个值都将作为单独的集合的根节点
 */
class UnionSet<T>(values: List<T>) {
    /**
     * 调用方传入的 key : 包装成的节点
     */
    private val nodes = hashMapOf<T, Node<T>>()

    /**
     * 节点 : 父节点（这样就不需要通过指针来建立父子关系）
     */
    private val parents = hashMapOf<Node<T>, Node<T>>()

    /**
     * 集合代表节点 : 集合当前大小
     */
    private val sizeMap = hashMapOf<Node<T>, Int>()

    init {
        values.forEach {
            Node(it).apply {
                nodes[it] = this
                parents[this] = this
                sizeMap[this] = 1
            }
        }
    }

    /**
     * 寻找集合的代表节点
     * 寻找代表节点的同时，将整条链扁平化（即遇到的所有节点都指向根节点），降低复杂度
     */
    fun getParent(value: Node<T>): Node<T> {
        val stack = Stack<Node<T>>()
        var cur = value
        while (parents[cur] != cur) {
            stack.push(cur)
            cur = parents[cur]!!
        }
        val root = stack.peek()
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            parents[node] = root
        }
        return root
    }

    fun isSameSet(a: T, b: T): Boolean {
        return getParent(nodes[a]!!) == getParent(nodes[b]!!)
    }

    /**
     * 1. 将小集合的 parent 指向 大集合
     * 2. 将小集合的大小从 [sizeMap] 中删除
     * 3. 更新大集合在 [sizeMap] 中的大小
     */
    fun union(a: T, b: T) {
        val aRoot = getParent(nodes[a]!!)
        val bRoot = getParent(nodes[b]!!)
        if (aRoot == bRoot) return

        val aSize = sizeMap[nodes[a]!!]!!
        val bSize = sizeMap[nodes[b]!!]!!
        var largeSetRoot = aRoot
        var smallSetRoot = bRoot
        if (aSize < bSize) {
            largeSetRoot = bRoot
            smallSetRoot = aRoot
        }
        parents[smallSetRoot] = largeSetRoot
        sizeMap[largeSetRoot] = aSize + bSize
        sizeMap.remove(smallSetRoot)
    }

    fun setCount(): Int {
        return sizeMap.size
    }
}