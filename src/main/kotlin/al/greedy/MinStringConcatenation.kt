package al.greedy

import java.util.*

/**
 * nowcoder-拼接所有的字符串产生字典序最小的字符串
 */
class MinStringConcatenation {

    fun minString(strs: Array<String>): String {
        Arrays.sort(strs) { str1, str2 ->
            "$str1$str2".compareTo("$str2$str1")
        }
        val result = StringBuilder()
        strs.forEach { result.append(it) }
        return result.toString()
    }

    fun bruteForce(strs: Array<String>): String {
        strs.sort()
        val compositions = mutableListOf<String>()
        val visited = BooleanArray(strs.size)
        backtrack(strs, visited, LinkedList(), compositions)
        return compositions.min()
    }

    private fun backtrack(
        strs: Array<String>,
        visited: BooleanArray,
        temp: LinkedList<String>,
        result: MutableList<String>
    ) {
        if (temp.size == strs.size) {
            result.add(temp.joinToString(""))
            return
        }
        for ((index, str) in strs.withIndex()) {
            if (visited[index]) continue

            if (index > 0 && !visited[index - 1] && (strs[index] == strs[index - 1])) continue
            temp.offer(str)
            visited[index] = true

            backtrack(strs, visited, temp, result)

            temp.removeLast()
            visited[index] = false
        }
    }

    fun dp(strs: Array<String>): String {
        TODO("动态规划")
    }
}