package al.backtrack

import al.util.createMatrix2
import al.util.displayStr
import al.util.isMatrixEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class CombinationSumTest {

    @Test
    fun test1() {
        val arr = intArrayOf(2, 3, 6, 7)
        val real = CombinationSum().combinationSum(arr, 7)
        val expect = createMatrix2("[[2,2,3],[7]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }

    @Test
    fun test2() {
        val arr = intArrayOf(2, 3, 5)
        val real = CombinationSum().combinationSum(arr, 8)
        val expect = createMatrix2("[[2,2,2,2],[2,3,3],[3,5]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }

    @Test
    fun test3() {
        val arr = intArrayOf(2)
        val real = CombinationSum().combinationSum(arr, 1)
        val expect = createMatrix2("[]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }
}