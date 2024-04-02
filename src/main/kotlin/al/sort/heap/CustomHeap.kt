package al.sort.heap

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

interface Heap<T> {
    /**
     * 堆的最大容量
     */
    val maxSize: Int

    /**
     * 堆是否已满
     */
    fun isFull(): Boolean

    /**
     * 堆是否为空
     */
    fun isEmpty(): Boolean

    /**
     * 查看堆的根节点
     */
    fun peek(): T

    /**
     * 向堆插入新的元素
     */
    fun push(value: T)

    /**
     * 获取堆根节点元素，并将其删除
     */
    fun pop(): T

    /**
     * 获取堆中所有元素
     */
    fun getAllElements(): List<T>

    /**
     * 检查堆中是否包含指定元素
     */
    fun contains(value: T): Boolean

    /**
     * 从堆中移除指定元素
     */
    fun remove(value: T): Boolean

    /**
     * 节点值发生变化
     */
    fun resign(value: T)

}

/**
 * 系统提供的堆仅支持下面能力
 * 1. 插入一个元素，保持堆的结构
 * 2. 弹出堆顶元素，保持堆的结构
 *
 * 不支持的能力
 * 1. 已经入堆的元素，如果参与排序的指标方法变化，（或者某个元素值发生变化）
 * 无法做到时间复杂度 O(logN) 调整，都是 O(N) 的调整
 * 2. 只能弹出堆顶，做不到自由删除任何一个堆中的元素，
 * 或者说，无法在时间复杂度 O(logN) 内完成，一定会高于 O(logN)
 * 根本原因：无反向索引表
 *
 * comparator 默认升序
 * heap 实现默认按小根堆实现
 */
class CustomHeap<T>(override val maxSize: Int, private val comparator: Comparator<T>) : Heap<T> {
    // 存储堆中数据的容器，最大容量是 maxSize
    private val data = ArrayList<T>(maxSize)

    // 表示堆的大小
    private var size = 0

    /**
     * 堆中插入数据
     * 插入后检查是否大于父节点，大于则与父节点交换，然后再检查父节点，依次往上。
     */
    override fun push(value: T) {
        check(!isFull()) { "Heap is full" }
        data[size] = value
        size++
        heapifyUp(size - 1) // 最后一个元素，当前 size - 1
    }

    override fun peek(): T {
        check(!isEmpty()) { "Heap is empty" }
        return data[0]
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
     */
    override fun pop(): T {
        check(!isEmpty()) { "Heap is empty" }
        return data[0].also {
            swap(data, 0, size - 1)
            size--
            heapifyDown(0)
        }
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun isFull(): Boolean {
        return size == maxSize
    }

    override fun getAllElements(): List<T> {
        return data.slice(0 until size)
    }

    override fun resign(value: T) {
        TODO("Not yet implemented")
    }

    override fun remove(value: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(value: T): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 向上检查
     * 尽量避免递归
     */
    private fun heapifyUp(index: Int) {
        var cur = index

        while (cur > 0) {
            val parent = (cur - 1) / 2
            if (compare(cur, parent) < 0) {
                swap(data, cur, parent)
            }
            cur = parent
        }
    }

    private fun heapifyDown(index: Int) {
        var cur = index

        while (cur < size) {
            val left = 2 * cur + 1
            if (left >= size) break

            val right = left + 1
            val minChild = if (right >= size) {
                left
            } else if (compare(right, left) < 0) {
                right
            } else {
                left
            }

            if (compare(minChild, cur) < 0) {
                swap(data, minChild, cur)
                cur = minChild
            } else {
                break
            }
        }
    }

    private fun swap(arr: MutableList<T>, index1: Int, index2: Int) {
        val temp = arr[index2]
        arr[index2] = arr[index1]
        arr[index1] = temp
    }

    private fun compare(index1: Int, index2: Int): Int {
        return comparator.compare(data[index1], data[index2])
    }
}