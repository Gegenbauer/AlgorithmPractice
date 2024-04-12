package al.graph

/**
 * 没有出度入度，可能也没有权重
 */
class Node(
    val value: Int,
    /**
     * 入度：有多少条边指向当前节点
     */
    var `in`: Int = 0,

    /**
     * 出度：该节点指向多少条边
     */
    var out: Int = 0,

    /**
     * 邻接节点：从该节点出发能直接找到的节点
     */
    val nexts: MutableList<Node> = mutableListOf(),

    /**
     * 邻接边：从该节点出出发直接连接的边
     */
    val edges: MutableList<Edge> = mutableListOf()
)

class Edge(
    val weight: Int,
    var from: Node,
    var to: Node
)

class Graph(
    /**
     * 存储调用方的 key 与内部存储的节点的映射关系
     */
    val nodes: MutableMap<Int, Node> = hashMapOf(),
    val edges: MutableSet<Edge> = hashSetOf()
)

/**
 * 传入的矩阵，每行元素有三个，分别表示，权重，起始节点，末尾节点
 */
fun generateGraph(matrix: Array<IntArray>): Graph {
    val graph = Graph()
    for (edgeInfo in matrix) {
        val weight = edgeInfo[0]
        val from = edgeInfo[1]
        val to = edgeInfo[2]
        if (!graph.nodes.containsKey(from)) {
            graph.nodes[from] = Node(from)
        }
        if (!graph.nodes.containsKey(to)) {
            graph.nodes[to] = Node(to)
        }
        val fromNode = graph.nodes[from]!!
        val toNode = graph.nodes[to]!!
        fromNode.out++
        toNode.`in`++
        val edge = Edge(weight, fromNode, toNode)

        fromNode.nexts.add(toNode)
        fromNode.edges.add(edge)

        graph.edges.add(edge)
    }
    return graph
}