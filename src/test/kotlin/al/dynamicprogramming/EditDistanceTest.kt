package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class EditDistanceTest {

    @Test
    fun test() {
        val real = EditDistance().minDistance("horse", "ros")
        assertEquals(3, real)
    }

    @Test
    fun test2() {
        val real = EditDistance().minDistance("intention", "execution")
        assertEquals(5, real)
    }
}