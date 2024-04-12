package al.disjointset

/**
 * 给定一个 n * n 的对称矩阵
 * (i, i) == 1 意味着 i 认识 j
 * (i, i) == 0 意味着 i 不认识 j
 * 朋友圈的概念是
 *      如果 i 认识 j，则 i 和 j 属于一个朋友圈
 *      如果 j 认识 k，则 i 和 j 和 k 属于一个朋友圈
 *
 * 求朋友圈的个数
 *
 * leetcode-547 省份数量
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 提示：
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
class SocialCircle {

    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val unionSet = UnionSet(isConnected.size)
        for (i in isConnected.indices) {
            for (j in i until isConnected.size) {
                if (isConnected[i][j] == 1) {
                    unionSet.union(i, j)
                }
            }
        }
        return unionSet.setSize()
    }

    /**
     * 二维矩阵的一个列表的索引作为 key 值，所以可以直接使用数组
     * 而且栈可以替换为数组
     */
    private class UnionSet(n: Int) {

        private val parents = IntArray(n)
        private val sizes = IntArray(n)
        private val help = IntArray(n)
        private var sets = 0

        init {
            for (i in 0 until n) {
                parents[i] = i
                sizes[i] = 1
                sets++
            }
        }

        fun getRoot(a: Int): Int {
            var cur = a
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

        fun union(a: Int, b: Int) {
            val rootA = getRoot(a)
            val rootB = getRoot(b)
            if (rootA == rootB) return

            val sizeA = sizes[rootA]
            val sizeB = sizes[rootB]
            var large = rootA
            var small = rootB
            if (sizeB > sizeA) {
                large = rootB
                small = rootA
            }

            parents[small] = large
            sizes[small] = 0
            sets--
            sizes[large] = sizeA + sizeB
        }

        fun setSize(): Int {
            return sets
        }
    }
}