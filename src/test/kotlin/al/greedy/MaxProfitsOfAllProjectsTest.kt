package al.greedy

import kotlin.test.Test
import kotlin.test.assertEquals

class MaxProfitsOfAllProjectsTest {

    @Test
    fun test() {
        val profits = intArrayOf(1, 2, 3)
        val capital = intArrayOf(0, 1, 1)
        assertEquals(4, MaxProfitsOfAllProjects().findMaximizedCapital(2, 0, profits, capital))
    }

    @Test
    fun test2() {
        val profits = intArrayOf(1, 2, 3)
        val capital = intArrayOf(1, 1, 2)
        assertEquals(4, MaxProfitsOfAllProjects().findMaximizedCapital(1, 2, profits, capital))
    }
}