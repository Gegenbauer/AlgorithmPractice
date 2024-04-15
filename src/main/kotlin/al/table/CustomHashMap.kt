package al.table

import java.util.LinkedList

/**
 * leetcode-706 设计哈希映射
 */
class CustomHashMap {
    private val data = Array<LinkedList<Node>?>(BASE) { null }

    fun put(key: Int, value: Int) {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            val newList = LinkedList<Node>()
            data[bucketIndex] = newList
            newList.add(Node(key, value))
        }
        val bucket = data[bucketIndex]!!
        for (node in bucket) {
            if (node.key == key) {
                node.value = value
                return
            }
        }
        bucket.add(Node(key, value))
    }

    fun get(key: Int): Int {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            return -1
        }
        val bucket = data[bucketIndex]!!
        for (node in bucket) {
            if (node.key == key) {
                return node.value
            }
        }
        return -1
    }

    fun remove(key: Int) {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            return
        }
        val bucket = data[bucketIndex]!!
        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (next.key == key) {
                iterator.remove()
                break
            }
        }
    }

    private class Node(
        val key: Int,
        var value: Int,
    )

    private fun hash(key: Int): Int {
        return key % BASE
    }

    companion object {
        private const val BASE = 10009
    }
}

/**
 * leetcode-705 设计哈希集合
 */
class CustomHashSet {
    private val data = Array<LinkedList<Int>?>(BASE) { null }

    fun add(key: Int) {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            data[bucketIndex] = LinkedList()
        }
        val bucket = data[bucketIndex]!!
        for (savedKey in bucket) {
            if (savedKey == key) {
                return
            }
        }
        bucket.add(key)
    }

    fun remove(key: Int) {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            return
        }
        val bucket = data[bucketIndex]!!
        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (next == key) {
                iterator.remove()
                break
            }
        }
    }

    fun contains(key: Int): Boolean {
        val bucketIndex = hash(key)
        if (data[bucketIndex] == null) {
            return false
        }
        val bucket = data[bucketIndex]!!
        for (savedKey in bucket) {
            if (savedKey == key) {
                return true
            }
        }
        return false
    }

    private fun hash(key: Int): Int {
        return key % BASE
    }

    companion object {
        private const val BASE = 10009
    }
}