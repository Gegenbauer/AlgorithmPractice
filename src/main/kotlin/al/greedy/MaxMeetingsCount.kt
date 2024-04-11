package al.greedy

import java.util.*
import kotlin.math.max

/**
 * 会议室能安排的会议的最大数量
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
class MaxMeetingsCount {

    /**
     * 贪心策略
     * 会议开始时间和结束时间都是述职，且满足 time > 0
     */
    fun maxMeetingsCount(meetings: Array<Meeting>): Int {
        Arrays.sort(meetings) { meeting1: Meeting, meeting2: Meeting ->
            meeting1.end - meeting2.end
        }
        var result = 0
        var lastEndTime = 0
        meetings.forEach {
            if (lastEndTime <= it.start) {
                result++
                lastEndTime = it.end
            }
        }
        return result
    }

    fun bruteForce(meetings: Array<Meeting>): Int {
        Arrays.sort(meetings) { meeting1: Meeting, meeting2: Meeting ->
            meeting1.start - meeting2.start
        }
        val result = mutableListOf<List<Meeting>>()
        backtrack(meetings, BooleanArray(meetings.size), LinkedList(), result, 0)
        return result.maxOf { it.size }
    }

    private fun backtrack(
        meetings: Array<Meeting>,
        visited: BooleanArray,
        temp: LinkedList<Meeting>,
        result: MutableList<List<Meeting>>,
        cur: Int,
    ) {
        if (cur == meetings.size) {
            result.add(temp.toList())
            return
        }

        // 如果当前无可选择的会议，则将当前结果加入到结果集中
        var restMeetingsCount = meetings.size
        for ((index, meeting) in meetings.withIndex()) {
            if (visited[index] || !isValidMeeting(meeting, temp)) {
                if ((--restMeetingsCount) == 0) {
                    result.add(temp.toList())
                }
                continue
            }
            visited[index] = true

            temp.offer(meeting)
            backtrack(meetings, visited, temp, result, cur + 1)
            temp.removeLast()
            visited[index] = false
        }
    }

    private fun isValidMeeting(meeting: Meeting, meetings: LinkedList<Meeting>): Boolean {
        meetings.forEach {
            if (meetings.last.end > meeting.start) return false
        }
        return true
    }

    /**
     * 判断两区间是否有交集
     * 可以找出无交集的情况
     */
    private fun isMeetingsOverlapping(meeting1: Meeting, meeting2: Meeting): Boolean {
        return !(meeting1.end <= meeting2.start || meeting1.start >= meeting2.end)
    }

    fun bruteForce2(meetings: Array<Meeting>): Int {
        return process(meetings.toList(), 0, 0)
    }

    /**
     * [done] 已经安排的会议数量
     * [timeLine] 目前安排到的会议最后的时间点
     */
    private fun process(meetings: List<Meeting>, done: Int, timeLine: Int): Int {
        if (meetings.isEmpty()) return done

        var max = done

        for ((index, meeting) in meetings.withIndex()) {
            if (meeting.start >= timeLine) {
                max = max(max,
                    process(meetings.filterIndexed { i, _ -> i != index }, done + 1, meeting.end)
                )
            }
        }
        return max
    }

    fun dp(meetings: Array<Meeting>): Int {
        TODO("动态规划")
    }
}

class Meeting(
    val start: Int,
    val end: Int
)