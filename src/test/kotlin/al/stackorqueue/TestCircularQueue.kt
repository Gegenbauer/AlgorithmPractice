package al.stackorqueue

import kotlin.test.Test
import kotlin.test.assertEquals

class TestCircularQueue {

    @Test
    fun test() {
        val circularQueue = MyCircularQueue2(6)
        assertEquals(circularQueue.enQueue(6), true)
        assertEquals(circularQueue.Rear(), 6)
        assertEquals(circularQueue.Rear(), 6)
        assertEquals(circularQueue.deQueue(), true)
        assertEquals(circularQueue.enQueue(5), true)
        assertEquals(circularQueue.Rear(), 5)
        assertEquals(circularQueue.deQueue(), true)
        assertEquals(circularQueue.Front(), -1)
        assertEquals(circularQueue.deQueue(), false)
        assertEquals(circularQueue.deQueue(), false)
        assertEquals(circularQueue.deQueue(), false)
    }
}