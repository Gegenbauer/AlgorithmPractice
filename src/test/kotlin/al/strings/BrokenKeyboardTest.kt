package al.strings

import kotlin.test.Test
import kotlin.test.assertEquals

class BrokenKeyboardTest {

    @Test
    fun testBruteForce() {
        assertEquals("rtsng", BrokenKeyboard().finalString("string"))
        assertEquals("ponter", BrokenKeyboard().finalString("poiinter"))
    }

    @Test
    fun testQueueSolution() {
        assertEquals("rtsng", BrokenKeyboard().finalString2("string"))
        assertEquals("ponter", BrokenKeyboard().finalString2("poiinter"))
    }
}