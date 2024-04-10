package al.backtrack

import kotlin.test.Test
import kotlin.test.assertEquals

class PermutationTest {

    @Test
    fun testArrayWithNoDuplicateElements() {
        val arr = intArrayOf(1, 2, 3)
        val permutations = Permutation().permute(arr)
        println(permutations)
        assertEquals(6, permutations.size)
    }

    @Test
    fun testArrayWithNoDuplicateElements2() {
        val arr = intArrayOf(1, 2, 3)
        val permutations = Permutation().permute2(arr)
        println(permutations)
        assertEquals(6, permutations.size)
    }

    @Test
    fun testArrayWithNoDuplicateElements3() {
        val arr = intArrayOf(1, 2, 3)
        val permutations = Permutation().permuteArrayWithDuplicates(arr)
        println(permutations)
        assertEquals(6, permutations.size)
    }

    @Test
    fun testArrayWithDuplicateElements3() {
        val arr = intArrayOf(1, 2, 2)
        val permutations = Permutation().permuteArrayWithDuplicates(arr)
        println(permutations)
        assertEquals(3, permutations.size)
    }
}