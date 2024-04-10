package al.sort

import al.sort.heap.HeapSort
import al.sort.merge.IterativeMergeSort
import al.sort.merge.RecursiveMergeSort
import al.sort.noncomparison.CountSort
import al.sort.noncomparison.RadixSort
import al.sort.quick.QuickSort
import al.sort.simple.bubbleSort
import al.sort.simple.insertSort
import al.sort.simple.selectionSort
import al.util.generateRandomArray
import al.util.isArrayEquals
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

    private fun checkSortResultWithBuildInMethod(method: (IntArray) -> Unit, positive: Boolean = false): Boolean {
        val targetMethodResult = generateRandomArray(1000, isPositive = positive)
        val copy = targetMethodResult.copyOf()
        val buildInResult = targetMethodResult.copyOf()
        method(targetMethodResult)
        buildInResult.sort()
        return isArrayEquals(buildInResult, targetMethodResult).also {
            if (it.not()) {
                println(copy.contentToString())
            }
        }
    }

    @Test
    fun checkRecursiveMergeSort() {
        assertTrue(checkSortResultWithBuildInMethod(RecursiveMergeSort()::mergeSort))
    }

    @Test
    fun checkIterativeMergeSort() {
        assertTrue(checkSortResultWithBuildInMethod(IterativeMergeSort()::mergeSort))
    }

    @Test
    fun checkQuickSort() {
        assertTrue(checkSortResultWithBuildInMethod(QuickSort()::sort))
    }

    @Test
    fun checkHeapSort() {
        assertTrue(checkSortResultWithBuildInMethod(HeapSort()::sort2))
    }

    @Test
    fun checkCountSort() {
        assertTrue(checkSortResultWithBuildInMethod(CountSort()::sort))
    }

    @Test
    fun checkRadixSort() {
        assertTrue(checkSortResultWithBuildInMethod(RadixSort()::sort, true))
    }

    @Test
    fun checkRadixSort2() {
        assertTrue(checkSortResultWithBuildInMethod(RadixSort()::sort2, true))
    }

}