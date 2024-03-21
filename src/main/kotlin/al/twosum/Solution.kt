package al.twosum

import kotlin.math.max
import kotlin.math.min

class Solution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val record = mutableMapOf<Int, Int>() // value - index
        for ((index, num) in nums.withIndex()) {
            val anotherValue = target - num
            if (anotherValue in record) {
                return intArrayOf(min(index, record[anotherValue]!!), max(index, record[anotherValue]!!))
            }
            record[num] = index
        }
        return intArrayOf()
    }
}