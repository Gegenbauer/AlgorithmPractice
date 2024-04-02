package al.sort

import al.sort.heap.CustomHeap
import java.util.*
import kotlin.Comparator
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomHeapTest {

    @Test
    fun test() {
        val comparator = Comparator<Int> { o1, o2 ->
            /**
             * # o1 - o2 这里如果用 o1 - o2 可能导致溢出
             */
            when {
                o1 > o2 -> 1
                o1 == o2 -> 0
                else -> -1
            }
        }
        val maxSize = 10000
        val heap = CustomHeap(maxSize, comparator)

        // 默认为小根堆
        val priorityQueue = PriorityQueue(maxSize, comparator)

        repeat(maxSize) {
            val randomValue = Random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE)
            heap.push(randomValue)
            priorityQueue.add(randomValue)
        }

        repeat(maxSize) {
            assertEquals(priorityQueue.poll(), heap.pop())
        }
    }
}