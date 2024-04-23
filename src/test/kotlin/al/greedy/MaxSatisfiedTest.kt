package al.greedy

import kotlin.test.Test
import kotlin.test.assertEquals

class MaxSatisfiedTest {

    @Test
    fun test() {
        val customers = intArrayOf(1, 0, 1, 2, 1, 1, 7, 5)
        val grumpy = intArrayOf(0, 1, 0, 1, 0, 1, 0, 1)
        val real = MaxSatisfied().maxSatisfied(customers, grumpy, 3)
        assertEquals(16, real)
    }

    @Test
    fun test2() {
        val customers = intArrayOf(1)
        val grumpy = intArrayOf(0)
        val real = MaxSatisfied().maxSatisfied(customers, grumpy, 1)
        assertEquals(1, real)
    }
}