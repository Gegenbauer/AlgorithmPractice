package al.others

import java.util.LinkedList

/**
 * leetcode-49 字母异位词分组
 *
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = HashMap<String, LinkedList<String>>()
        strs.forEach {
            val code = encodeString(it)
            if (map[code] == null) {
                map[code] = LinkedList()
            }
            map[code]!!.offer(it)
        }
        return map.values.toList()
    }

    /**
     * 26 个字母，映射成另外一个字符串，保证组合相同，映射结果相同
     */
    private fun encodeString(str: String): String {
        val result = IntArray(26)
        for (char in str) {
            result[char - 'a']++
        }
        val sb = StringBuilder()
        result.forEachIndexed { index, value ->
            var count = value
            while (count-- > 0) {
                sb.append('a' + index)
            }
        }
        return sb.toString()
    }
}