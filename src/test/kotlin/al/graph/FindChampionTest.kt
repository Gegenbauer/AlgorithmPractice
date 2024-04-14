package al.graph

import kotlin.test.Test
import kotlin.test.assertEquals

class FindChampionTest {

    @Test
    fun test() {
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
        )
        assertEquals(0, FindChampion().findChampion(3, edges))
    }

    @Test
    fun test2() {
        val edges = arrayOf(
            intArrayOf(0, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 2),
        )
        assertEquals(-1, FindChampion().findChampion(4, edges))
    }
}