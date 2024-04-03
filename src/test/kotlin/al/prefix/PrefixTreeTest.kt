package al.prefix

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class PrefixTreeTest {

    @Test
    fun testInsertAndSearch() {
        val trie = PrefixTree.Trie()
        trie.insert("apple")
        trie.insert("banana")
        trie.insert("orange")
        trie.insert("pineapple")
        trie.insert("grape")
        trie.insert("apricot")

        assertEquals(1, trie.search("apple"))
        assertEquals(1, trie.search("orange"))
        assertEquals(1, trie.search("grape"))
        assertEquals(0, trie.search("mango"))
    }

    @Test
    fun testDelete() {
        val trie = PrefixTree.Trie()
        trie.insert("apple")
        trie.insert("banana")
        trie.insert("orange")

        trie.delete("orange")
        assertEquals(0, trie.search("orange"))

        trie.delete("banana")
        assertEquals(0, trie.search("banana"))
    }

    @Test
    fun testPrefixCount() {
        val trie = PrefixTree.Trie()
        trie.insert("apple")
        trie.insert("banana")
        trie.insert("orange")
        trie.insert("apricot")

        assertEquals(2, trie.prefixCount("ap"))
        assertEquals(2, trie.prefixCount("a"))
        assertEquals(0, trie.prefixCount("m"))
    }

    @Test
    fun testTotalWordsCount() {
        val trie = PrefixTree.Trie()
        trie.insert("apple")
        trie.insert("banana")
        trie.insert("orange")
        trie.insert("pineapple")
        trie.insert("grape")
        trie.insert("apricot")

        assertEquals(6, trie.totalWordCount)
    }
}