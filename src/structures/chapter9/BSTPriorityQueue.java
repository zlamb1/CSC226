package structures.chapter9;

import structures.chapter4.ArrayQueue;
import structures.chapter4.IQueue;
import structures.chapter5.ComparatorUtility;

import java.util.Comparator;

public class BSTPriorityQueue<T> implements IPriorityQueue<T> {
    protected class BSTPQNode {
        private T value;
        private BSTPQNode left, right;
        // separate chaining for duplicates
        private BSTPQNode next = null;

        public BSTPQNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public BSTPQNode getLeft() {
            return left;
        }

        public void setLeft(BSTPQNode left) {
            this.left = left;
        }

        public BSTPQNode getRight() {
            return right;
        }

        public void setRight(BSTPQNode right) {
            this.right = right;
        }

        public BSTPQNode getNext() {
            return next;
        }

        public void setNext(BSTPQNode next) {
            this.next = next;
        }
    }

    private final Comparator<T> comparator;
    private BSTPQNode root;
    private int size;

    public BSTPriorityQueue() {
        this(ComparatorUtility.getDefaultComparator());
    }

    public BSTPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (root == null) {
            root = new BSTPQNode(element);
        } else {
            BSTPQNode cursor = root;
            while (cursor != null) {
                int cmp = comparator.compare(element, cursor.getValue());
                if (cmp == 0) {
                    BSTPQNode _cursor = cursor;
                    while (_cursor.getNext() != null) {
                        _cursor = _cursor.getNext();
                    }
                    _cursor.setNext(new BSTPQNode(element));
                    break;
                } else if (cmp < 0) {
                    BSTPQNode left = cursor.getLeft();
                    if (left == null) {
                        cursor.setLeft(new BSTPQNode(element));
                        break;
                    }
                    cursor = left;
                } else {
                    BSTPQNode right = cursor.getRight();
                    if (right == null) {
                        cursor.setRight(new BSTPQNode(element));
                        break;
                    }
                    cursor = cursor.getRight();
                }
            }
        }

        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (isEmpty()) {
            throw new PriorityQueueUnderflow();
        }

        BSTPQNode cursor = root, precursor = null;
        while (cursor.getRight() != null) {
            precursor = cursor;
            cursor = cursor.getRight();
        }

        T element;
        if (cursor.getNext() == null) {
            element = cursor.getValue();
            if (precursor == null) {
                root = root.getLeft();
            } else {
                precursor.setRight(cursor.getLeft());
            }
        } else {
            BSTPQNode _cursor = cursor, _precursor = null;
            while (_cursor.getNext() != null) {
                _precursor = cursor;
                _cursor = _cursor.getNext();
            }
            element = _cursor.getValue();
            assert _precursor != null;
            _precursor.setNext(null);
        }

        size--;
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return getNodeAsString(root, "");
    }

    protected String getNodeAsString(BSTPQNode root, String prefix) {
        if (root == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        BSTPQNode left = root.getLeft(), right = root.getRight(), cursor = root;

        IQueue<T> queue = new ArrayQueue<>();
        while (cursor != null) {
            queue.enqueue(cursor.getValue());
            cursor = cursor.getNext();
        }

        while (!queue.isEmpty()) {
            T element = queue.dequeue();
            stringBuilder.append(element);
            if (!queue.isEmpty()) {
                stringBuilder.append(" → ");
            }
        }

        if (stringBuilder.length() % 2 == 0) {
            stringBuilder.insert(0, "0");
        }

        int halfLen = (int) (stringBuilder.length() / 2.0);
        prefix += StringUtility.repeat(' ', halfLen);

        stringBuilder.append("\n");
        stringBuilder
            .append(prefix)
            .append("├─")
            .append(left != null ? getNodeAsString(left,prefix + "│ ") : "null")
            .append("\n")
            .append(prefix)
            .append("└─")
            .append(right != null ? getNodeAsString(right, prefix + "  ") : "null");

        return stringBuilder.toString();
    }
}
