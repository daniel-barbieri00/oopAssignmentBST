package assignment3oopBST;

import static org.junit.jupiter.api.Assertions.*;

import utilities.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTreeTest {

    private BSTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BSTree<>();
    }

    @Test
    void testEmptyTree() {
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        assertEquals(0, bst.getHeight());
        assertFalse(bst.contains(42));
        assertNull(bst.search(42));
    }

    @Test
    void testAddAndSearch() {
        assertTrue(bst.add(50));
        assertTrue(bst.add(30));
        assertTrue(bst.add(60));
        assertTrue(bst.add(40));

        assertFalse(bst.add(null));

        assertTrue(bst.contains(50));
        assertTrue(bst.contains(40));
        assertFalse(bst.contains(70));

        assertNotNull(bst.search(30));
        assertNull(bst.search(70));
    }

    @Test
    void testRemoveMinAndMax() {
        bst.add(50);
        bst.add(30);
        bst.add(60);
        bst.add(40);

        assertEquals(30, bst.removeMin().getData());
        assertEquals(40, bst.removeMin().getData());
        assertEquals(60, bst.removeMax().getData());
        assertNull(bst.removeMin());
        assertNull(bst.removeMax());
    }

    @Test
    void testClear() {
        bst.add(50);
        bst.add(30);
        bst.add(60);

        assertFalse(bst.isEmpty());
        bst.clear();
        assertTrue(bst.isEmpty());
        assertNull(bst.getRoot());
    }

    @Test
    void testInorderIterator() {
        bst.add(50);
        bst.add(30);
        bst.add(60);
        bst.add(40);

        Iterator<Integer> iterator = bst.inorderIterator();
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }

        assertEquals("30 40 50 60 ", result.toString());
    }

    @Test
    void testPreorderIterator() {
        bst.add(50);
        bst.add(30);
        bst.add(60);
        bst.add(40);

        Iterator<Integer> iterator = bst.preorderIterator();
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }

        assertEquals("50 30 40 60 ", result.toString());
    }

    @Test
    void testPostorderIterator() {
        bst.add(50);
        bst.add(30);
        bst.add(60);
        bst.add(40);

        Iterator<Integer> iterator = bst.postorderIterator();
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }

        assertEquals("40 30 60 50 ", result.toString());
    }
}
