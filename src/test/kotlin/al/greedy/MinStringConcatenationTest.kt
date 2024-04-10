package al.greedy

import kotlin.test.Test
import kotlin.test.assertEquals

class MinStringConcatenationTest {

    @Test
    fun test() {
        val target = arrayOf("ba", "b", "cd", "ccb", "cde")
        assertEquals("babccbcdcde", MinStringConcatenation().minString(target))
        assertEquals("babccbcdcde", MinStringConcatenation().bruteForce(target))
    }
}