package structures.chapter9;

import structures.chapter6.IList;

import java.util.Comparator;

public abstract class ListPriorityQueue<T> implements IBoundedPriorityQueue<T> {
    protected Comparator<T> comparator;
    protected IList<T> list;
    protected int size = 0;

    public ListPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        int index = binarySearch(element);
        if (index < 0) {
            index = -index - 1;
        }

        if (isFull()) {
            list.add(element);
        }

        for (int i = size - 1; i >= index; i--) {
            list.set(i + 1, list.get(i));
        }

        list.set(index, element);

        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (isEmpty()) {
            throw new PriorityQueueUnderflow();
        }

        return list.get(--size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == list.size();
    }

    protected int binarySearch(T element) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparator.compare(element, list.get(mid));
            if (cmp > 0) {
                low = mid + 1;
            } else if (cmp < 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -(low + 1);
    }
}
