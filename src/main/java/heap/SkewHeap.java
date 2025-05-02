package heap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkewHeap <E extends Comparable<? super E>> {

    private Node<E> root;

    @Getter
    @Setter
    public static class Node<E> {
        E key;
        Node<E> right;
        Node<E> left;

        public Node(E key) {
            this(key, null, null);
        }

        public Node(E key, Node<E> left, Node<E> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public SkewHeap()
    {
        root = null;
    }

    public void merge(SkewHeap<E> heap) {
        if (heap == null || this == heap) {
            return;
        }

        root = merge(this.root, heap.root);
    }

    public void insert(E key) {
        Node<E> insertable = new Node<>(key);
        root = merge(this.root, insertable);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        root = merge(this.root.left, this.root.right);
    }

    public E findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return this.root.key;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clearHeap() {
        this.root = null;
    }

    // the special merge function that's
    // used in most of the other operations
    private Node<E> merge(Node<E> h1, Node<E> h2)
    {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null)
            return h1;

        // Make sure that h1 has smaller
        // key.
        if (h1.key.compareTo(h2.key) > 0) {
            Node<E> temp = h1;
            h1 = h2;
            h2 = temp;
        }

        // Swap h1.left and h1.right
        Node<E> temp = h1.left;
        h1.left = h1.right;
        h1.right = temp;

        // Merge h2 and h1.left and make
        // merged tree as left of h1.
        h1.left = merge(h2, h1.left);

        return h1;
    }
}
