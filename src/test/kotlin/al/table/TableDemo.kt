package al.table

import java.util.*
import kotlin.test.Test

class TableDemo {
    @Test
    fun test() {
        val treeMap = TreeMap<A, String>()
        treeMap[A(20)] = "值是 20"
    }

    class A(
        val value: Int
    ): Comparable<A> {
        override fun compareTo(other: A): Int {
            return value - other.value
        }
    }
}