package al.other.math

import al.others.math.DistanceTraveled
import kotlin.test.Test
import kotlin.test.assertEquals

class DistanceTraveledTest {

    private val solution = DistanceTraveled()

    @Test
    fun test1() {
        val real = solution.distanceTraveled(5,10)
        assertEquals(60, real)
    }

    @Test
    fun test2() {
        val real = solution.distanceTraveled(1, 2)
        assertEquals(10, real)
    }
}