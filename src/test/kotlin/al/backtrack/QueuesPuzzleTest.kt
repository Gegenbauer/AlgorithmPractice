package al.backtrack

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QueuesPuzzleTest {

    private val solution = QueuesPuzzle()

    @Test
    fun testIsAttacked() {
        assertTrue(solution.isAttacked(5, 3, 2, 0, 2))
        assertTrue(solution.isAttacked(5, 3, 2, 1, 0))
        assertTrue(solution.isAttacked(5, 3, 2, 1, 2))
        assertTrue(solution.isAttacked(5, 3, 2, 1, 4))
        assertTrue(solution.isAttacked(5, 3, 2, 2, 1))
        assertTrue(solution.isAttacked(5, 3, 2, 2, 2))
        assertTrue(solution.isAttacked(5, 3, 2, 2, 3))
        assertTrue(solution.isAttacked(5, 3, 2, 3, 0))
        assertTrue(solution.isAttacked(5, 3, 2, 3, 1))
        assertTrue(solution.isAttacked(5, 3, 2, 3, 2))
        assertTrue(solution.isAttacked(5, 3, 2, 3, 3))
        assertTrue(solution.isAttacked(5, 3, 2, 3, 4))
        assertTrue(solution.isAttacked(5, 3, 2, 4, 1))
        assertTrue(solution.isAttacked(5, 3, 2, 4, 2))
        assertTrue(solution.isAttacked(5, 3, 2, 4, 3))
    }

    @Test
    fun testFiveQueues() {
        val real = solution.solveNQueens(5).sortedBy { it.joinToString() }
        val expected = listOf(
            listOf("Q....", "..Q..", "....Q", ".Q...", "...Q."),
            listOf("Q....", "...Q.", ".Q...", "....Q", "..Q.."),
            listOf(".Q...", "...Q.", "Q....", "..Q..", "....Q"),
            listOf(".Q...", "....Q", "..Q..", "Q....", "...Q."),
            listOf("..Q..", "Q....", "...Q.", ".Q...", "....Q"),
            listOf("..Q..", "....Q", ".Q...", "...Q.", "Q...."),
            listOf("...Q.", "Q....", "..Q..", "....Q", ".Q..."),
            listOf("...Q.", ".Q...", "....Q", "..Q..", "Q...."),
            listOf("....Q", ".Q...", "...Q.", "Q....", "..Q.."),
            listOf("....Q", "..Q..", "Q....", "...Q.", ".Q..."),
        ).sortedBy { it.joinToString() }
        assertEquals(expected, real)
    }
}