package al.graph

import java.util.LinkedList
import java.util.Queue

class TopologicalSort {

    /**
     * 先找到第一批入度为 0 的节点，放入待处理任务队列
     * 1. 遍历任务队列，弹出任务并处理
     * 2. 然后将其邻接节点入度都减一
     * 3. 如果有邻接节点入度为 0，则放入任务队列
     * 重复 1-3，直到队列为空
     */
    fun sort(graph: Graph): List<Node> {
        // 建立入度表，因为不能修改原来的节点信息
        val inDegreeMap = hashMapOf<Node, Int>()
        val zeroDegreeNodes: Queue<Node> = LinkedList()
        for (node in graph.nodes.values) {
            inDegreeMap[node] = node.inDegree
            if (node.inDegree == 0) {
                zeroDegreeNodes.add(node)
            }
        }

        val result = mutableListOf<Node>()
        while (zeroDegreeNodes.isNotEmpty()) {
            val node = zeroDegreeNodes.poll()

            // 处理任务
            result.add(node)

            // 更新邻接节点入度
            for (next in node.nexts) {
                inDegreeMap[next] = inDegreeMap[next]!! - 1
                // 添加新的可以添加的任务
                if (inDegreeMap[next] == 0) {
                    zeroDegreeNodes.offer(next)
                }
            }
        }

        return result
    }
}