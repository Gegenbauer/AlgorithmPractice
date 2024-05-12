package al.strings

/**
 * leetcode-316 去除重复字母
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的
 * 字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
class RemoveDuplicateLetters {

    /**
     * 贪心策略：每次添加字符都确保当前为字典序最小的字符串
     * 每次添加新字符前，先检查当前字符是否小于结果字符串的最后一个字符，在检查结果字符串的最后一个字符是否在后续还会出现
     * 如果满足条件，则移除结果字符串的最后一个字符
     * 然后添加新字符
     */
    fun removeDuplicateLetters(s: String): String {
        val result = StringBuilder()
        val charIndexRecord = IntArray(26) { -1 }
        for (i in s.indices) {
            val index = s[i] - 'a'
            if (charIndexRecord[index] < i) {
                charIndexRecord[index] = i
            }
        }
        val set = hashSetOf<Char>()
        for ((index, ch) in s.withIndex()) {
            if (ch in set) continue
            while (result.isNotEmpty() && ch < result.last()) {
                val hasSameChar = charIndexRecord[result.last() - 'a'] > index
                if (hasSameChar) {
                    set.remove(result.last())
                    result.deleteAt(result.lastIndex)
                } else {
                    break
                }
            }
            set.add(ch)
            result.append(ch)
        }
        return result.toString()
    }
}