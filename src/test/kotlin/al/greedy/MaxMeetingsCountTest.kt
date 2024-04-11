package al.greedy

import kotlin.test.Test
import kotlin.test.assertEquals

class MaxMeetingsCountTest {

    @Test
    fun test1() {
        val meetings = arrayOf(
           Meeting(0, 100), Meeting(10, 20), Meeting(0, 15), Meeting(15, 30), Meeting(40, 50)
        )
        val maxMeetingsCount = MaxMeetingsCount().maxMeetingsCount(meetings)
        assertEquals(3, maxMeetingsCount)
        val maxMeetingsCount2 = MaxMeetingsCount().bruteForce(meetings)
        assertEquals(3, maxMeetingsCount2)
        val maxMeetingsCount3 = MaxMeetingsCount().bruteForce2(meetings)
        assertEquals(3, maxMeetingsCount3)
    }

    @Test
    fun test2() {
        val meetings = arrayOf(
            Meeting(0, 15), Meeting(40, 50), Meeting(15, 30)
        )
        val maxMeetingsCount = MaxMeetingsCount().maxMeetingsCount(meetings)
        assertEquals(3, maxMeetingsCount)
        val maxMeetingsCount2 = MaxMeetingsCount().bruteForce(meetings)
        assertEquals(3, maxMeetingsCount2)
        val maxMeetingsCount3 = MaxMeetingsCount().bruteForce2(meetings)
        assertEquals(3, maxMeetingsCount3)
    }

    @Test
    fun test3() {
        val meetings = arrayOf(
            Meeting(0, 100)
        )
        val maxMeetingsCount = MaxMeetingsCount().maxMeetingsCount(meetings)
        assertEquals(1, maxMeetingsCount)
        val maxMeetingsCount2 = MaxMeetingsCount().bruteForce(meetings)
        assertEquals(1, maxMeetingsCount2)
        val maxMeetingsCount3 = MaxMeetingsCount().bruteForce2(meetings)
        assertEquals(1, maxMeetingsCount3)
    }
}