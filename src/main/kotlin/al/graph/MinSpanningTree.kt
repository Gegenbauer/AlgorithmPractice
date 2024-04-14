package al.graph

import java.util.*

class MinSpanningTree {

    /**
     * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
     * 2）当前的边要么进入最小生成树的集合，要么丢弃
     * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
     * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
     * 5）考察完所有边之后，最小生成树的集合也得到了
     *
     * 由于这里选择边是按照权重优先级来选择
     * 没有路径上的先后顺序，因此只能通过并查集来确定新的边会不会形成环
     */
    fun kruskal(graph: Graph): Set<Edge> {
        val priorityQueue = PriorityQueue<Edge> { o1, o2 -> o1.weight - o2.weight }
        graph.edges.forEach { priorityQueue.offer(it) } // O(M * logM), M 为边条数
        val result = mutableSetOf<Edge>()
        val unionSet = UnionSet(graph.nodes.size)
        while (priorityQueue.isNotEmpty()) {  // M 次
            val edge = priorityQueue.poll() // O(logM)
            if (unionSet.union(edge.from.value, edge.to.value)) {
                continue
            }
            result.add(edge)
        }
        return result
    }

    /**
     * 1）可以从任意节点出发来寻找最小生成树
     * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
     * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
     * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
     * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
     * 6）当所有点都被选取，最小生成树就得到了
     *
     * 这里通过依次遍历节点的路径，找到新解锁的节点，所以可以通过哈希集合来判断新解锁的边是否会形成环
     */
    fun prim(graph: Graph): Set<Edge> {
        val priorityQueue = PriorityQueue<Edge> { o1, o2 -> o1.weight - o2.weight }
        // 已经解锁的点，避免重复解锁同一个点
        val nodeSet = mutableSetOf<Node>()

        val result = mutableSetOf<Edge>()

        // 防止图中有多个联通区域（森林）
        for (node in graph.nodes.values) {
            if (node in nodeSet) continue
            // 随便选择一个未解锁的节点进行解锁
            nodeSet.add(node)

            // 解锁这个节点下所有的边
            node.edges.forEach {
                priorityQueue.offer(it)
            }
            while (priorityQueue.isNotEmpty()) {
                // 根据权重优先级，找到一条解锁的边
                val edge = priorityQueue.poll()

                // 判断是否形成环
                if (edge.to !in nodeSet) {
                    // 解锁新的节点
                    result.add(edge)
                    nodeSet.add(edge.to)

                    // 继续解锁新节点下的所有边
                    edge.to.edges.forEach {
                        priorityQueue.offer(it)
                    }
                }
            }
        }

        return result
    }

    /**
     * 存储节点编号即可
     */
    private class UnionSet(n: Int) {

        private val parents = IntArray(n)
        private val sizes = IntArray(n)
        private val help = IntArray(n)

        init {
            for (i in 0 until n) {
                parents[i] = i
                sizes[i] = 1
            }
        }

        private fun getRoot(node: Int): Int {
            var helpIndex = 0
            var cur = node
            while (parents[cur] != cur) {
                help[helpIndex++] = cur
                cur = parents[cur]
            }
            val root = cur
            while (helpIndex > 0) {
                parents[help[--helpIndex]] = root
            }
            return root
        }

        fun union(node1: Int, node2: Int): Boolean {
            val rootA = getRoot(node1)
            val rootB = getRoot(node2)
            if (rootA == rootB) return true

            val sizeA = sizes[rootA]
            val sizeB = sizes[rootB]
            if (sizeA > sizeB) {
                parents[rootB] = rootA
                sizes[rootB] = 0
                sizes[rootA] = sizeA + sizeB
            } else {
                parents[rootA] = rootB
                sizes[rootA] = 0
                sizes[rootB] = sizeA + sizeB
            }
            return false
        }
    }
}