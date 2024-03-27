package al.sort

import al.utils.generateRandomArray
import al.utils.isArrayEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class SortTest {

    @Test
    fun checkSelectionSort() {
        repeat(20) {
            assertTrue { checkSortResultWithBuildInMethod(::selectionSort) }
        }
    }

    @Test
    fun checkBubbleSort() {
        repeat(1000) {
            assertTrue { checkSortResultWithBuildInMethod(::bubbleSort) }
        }
    }

    @Test
    fun checkInsertSort() {
        repeat(1000) {
            assertTrue { checkSortResultWithBuildInMethod(::insertSort) }
        }
    }

    private fun checkSortResultWithBuildInMethod(method: (IntArray) -> Unit): Boolean {
        val targetMethodResult = generateRandomArray(1000)
        val buildInResult = targetMethodResult.copyOf()
        method(targetMethodResult)
        buildInResult.sort()
        return isArrayEquals(buildInResult, targetMethodResult)
    }

    @Test
    fun checkMergeSort() {
        checkSortResultWithBuildInMethod(RecursiveMergeSort()::mergeSort)
    }

    @Test
    fun checkIterativeMergeSort() {
        checkSortResultWithBuildInMethod(IterativeMergeSort()::mergeSort)
    }

}