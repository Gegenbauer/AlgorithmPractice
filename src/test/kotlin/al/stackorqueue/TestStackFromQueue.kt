package al.stackorqueue

import kotlin.test.Test
import kotlin.test.assertEquals

class TestStackFromQueue {

    @Test
    fun test() {
        val stack = MyStack1()

        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.top())
        assertEquals(2, stack.pop())
        assertEquals(false, stack.empty())
    }

    @Test
    fun randomOperationTest() {
        // TODO implementation
    }
}