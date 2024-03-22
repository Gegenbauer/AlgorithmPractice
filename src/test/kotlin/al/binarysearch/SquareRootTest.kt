package al.binarysearch

import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class SquareRootTest {

    @Test
    fun test() {
        repeat(10000) {
            val target = Random.nextInt(10000)
            val expectedValue = sqrt(target.toDouble()).toInt()
            val realValue = SquareRoot().mySqrt(target)
            assertEquals(
                expectedValue, realValue,
                "target=$target, expected=$expectedValue, realValue=$realValue"
            )
        }
    }

    @Test
    fun testSpecificValue() {
        val target = 2147395599
        val expectedValue = sqrt(target.toDouble()).toInt()
        val realValue = SquareRoot().mySqrt(target)
        assertEquals(
            expectedValue, realValue,
            "target=$target, expected=$expectedValue, realValue=$realValue"
        )
    }
}