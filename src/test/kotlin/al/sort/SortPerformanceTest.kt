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
        val methods = listOf(
            MethodPerformanceData(
                ::bubbleSort,
                "bubbleSort",
            )
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testInsertSort() {
        val methods = listOf(
            MethodPerformanceData(
                ::insertSort,
                "insertSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testSelectionSort() {
        val methods = listOf(
            MethodPerformanceData(
                ::selectionSort,
                "selectionSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testMergeSort() {
        val methods = listOf(
            MethodPerformanceData(
                IterativeMergeSort()::mergeSort,
                "mergeSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testQuickSort() {
        val methods = listOf(
            MethodPerformanceData(
                QuickSort()::sort,
                "quickSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testQuickSort2() {
        val methods = listOf(
            MethodPerformanceData(
                QuickSort()::sort2,
                "quickSort2",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testQuickSort3() {
        val methods = listOf(
            MethodPerformanceData(
                QuickSort()::sort3,
                "quickSort3",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testZuoQuickSort() {
        val methods = listOf(
            MethodPerformanceData(
                Code06_QuickSort::quickSort,
                "zuoQuickSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testHeapSort() {
        val methods = listOf(
            MethodPerformanceData(
                HeapSort()::sort2,
                "heapSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
    }

    @Test
    fun testBuildInSort() {
        val methods = listOf(
            MethodPerformanceData(
                buildInSort,
                "buildInSort",
            ),
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(REPEAT_COUNT) {
                add(generateRandomArray(ARRAY_SIZE))
            }
        }
        methods.forEach {
            val cost = measureTimeMillis {
                randomArrays.forEach { arr ->
                    testSortMethodPerformance(arr, it.method)
                }
            }
            it.averageCost = cost
        }

        methods.sortedBy { it.averageCost }
        methods.forEach {
            println("method ${it.methodName} cost ${it.averageCost}")
        }
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
        var averageCost: Long = 0
    )

    companion object {
        private const val REPEAT_COUNT = 100
        private const val ARRAY_SIZE = 10000
    }
}