package al.prefix

/**
 * 前缀树，也叫 trie
 * 1. 给定一个字符串类型数组，构建一个多叉树，字符一般放在路径上
 * 2. 每个节点都存储一个 p 值和 e 值，还有一个 nextNodes 属性，存储一个大小为 26 的数组
 * nextNodes 的每个元素代表一个字符的路径
 * p 值代表经过这个节点的字符串数量
 * e 值代表以这个节点为终点的字符串数量
 * 根节点的 p 值代表总共的字符串数量
 */
class PrefixTree {

    /**
     * 前缀树节点类型
     */
    class Node(
        /**
         * 有多少路径经过这个节点
         */
        var pass: Int = 0,
        /**
         * 有多少路径以这个节点结尾
         */
        var end: Int = 0,
        /**
         * 假设字符串都是小写字母或大写字母
         * 用下一个节点是否为空表示，路径是否存在
         */
        val nextNodes: Array<Node?> = Array(26) { null }
    )

    class Trie(private val root: Node = Node()) {

        val totalWordCount: Int
            get() = root.pass

        fun insert(word: String) {
            root.pass++
            var curNode = root
            word.forEach { ch ->
                val charIndex = ch - BASE_CHAR
                val nextNode = curNode.nextNodes[charIndex]
                if (nextNode == null) {
                    curNode.nextNodes[charIndex] = Node()
                }
                curNode = curNode.nextNodes[charIndex]!!
                curNode.pass++
            }
            curNode.end++
        }

        /**
         * 检查字符串 [word] 出现过几次
         */
        fun search(word: String): Int {
            var cur = root
            word.forEach { ch ->
                val charIndex = ch - BASE_CHAR
                val nextNode = cur.nextNodes[charIndex] ?: return 0
                cur = nextNode
            }
            return cur.end
        }

        fun delete(word: String) {
            if (search(word) == 0) return

            var cur = root
            root.pass--
            word.forEach { ch ->
                val charIndex = ch - BASE_CHAR
                val nextNode = cur.nextNodes[charIndex]
                nextNode!!.pass--
                if (nextNode.pass == 0) {
                    cur.nextNodes[charIndex] = null
                    return
                } else {
                    cur = nextNode
                }
            }
            cur.end-- // 注意最后需要将 end 减一
        }

        /**
         * 有多少个字符串是以 [pre] 作为前缀的
         */
        fun prefixCount(pre: String): Int {
            var cur = root
            pre.forEach { ch ->
                val charIndex = ch - BASE_CHAR
                val nextNode = cur.nextNodes[charIndex] ?: return 0
                cur = nextNode
            }
            return cur.pass
        }
    }

    companion object {
        private const val BASE_CHAR = 'a'
    }
}