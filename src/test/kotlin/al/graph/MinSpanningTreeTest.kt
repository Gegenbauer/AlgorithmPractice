package al.graph

import kotlin.test.Test

class MinSpanningTreeTest {

    @Test
    fun testKruskal() {
        val tree = generateGraph()
        val minSpanningTree = MinSpanningTree().kruskal(tree)
        println(minSpanningTree.map { "${it.from.value}:${it.to.value}" })
    }

    @Test
    fun testPrim() {
        val tree = generateGraph()
        val minSpanningTree = MinSpanningTree().prim(tree)
        println(minSpanningTree.map { "${it.from.value}:${it.to.value}" })
    }
}