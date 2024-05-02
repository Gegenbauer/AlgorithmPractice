package al.strings

import java.util.LinkedList

/**
 * leetcode-438 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 */
class FindAnagrams {

    fun findAnagrams(s: String, p: String): List<Int> {
        val intArrayFromP = IntArray(26)
        p.forEach { c ->
            intArrayFromP[c - 'a']++
        }
        val charCount = intArrayFromP.count { it != 0 }
        val result = LinkedList<Int>()
        for (i in s.indices) {
            if (i + p.length > s.length) break
            var cur = i
            val temp = intArrayFromP.copyOf()
            var tempCharCount = charCount
            while (cur < i + p.length) {
                val index = s[cur] - 'a'
                if (temp[index] == 0) {
                    break
                }
                temp[index]--
                if (temp[index] == 0) {
                    tempCharCount--
                }
                if (tempCharCount == 0) {
                    result.add(i)
                    break
                }
                cur++
            }
        }
        return result
    }
}