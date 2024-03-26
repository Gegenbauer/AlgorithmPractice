package al.sort

import al.utils.generateRandomArray
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class SortPerformanceTest {

    @Test
    fun testSortMethodsCost() {
        val methods = listOf(
            MethodPerformanceData(
                ::selectionSort,
                "selectionSort",
                0
            ),
            MethodPerformanceData(
                ::bubbleSort,
                "bubbleSort",
                0
            ),
            MethodPerformanceData(
                ::insertSort,
                "insertSort",
                0
            ),
            MethodPerformanceData(
                RecursiveMergeSort()::mergeSort,
                "mergeSort",
                0
            ),
            MethodPerformanceData(
                buildInSort,
                "builtInSort",
                0
            )
        )

        val randomArrays = mutableListOf<IntArray>().apply {
            repeat(10000) {
                add(generateRandomArray(1000))
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
}