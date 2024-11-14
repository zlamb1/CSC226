package structures.chapter9;

import structures.chapter2.LLNode;
import structures.chapter5.ComparatorUtility;

import java.util.Comparator;

public class LLPriorityQueue<T> implements IPriorityQueue<T> {
    protected Comparator<T> comparator;
    protected LLNode<T> head = null;
    protected int size = 0;

    public LLPriorityQueue() {
        comparator = ComparatorUtility.getDefaultComparator();
    }

    public LLPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (isEmpty()) {
            head = new LLNode<>(element);
        } else {
            LLNode<T> cursor = head, precursor = null;
            while (cursor != null) {
                int cmp = comparator.compare(element, cursor.getElement());
                if (cmp > 0) {
                    if (precursor == null) {
                        head = new LLNode<>(element, head);
                    } else {
                        precursor.setNext(new LLNode<>(element, cursor));
                    }
                } else if (cursor.getNext() == null) {
                    cursor.setNext(new LLNode<>(element));
                }

                precursor = cursor;
                cursor = cursor.getNext();
            }
        }

        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (isEmpty()) {
            throw new PriorityQueueUnderflow();
        }

        T element = head.getElement();
        head = head.getNext();
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
}
