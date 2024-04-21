package al.backtrack

import al.util.createMatrix2
import al.util.displayStr
import al.util.isMatrixEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class CombinationSum3Test {

    @Test
    fun test1() {
        val real = CombinationSum3().combinationSum3(3, 7)
        val expect = createMatrix2("[[1,2,4]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }

    @Test
    fun test2() {
        val real = CombinationSum3().combinationSum3(3, 9)
        val expect = createMatrix2("[[1,2,6],[1,3,5],[2,3,4]]")
        assertTrue("real=\n${real.displayStr}, \nexpected=\n${expect.displayStr}") { isMatrixEquals(expect, real) }
    }
}