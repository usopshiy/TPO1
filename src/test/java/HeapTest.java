import heap.SkewHeap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static heap.SkewHeap.Node;

public class HeapTest {
    SkewHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new SkewHeap<>();
    }

    @AfterEach
    void tearDown() {
        heap = null;
    }

    @Test
    void isEmptyTest() {
        assertTrue(heap.isEmpty());
        Node<Integer> node = new Node<>(1);
        heap.setRoot(node);
        assertFalse(heap.isEmpty());
    }

    @Test
    void insertTest() {
        heap.insert(1);
        assertEquals(1, heap.getRoot().getKey());

        heap.insert(2);
        assertEquals(1, heap.getRoot().getKey());
        assertEquals(2, heap.getRoot().getLeft().getKey());
    }

    @Test
    void insertStructTest(){
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        assertAll(
                () -> {assertEquals(1, heap.getRoot().getKey());},
                () -> {assertEquals(2, heap.getRoot().getRight().getKey());},
                () -> {assertEquals(3, heap.getRoot().getLeft().getKey());});
    }


    @Test
    void clearHeapTest() {
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);

        heap.clearHeap();
        assertTrue(heap.isEmpty());
    }

    @Test
    void findMinTest() {
        heap.insert(-3);
        assertEquals(-3, heap.findMin());
        heap.insert(-10);
        assertEquals(-10, heap.findMin());
        heap.insert(2);
        heap.insert(3);
        assertEquals(-10, heap.findMin());
    }

    @Test
    void testFindMinInEmptyHeap() {
        assertThrows(IllegalStateException.class, heap::findMin);
    }

    @Test
    void deleteMinTest() {
        heap.insert(-3);
        heap.insert(-5);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        assertEquals(-5, heap.findMin());
        heap.deleteMin();
        assertEquals(-3, heap.findMin());
    }

    @Test
    void testDeleteMinInEmptyHeap() {
        assertThrows(IllegalStateException.class, heap::deleteMin);
    }

    @Test
    void mergeTest() {
        SkewHeap<Integer> heap2 = new SkewHeap<>();
        heap.insert(-5);
        heap2.insert(-10);
        assertEquals(-5, heap.findMin());
        heap.merge(heap2);
        assertEquals(-10, heap.findMin());
    }

    @Test
    void testMergeEmptyHeap() {
        SkewHeap<Integer> heap2 = new SkewHeap<>();
        heap2.insert(1);
        assertDoesNotThrow(() -> heap.merge(heap2));
        assertFalse(heap.isEmpty());
    }

    @Test
    void testNullMerge(){
        assertDoesNotThrow(() -> heap.merge(null));
    }

    @Test
    void testSelfMerge(){
        heap.insert(2);
        assertDoesNotThrow(() -> heap.merge(heap));
        //checking for heap duplication
        heap.deleteMin();
        assertTrue(heap.isEmpty());
    }
}
