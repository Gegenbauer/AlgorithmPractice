package al.strings

import kotlin.test.Test
import kotlin.test.assertEquals

class StringToIntTest {

    @Test
    fun test() {
        assertEquals(42, StringToInt().myAtoi("42"))
        assertEquals(-42, StringToInt().myAtoi("   -42"))
        assertEquals(4193, StringToInt().myAtoi("4193 with words"))
    }
}