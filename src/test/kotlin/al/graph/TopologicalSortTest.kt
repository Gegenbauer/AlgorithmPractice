package al.graph

import al.graph.TopologicalSortJ.DirectedGraphNode
import al.util.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class TopologicalSortTest {

    @Test
    fun test() {
        val graph = ArrayList<DirectedGraphNode>()
        val node0 = DirectedGraphNode(0)
        val node1 = DirectedGraphNode(1)
        val node2 = DirectedGraphNode(2)
        val node3 = DirectedGraphNode(3)
        val node4 = DirectedGraphNode(4)
        val node5 = DirectedGraphNode(5)
        node0.neighbors.add(node1)
        node0.neighbors.add(node2)
        node0.neighbors.add(node3)

        node1.neighbors.add(node4)

        node2.neighbors.add(node4)
        node2.neighbors.add(node5)

        node3.neighbors.add(node4)
        node3.neighbors.add(node5)
        graph.add(node0)
        graph.add(node1)
        graph.add(node2)
        graph.add(node3)
        graph.add(node4)
        graph.add(node5)
        val result = TopologicalSortJ().topSort(graph)
        assertTrue { isArrayEquals(intArrayOf(0, 1, 2, 3, 4, 5), result.map { it.label }.toIntArray()) }
    }
}