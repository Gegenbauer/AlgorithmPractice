package al.binarysearch

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class FirstBadVersionTest {

    @Test
    fun test() {
        repeat(10000) {
            val versionCount = Random.nextInt(10000)
            val badVersionStart = Random.nextInt(versionCount)

            val isBadVersionFun: ((Int) -> Boolean) = {
                it >= badVersionStart
            }

            val firstBadVersion = FirstBadVersion().firstBadVersion(versionCount, isBadVersionFun)
            assertEquals(badVersionStart, firstBadVersion)
        }
    }
}