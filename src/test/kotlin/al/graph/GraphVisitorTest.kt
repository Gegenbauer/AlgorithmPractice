package al.graph

import al.util.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class GraphVisitorTest {

    @Test
    fun testBfs1() {
        val graph = generateGraph(arrayOf(
            intArrayOf(1, 1, 2),
            intArrayOf(1, 1, 3),
            intArrayOf(1, 1, 6),
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 2),
            intArrayOf(1, 3, 4),
            intArrayOf(1, 3, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 6, 5),
        ))
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.bfs(graph.nodes[1]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(1, 2, 3, 6, 4, 5)) }
    }

    @Test
    fun testBfs2() {
        val graph = generateGraph(arrayOf(
            intArrayOf(1, 1, 2),
            intArrayOf(1, 1, 3),
            intArrayOf(1, 1, 6),
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 2),
            intArrayOf(1, 3, 4),
            intArrayOf(1, 3, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 6, 5),
        ))
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.bfs2(graph.nodes[1]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(1, 2, 3, 6, 4, 5)) }
    }

    @Test
    fun testDfs() {
        val graph = generateGraph(arrayOf(
            intArrayOf(1, 1, 2),
            intArrayOf(1, 1, 3),
            intArrayOf(1, 1, 6),
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 2),
            intArrayOf(1, 3, 4),
            intArrayOf(1, 3, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 6, 5),
        ))
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.dfs(graph.nodes[1]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(1, 2, 4, 3, 5, 6)) }
    }

    @Test
    fun testDfs2() {
        val graph = generateGraph(arrayOf(
            intArrayOf(1, 1, 2),
            intArrayOf(1, 1, 3),
            intArrayOf(1, 1, 6),
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 2),
            intArrayOf(1, 3, 4),
            intArrayOf(1, 3, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 6, 5),
        ))
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.dfs2(graph.nodes[1]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(1, 2, 4, 3, 5, 6)) }
    }
}