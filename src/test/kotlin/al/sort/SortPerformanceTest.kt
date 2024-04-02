package al.sort

import al.sort.heap.HeapSort
import al.sort.merge.IterativeMergeSort
import al.sort.quick.QuickSort
import al.sort.simple.bubbleSort
import al.sort.simple.insertSort
import al.sort.simple.selectionSort
import al.utils.generateRandomArray
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class SortPerformanceTest {

    @Test
    fun testBubbleSort() {
        val method = MethodPerformanceData(
            ::bubbleSort,
            "bubbleSort",
        )


        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    private fun sortRandomArrays(method: (IntArray) -> Unit, repeatCount: Int, arrSize: Int): Long {
        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(repeatCount) {
                add(generateRandomArray(arrSize))
            }
        }
        return measureTimeMillis {
            randomArrays.forEach { arr ->
                testSortMethodPerformance(arr, method)
            }
        }
    }

    @Test
    fun testInsertSort() {
        val method = MethodPerformanceData(
            ::insertSort,
            "insertSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testSelectionSort() {
        val method = MethodPerformanceData(
            ::selectionSort,
            "selectionSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testMergeSort() {
        val method = MethodPerformanceData(
            IterativeMergeSort()::mergeSort,
            "mergeSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testQuickSort() {
        val method = MethodPerformanceData(
            QuickSort()::sort,
            "quickSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testQuickSort2() {
        val method = MethodPerformanceData(
            QuickSort()::sort2,
            "quickSort2",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testQuickSort3() {
        val method = MethodPerformanceData(
            QuickSort()::sort3,
            "quickSort3",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testZuoQuickSort() {
        val method = MethodPerformanceData(
            Code06_QuickSort::quickSort,
            "zuoQuickSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testHeapSort() {
        val method = MethodPerformanceData(
            HeapSort()::sort2,
            "heapSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    @Test
    fun testBuildInSort() {
        val method = MethodPerformanceData(
            buildInSort,
            "buildInSort",
        )

        val cost = sortRandomArrays(method.method, REPEAT_COUNT, ARRAY_SIZE)

        println("method ${method.methodName} cost $cost")
    }

    private val buildInSort: ((IntArray) -> Unit) = {
        it.sort()
    }

    private inline fun testSortMethodPerformance(arr: IntArray, method: (IntArray) -> Unit): Long {
        return measureNanoTime {
            method(arr)
        }
    }

    private data class MethodPerformanceData(
        val method: (IntArray) -> Unit,
        val methodName: String,
    )

    companion object {
        private const val REPEAT_COUNT = 100
        private const val ARRAY_SIZE = 10000
    }
}