package al.sort.heap

import al.sort.simple.swap
import java.util.*
import kotlin.random.Random

/**
 * 实际通过 优先级队列 PriorityQueue(默认为小根堆) 来使用堆
 *
 * # 度：子节点的数目
 * # 深度：层次
 * # 叶子节点：度为 0 的节点
 * # 二叉树：每个节点最多拥有的子节点数为 2
 *
 * # 满二叉树：每个节点，除了叶子节点，都有两个子节点的特殊二叉树
 * ## 深度为 n，第 n 层节点数为 2 ^ (n - 1)
 * ## 深度为 n，节点总数为 2 ^ n - 1
 *
 * # 完全二叉树，除了最后一层有缺失外，其他层的节点都是满的，而且最后一层缺失的节点都在右侧
 * ## 深度为 n，最后一层节点数最多为 2 ^ (n - 1)
 * ## 深度为 n，节点总数最多为 为 2 ^ n - 1
 * ## 节点数为 n，层数最多为 log2(n)
 * ## 叶节点数量为 n / 2
 *
 *
 * # 将一个数组转化为一颗完全二叉树，即认为是一棵二叉树的层序遍历结果
 * ## 任何一个 i 位置，左子节点为 2 * i + 1，右子节点为 2 * i + 2
 * ## 任何一个 i 位置，父节点为 (i - 2) / 2 => 向下取整
 *
 * # 堆
 * ## 是一棵完全二叉树，分为大根堆和小根堆
 * ## 每一棵子树的最大值，都是子树头部节点的值 ==> 大根堆
 * ## 每一棵子树的最小值，都是子树头部节点的值 ==> 小根堆
 *
 */
class Heap(private val maxSize: Int, private val comparator: Comparator<Int>) {
    // 存储堆中数据的容器，最大容量是 maxSize
    private val data = IntArray(maxSize + 1)

    // 表示堆的大小
    private var size = 0

    /**
     * 堆中插入数据
     * 插入后检查是否大于父节点，大于则与父节点交换，然后再检查父节点，依次往上。
     */
    fun heapInsert(value: Int) {
        data[size] = value
        checkParent(size)
        size++
        if (size > maxSize) {
            size--
            checkParent(size)
        }
    }

    /**
     * 向上检查
     * 尽量避免递归
     */
    private fun checkParent(node: Int) {

        var cur = node

        while (cur > 0) {
            val parent = (cur - 1) / 2
            if (comparator.compare(data[cur], data[parent]) < 0) {
                swap(cur, parent, data)
                cur = parent
            } else {
                break
            }
        }
    }

    /**
     * 返回堆中的最大值，并将其从堆中删除。
     *
     * 1. 将最大值与堆中最后一个值交换
     * 即最大值来到了最后一个节点，最后一个节点来到了根节点
     *
     * 2. 将堆大小减 1，即代表删除最后一项。
     *
     * 3. 从根节点开始调整堆，使其满足大根堆
     *
     */
    fun getAndRemoveMax(): Int {
        val max = data[0]
        swap(0, size - 1, data)
        size--
        checkChildren(0)
        return max
    }

    /**
     * 向下检查
     */
    private fun checkChildren(node: Int) {
        var cur = node
        while (cur < size) {
            val leftChild = cur * 2 + 1
            val rightChild = cur * 2 + 2

            if (leftChild >= size) break

            val maxChild = if (rightChild >= size) {
                leftChild
            } else if (comparator.compare(data[leftChild], data[rightChild]) < 0) {
                leftChild
            } else {
                rightChild
            }

            if (comparator.compare(data[maxChild], data[cur]) < 0) {
                swap(maxChild, cur, data)
                cur = maxChild
            } else {
                break
            }
        }
    }

}

fun main() {
    val comparator = Comparator<Int> { o1, o2 -> (o2 ?: Int.MAX_VALUE) - (o1 ?: Int.MAX_VALUE) }
    val heap = Heap(1000, comparator)

    // 默认为小根堆
    val priorityQueue = PriorityQueue(1000, comparator)

    repeat(100) {
        val randomValue = Random.nextInt(0, 100)
        heap.heapInsert(randomValue)
        priorityQueue.add(randomValue)
    }

    println("expected: ${priorityQueue.poll()}, real: ${heap.getAndRemoveMax()}")
    println("expected: ${priorityQueue.poll()}, real: ${heap.getAndRemoveMax()}")
    println("expected: ${priorityQueue.poll()}, real: ${heap.getAndRemoveMax()}")
    println("expected: ${priorityQueue.poll()}, real: ${heap.getAndRemoveMax()}")
    println("expected: ${priorityQueue.poll()}, real: ${heap.getAndRemoveMax()}")
}