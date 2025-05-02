import heap.SkewHeap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static heap.SkewHeap.Node;

public class HeapTest {

    @Test
    void isEmptyTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        assertTrue(heap.isEmpty());
        Node<Integer> node = new Node<>(1);
        heap.setRoot(node);
        assertFalse(heap.isEmpty());
    }

    @Test
    void insertTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        heap.insert(1);
        assertEquals(1, heap.getRoot().getKey());

        heap.insert(2);
        assertEquals(1, heap.getRoot().getKey());
        assertEquals(2, heap.getRoot().getLeft().getKey());

        heap.insert(3);
        assertEquals(1, heap.getRoot().getKey());
        assertEquals(2, heap.getRoot().getRight().getKey());
        assertEquals(3, heap.getRoot().getLeft().getKey());
    }

    @Test
    void clearHeapTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        assertFalse(heap.isEmpty());
        heap.clearHeap();
        assertTrue(heap.isEmpty());
    }

    @Test
    void findMinTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        heap.insert(-3);
        assertEquals(-3, heap.findMin());
        heap.insert(-10);
        assertEquals(-10, heap.findMin());
        heap.insert(-5);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        assertEquals(-10, heap.findMin());

        heap.clearHeap();
        assertThrows(IllegalStateException.class, heap::findMin);
    }

    @Test
    void deleteMinTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        heap.insert(-3);
        heap.insert(-5);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        assertEquals(-5, heap.findMin());
        heap.deleteMin();
        assertEquals(-3, heap.findMin());
        heap.deleteMin();
        assertEquals(1, heap.findMin());

        heap.clearHeap();
        assertThrows(IllegalStateException.class, heap::deleteMin);
    }

    @Test
    void mergeTest() {
        SkewHeap<Integer> heap = new SkewHeap<>();
        SkewHeap<Integer> heap2 = new SkewHeap<>();
        heap.insert(-3);
        heap.insert(-5);
        heap2.insert(1);
        heap2.insert(-10);
        assertEquals(-5, heap.findMin());
        heap.merge(heap2);
        assertEquals(-10, heap.findMin());

        heap.clearHeap();
        heap2.clearHeap();
        heap2.insert(2);
        heap2.insert(3);
        assertTrue(heap.isEmpty());
        assertDoesNotThrow(() -> heap.merge(heap2));
        assertFalse(heap.isEmpty());
        assertEquals(2, heap.findMin());

        assertDoesNotThrow(() -> heap.merge(null));

        assertDoesNotThrow(() -> heap.merge(heap));
        assertEquals(2, heap.findMin());
        heap.deleteMin();
        assertEquals(3, heap.findMin());
    }
}
