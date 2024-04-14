package al.graph

import al.util.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class GraphVisitorTest {

    @Test
    fun testBfs1() {
        val graph = generateGraph()
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.bfs(graph.nodes[0]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(0, 1, 2, 5, 3, 4)) }
    }

    @Test
    fun testBfs2() {
        val graph = generateGraph()
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.bfs2(graph.nodes[0]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(0, 1, 2, 5, 3, 4)) }
    }

    @Test
    fun testDfs() {
        val graph = generateGraph()
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.dfs(graph.nodes[0]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(0, 1, 3, 2, 4, 5)) }
    }

    @Test
    fun testDfs2() {
        val graph = generateGraph()
        val result = mutableListOf<Int>()
        val visitor = GraphVisitor()
        visitor.dfs2(graph.nodes[0]!!) {
            result.add(it.value)
            true
        }
        assertTrue { isArrayEquals(result.toIntArray(), intArrayOf(0, 1, 3, 2, 4, 5)) }
    }
}