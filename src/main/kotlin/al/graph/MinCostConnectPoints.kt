package al.graph

import java.util.*
import kotlin.math.abs

/**
 * leetcode-1584 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 *
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 *
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 *
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 *
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 */
class MinCostConnectPoints {

    /**
     * Kruskal
     */
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val unionSet = UnionSet(points)
        val priorityQueue = PriorityQueue(Comparator<Edge> { o1, o2 -> o1.distance - o2.distance })
        for (i in points.indices) {
            for (j in i until points.size) {
                priorityQueue.offer(Edge(points, i, j))
            }
        }
        var minCost = 0
        while (priorityQueue.isNotEmpty()) {
            val edge = priorityQueue.poll()
            if (unionSet.union(edge.point1, edge.point2)) {
                continue
            }
            minCost += edge.distance
        }
        return minCost
    }

    /**
     * Prim
     */
    fun minCostConnectPoints2(points: Array<IntArray>): Int {
        val priorityQueue = PriorityQueue(Comparator<Edge> { o1, o2 -> o1.distance - o2.distance })
        val nodeSet = hashSetOf<Int>()
        // 解锁初始点
        nodeSet.add(0)
        for (i in points.indices) {
            if (i in nodeSet) continue
            // 解锁初始点下的所有边
            priorityQueue.offer(Edge(points, 0, i))
        }
        var minCost = 0
        while (priorityQueue.isNotEmpty()) {
            val edge = priorityQueue.poll()
            if (edge.point2 in nodeSet) continue
            // 选择边
            minCost += edge.distance
            // 解锁下一个点
            nodeSet.add(edge.point2)
            for (i in points.indices) {
                if (i in nodeSet) continue
                // 解锁下一个点下的所有边
                priorityQueue.offer(Edge(points, edge.point2, i))
            }
        }
        return minCost
    }

    /**
     * 这种边定义方式值得复用
     */
    data class Edge(
        val point1: Int,
        val point2: Int,
        val distance: Int = 0
    ) {
        constructor(points: Array<IntArray>, point1: Int, point2: Int) : this(
            point1,
            point2,
            abs(points[point1][0] - points[point2][0]) + abs(points[point1][1] - points[point2][1])
        )
    }

    private class UnionSet(points: Array<IntArray>) {

        private val parents = IntArray(points.size)
        private val sizes = IntArray(points.size)
        private val help = IntArray(points.size)

        init {
            for (i in points.indices) {
                parents[i] = i
                sizes[i] = 1
            }
        }

        fun union(point1: Int, point2: Int): Boolean {
            val rootA = getRoot(point1)
            val rootB = getRoot(point2)
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

        fun getRoot(point: Int): Int {
            var cur = point
            var helpIndex = 0
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
    }
}