package al.dynamicprogramming

import kotlin.test.Test
import kotlin.test.assertEquals

class CoinChangeTest {

    @Test
    fun test() {
        val amount = 11
        val coins = intArrayOf(1, 2, 5)
        val real = CoinChange().coinChange(coins, amount)
        assertEquals(3, real)
    }

    @Test
    fun test2() {
        val amount = 3
        val coins = intArrayOf(2)
        val real = CoinChange().coinChange(coins, amount)
        assertEquals(-1, real)
    }

    @Test
    fun test3() {
        val amount = 0
        val coins = intArrayOf(1)
        val real = CoinChange().coinChange(coins, amount)
        assertEquals(0, real)
    }
}