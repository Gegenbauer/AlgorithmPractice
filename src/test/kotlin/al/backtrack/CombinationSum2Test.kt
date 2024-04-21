package al.backtrack

import al.util.createMatrix2
import al.util.displayStr
import al.util.isMatrixEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class CombinationSum2Test {

    @Test
    fun test1() {
        val arr = intArrayOf(10, 1, 2, 7, 6, 1, 5)
        val real = CombinationSum2().combinationSum2(arr, 8)
        val expect = createMatrix2("[[1,1,6],[1,2,5],[1,7],[2,6]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }

    @Test
    fun test2() {
        val arr = intArrayOf(2, 5, 2, 1, 2)
        val real = CombinationSum2().combinationSum2(arr, 5)
        val expect = createMatrix2("[[1,2,2],[5]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }
}