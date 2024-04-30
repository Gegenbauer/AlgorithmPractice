package al.strings

import kotlin.test.Test
import kotlin.test.assertEquals

class StringToIntTest {

    @Test
    fun test() {
        assertEquals(42, StringToInt().myAtoi("42"))
    }

    @Test
    fun test2() {
        assertEquals(-42, StringToInt().myAtoi("   -42"))
    }

    @Test
    fun test3() {
        assertEquals(4193, StringToInt().myAtoi("4193 with words"))
    }

    @Test
    fun test4() {
        assertEquals(-2147483648, StringToInt().myAtoi("-91283472332"))
    }

    @Test
    fun test5() {
        assertEquals(0, StringToInt().myAtoi(""))
    }

    @Test
    fun test6() {
        assertEquals(2147483647, StringToInt().myAtoi("2147483648"))
    }

    @Test
    fun test7() {
        assertEquals(2147483646, StringToInt().myAtoi("2147483646"))
    }
}