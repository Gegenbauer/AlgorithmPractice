package al.graph

import kotlin.math.min

/**
 * 求节点间最小加权路径和
 * dijkstra 本质上也是贪心
 */
class Dijkstra {

    /**
     * 返回指定节点到图中其他节点的最短加权路径和
     */
    fun dijkstra(start: Node): Map<Node, Int> {
        val distances = hashMapOf<Node, Int>()
        val lockedNode = hashSetOf<Node>()
        distances[start] = 0

        fun getUnSelectedMinDistanceNode(): Node? {
            return distances.filter { it.key !in lockedNode }.minByOrNull { it.value }?.key
        }

        while (lockedNode.size != distances.size) {
            val minDistanceNode = getUnSelectedMinDistanceNode() // O(N)
            minDistanceNode ?: break
            minDistanceNode.edges.forEach {
                distances[it.to] = min(distances[it.to] ?: Int.MAX_VALUE, it.weight + distances[it.from]!!)
            }
            lockedNode.add(minDistanceNode)
        }

        return distances
    }

    /**
     * 利用加强堆优化
     */
    fun dijkstra2(start: Node): Map<Node, Int> {
        val result = hashMapOf<Node, Int>()
        val heap = NodeHeap(5000)
        heap.putOrCreate(start, 0)
        while (heap.isEmpty().not()) {
            val minDistanceNode = heap.pop() // O(logN)
            val distance = heap.getDistance(minDistanceNode)
            minDistanceNode.edges.forEach {
                heap.putOrCreate(it.to, distance + it.weight)
            }
            result[minDistanceNode] = heap.getDistance(minDistanceNode)
        }
        return result
    }

    private class NodeHeap(maxSize: Int) {
        private val nodes = Array<Node?>(maxSize) { null }

        // 反向索引，找到节点在堆中的索引
        // 不在表中的节点表示从未加入过堆
        // 在表中且索引为负数的节点表示已经从堆中移除
        private val indexes = hashMapOf<Node, Int>()

        private val distances = hashMapOf<Node, Int>()
        private var size = 0

        fun putOrCreate(node: Node, distance: Int) {
            val index = indexes[node]
            if (index != null && index < 0) {
                return
            }
            if (index == null) {
                nodes[size] = node
                distances[node] = distance
                indexes[node] = size
                size++
                heapifyUp(size - 1)
            } else {
                val oldDistance = distances[node]
                if (oldDistance != null && oldDistance < distance) {
                    return
                }
                distances[node] = distance
                heapifyDown(index)
                heapifyUp(index)
            }
        }

        private fun heapifyDown(index: Int) {
            val left = 2 * index + 1
            val right = 2 * index + 2

            if (left >= size) return

            val minChild = if (right < size) {
                val leftDistance = distances[nodes[left]]!!
                val rightDistance = distances[nodes[right]]!!
                if (leftDistance > rightDistance) {
                    right
                } else {
                    left
                }
            } else {
                left
            }
            val parentDistance = distances[nodes[index]]!!
            val minChildDistance = distances[nodes[minChild]]!!
            if (minChildDistance < parentDistance) {
                swap(index, minChild)
                heapifyDown(minChild)
            }
        }

        private fun swap(index1: Int, index2: Int) {
            val node1 = nodes[index1]!!
            val node2 = nodes[index2]!!
            indexes[node1] = index2
            indexes[node2] = index1
            val temp = node1
            nodes[index1] = node2
            nodes[index2] = temp
        }

        private fun heapifyUp(index: Int) {
            val parent = (index - 1) / 2
            if (index <= 0) return
            val parentDistance = distances[nodes[parent]]!!
            val currentDistance = distances[nodes[index]]!!
            if (parentDistance > currentDistance) {
                swap(parent, index)
                heapifyUp(parent)
            }
        }

        fun pop(): Node {
            swap(0, size - 1)
            indexes[nodes[size - 1]!!] = -1
            size--
            heapifyDown(0)
            return nodes[size]!!
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun getDistance(node: Node): Int {
            return distances[node] ?: -1
        }
    }
}