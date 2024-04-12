package al.disjointset

import kotlin.test.Test
import kotlin.test.assertEquals

class CountOfIslandTest {

    @Test
    fun test1() {
        val grid = arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0'),
        )

        val count = CountOfIsland().numIslands(grid)
        assertEquals(1, count)
    }

    @Test
    fun test2() {
        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1'),
        )

        val count = CountOfIsland().numIslands(grid)
        assertEquals(3, count)
    }

    @Test
    fun test3() {
        val grid = arrayOf(
            charArrayOf('1', '1', '1'),
            charArrayOf('0', '1', '0'),
            charArrayOf('1', '1', '1'),
        )

        val count = CountOfIsland().numIslands(grid)
        assertEquals(1, count)
    }

    @Test
    fun test4() {
        val grid = arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0'),
        )

        val count = CountOfIsland().numIslands(grid)
        assertEquals(1, count)
    }
}