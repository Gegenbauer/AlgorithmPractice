package al.disjointset

/**
 * leetcode-200 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
class CountOfIsland {

    fun numIslands(grid: Array<CharArray>): Int {
        return unionFind(grid)
    }

    /**
     * 递归感染的方式，遍历过的岛屿都标记为 '2', 直到整个岛屿遍历完
     * 时间复杂度为 O(m * n)
     * 因为能确保每个位置只会遍历一次
     */
    private fun infect(grid: Array<CharArray>): Int {
        var ans = 0
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j)
                    ans++
                }
            }
        }
        return ans
    }

    private fun infect(grid: Array<CharArray>, i: Int, j: Int) {
        if (i !in grid.indices || j !in grid[0].indices) return
        if (grid[i][j] != '1') return

        grid[i][j] = '2'
        infect(grid, i, j + 1)
        infect(grid, i + 1, j)
        infect(grid, i - 1, j)
        infect(grid, i, j - 1)
    }


    private fun unionFind(grid: Array<CharArray>): Int {
        val unionSet = UnionSet(grid)
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] != '1') continue
                if (i > 0 && grid[i - 1][j] == '1') {
                    unionSet.union(i, j, i - 1, j)
                }
                if (j > 0 && grid[i][j - 1] == '1') {
                    unionSet.union(i, j, i, j - 1)
                }
            }
        }
        return unionSet.setSize
    }

    private class UnionSet(grid: Array<CharArray>) {

        private val maxRow = grid.size
        private val maxColumn = grid.firstOrNull()?.size ?: 0
        private val parents = IntArray(maxRow * maxColumn)
        private val sizes = IntArray(maxRow * maxColumn)
        private val help = IntArray(maxRow * maxColumn)
        var setSize = 0

        init {
            for (i in grid.indices) {
                for (j in grid[0].indices) {
                    if (grid[i][j] != '1') continue
                    val node = i * maxColumn + j
                    parents[node] = node
                    sizes[node] = 1
                    setSize++
                }
            }
        }

        /**
         *
         */
        fun findRoot(x: Int): Int {
            var cur = x
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

        fun union(row1: Int, col1: Int, row2: Int, col2: Int) {
            val a = row1 * maxColumn + col1
            val b = row2 * maxColumn + col2
            val aRoot = findRoot(a)
            val bRoot = findRoot(b)
            if (aRoot == bRoot) return
            val aSize = sizes[a]
            val bSize = sizes[b]
            var largeRoot = aRoot
            var smallRoot = bRoot
            if (bSize > aSize) {
                largeRoot = bRoot
                smallRoot = aRoot
            }
            parents[smallRoot] = largeRoot
            sizes[smallRoot] = 0
            sizes[largeRoot] = aSize + bSize
            setSize--
        }
    }
}

/**
 * 初始矩阵都是0，每次指定一个位置为 1，求每次指定 1 后，当前岛屿数量
 * 需要注意 union 后 size 要保留，区分是否为 0
 * 如果 size 为 0，需要创建新集合。
 * 而且每次新指定 1 后，需要检查上下左右四个位置是否可以合并。
 */
class CountOfIslandII {

}