package al.other

import al.table.SnapshotArray
import kotlin.test.Test
import kotlin.test.assertEquals

class SnapshotArrayTest {

    @Test
    fun test1() {
        val snapshotArray = SnapshotArray(2)
        snapshotArray.snap()
        assertEquals(0, snapshotArray.get(1, 0))
        assertEquals(0, snapshotArray.get(0, 0))
        snapshotArray.set(1, 8)
        assertEquals(0, snapshotArray.get(1, 0))
        snapshotArray.set(0, 20)
        assertEquals(0, snapshotArray.get(0, 0))
        snapshotArray.set(0, 7)
    }

    @Test
    fun test2() {
        val snapshotArray = SnapshotArray(3)
        snapshotArray.set(0, 5)
        snapshotArray.snap()
        snapshotArray.set(0, 6)
        assertEquals(snapshotArray.get(0, 0), 5)
    }

    @Test
    fun test3() {
        val snapshotArray = SnapshotArray(1)
        snapshotArray.set(0, 4)
        snapshotArray.set(0, 16)
        snapshotArray.set(0, 13)
        snapshotArray.snap()
        assertEquals(13, snapshotArray.get(0, 0))
        snapshotArray.snap()
    }

    @Test
    fun test4() {
        val snapshotArray = SnapshotArray(2)
        snapshotArray.snap()
        assertEquals(0, snapshotArray.get(1, 0))
        assertEquals(0, snapshotArray.get(0, 0))
        snapshotArray.set(1, 8)
        assertEquals(0, snapshotArray.get(1, 0))
        snapshotArray.set(0, 20)
        assertEquals(0, snapshotArray.get(0, 0))
        snapshotArray.set(0, 7)
    }

    @Test
    fun test5() {
        val snapshotArray = SnapshotArray(1)
        snapshotArray.set(0, 15)
        snapshotArray.snap()
        snapshotArray.snap()
        snapshotArray.snap()
        assertEquals(15, snapshotArray.get(0, 2))
        snapshotArray.snap()
        snapshotArray.snap()
        assertEquals(15, snapshotArray.get(0, 0))
    }
}