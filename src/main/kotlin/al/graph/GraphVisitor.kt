package al.graph

import java.util.*

class GraphVisitor {

    /**
     * 遍历每一个节点时，将所有邻居节点都放入队列
     *
     * bfs 队列内放置的是下一层需要遍历的节点
     */
    fun bfs(start: Node, visitor: (Node) -> Boolean) {
        // 保证一个节点只会进入队列一次
        val set = hashSetOf<Node>()
        val queue = LinkedList<Node>()
        queue.offer(start)
        set.add(start)
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            if (!visitor(cur)) return
            for (next in cur.nexts) {
                if (next in set) continue
                set.add(next)
                queue.offer(next)
            }
        }
    }

    fun bfs2(start: Node, visitor: (Node) -> Boolean) {
        // 保证一个节点只会进入队列一次
        val set = hashSetOf<Node>()
        var arr = LinkedList<Node>()
        arr.add(start)
        set.add(start)
        while (arr.isNotEmpty()) {
            val tmp = LinkedList<Node>()
            for (node in arr) {
                if (!visitor(node)) return
                for (next in node.nexts) {
                    if (next !in set) {
                        tmp.add(next)
                        set.add(next)
                    }
                }
            }
            arr = tmp
        }
    }

    /**
     * 遍历每个节点时都将其放入栈，每条路径都走到底，到底后则返回上一个节点
     *
     * dfs 栈内放置的是当前走过的路径
     */
    fun dfs(start: Node, visitor: (Node) -> Boolean) {
        val stack = Stack<Node>()
        val set = hashSetOf<Node>()
        var cur: Node? = start

        fun findNextUnvisited(cur: Node): Node? {
            return cur.nexts.firstOrNull { it !in set }
        }

        fun addNodeToStack(node: Node) {
            stack.push(node)
            set.add(node)
        }

        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                if (cur in set) continue
                if (!visitor(cur)) continue
                addNodeToStack(cur)
                cur = findNextUnvisited(cur)
            }
            val node = stack.peek()
            cur = findNextUnvisited(node)
            // 如果 cur 还有未访问的邻居，则 cur 不弹出
            if (cur == null) stack.pop()
        }
    }

    fun dfs2(start: Node, visitor: (Node) -> Boolean) {
        val stack = Stack<Node>()
        val set = hashSetOf<Node>()
        if (!visitor(start)) return

        stack.push(start)
        set.add(start)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            for (next in node.nexts) {
                if (next !in set) {
                    // 放入 next 同时要放入 cur
                    // 因为当前路径遍历完最后要回到 cur
                    if (!visitor(next)) return
                    stack.push(node)
                    stack.push(next)
                    set.add(next)
                    break
                }
            }
        }
    }
}