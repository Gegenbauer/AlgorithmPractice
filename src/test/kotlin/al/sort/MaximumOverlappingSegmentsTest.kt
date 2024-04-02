package al.sort

import kotlin.math.max

class MaximumOverlappingSegmentsTest {

    private fun bruteForce(lines: Array<Array<Int>>): Int {
        val min = lines.minOf { it[0] }
        val max = lines.minOf { it[1] }

        var cur: Double = min + 0.5
        var result = 0
        while (cur < max) {
            var overlappingCount = 0
            lines.forEach {
                overlappingCount += if (cur < it[1] && cur > it[0]) {
                    1
                } else {
                    0
                }
            }
            result = max(result, overlappingCount)
            cur += 1
        }

        return result
    }
}