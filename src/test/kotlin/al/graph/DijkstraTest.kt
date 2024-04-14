package al.graph

import kotlin.test.Test
import kotlin.test.assertEquals

class DijkstraTest {

    @Test
    fun test() {
        val graph = generateGraph()
        val result = Dijkstra().dijkstra(graph.nodes.values.first())
        val resultStr = result.toSortedMap{ o1, o2 -> o1.value - o2.value }.map { "0 -> ${it.key.value}: ${it.value}" }.joinToString("\n")
        val expected = """
            0 -> 0: 0
            0 -> 1: 3
            0 -> 2: 2
            0 -> 3: 6
            0 -> 4: 8
            0 -> 5: 7
        """.trimIndent()
        assertEquals(expected, resultStr)
    }

    @Test
    fun test2() {
        val graph = generateGraph()
        val result = Dijkstra().dijkstra2(graph.nodes.values.first())
        val resultStr = result.toSortedMap{ o1, o2 -> o1.value - o2.value }.map { "0 -> ${it.key.value}: ${it.value}" }.joinToString("\n")
        val expected = """
            0 -> 0: 0
            0 -> 1: 3
            0 -> 2: 2
            0 -> 3: 6
            0 -> 4: 8
            0 -> 5: 7
        """.trimIndent()
        assertEquals(expected, resultStr)
    }
}