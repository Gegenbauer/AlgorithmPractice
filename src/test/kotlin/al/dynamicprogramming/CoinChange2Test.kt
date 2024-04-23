package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class CoinChange2Test {

    @Test
    fun test() {
        val amount = 5
        val coins = intArrayOf(1, 2, 5)
        val real = CoinChange2().change(amount, coins)
        assertEquals(4, real)
    }

    @Test
    fun test2() {
        val amount = 3
        val coins = intArrayOf(2)
        val real = CoinChange2().change(amount, coins)
        assertEquals(0, real)
    }

    @Test
    fun test3() {
        val amount = 10
        val coins = intArrayOf(10)
        val real = CoinChange2().change(amount, coins)
        assertEquals(1, real)
    }
}