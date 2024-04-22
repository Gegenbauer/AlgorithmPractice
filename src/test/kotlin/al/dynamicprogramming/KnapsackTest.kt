package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class KnapsackTest {

    @Test
    fun test1() {
        val weights = intArrayOf(2, 3, 5, 7)
        val values = intArrayOf(1, 5, 2, 4)
        val capacity = 10
        val real = Knapsack().maxValueOfBags(weights, values, capacity)
        assertEquals(9, real)
    }

    @Test
    fun test2() {
        val weights = intArrayOf(2, 3, 8)
        val values = intArrayOf(2, 5, 8)
        val capacity = 10
        val real = Knapsack().maxValueOfBags(weights, values, capacity)
        assertEquals(10, real)
    }
}