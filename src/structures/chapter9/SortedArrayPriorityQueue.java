package structures.chapter9;

import structures.chapter4.QueueUnderflowException;
import structures.chapter5.SearchUtility;

import java.util.Comparator;

public class SortedArrayPriorityQueue<T> extends ArrayPriorityQueue<T> {
    public SortedArrayPriorityQueue(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        int index = SearchUtility.binarySearch(array, element, comparator);
        if (index < 0) index = -index - 1;

        for (int i = size - 1; i > index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (size == 0) {
            throw new QueueUnderflowException();
        }

        T element = array[size - 1];
        size--;
        return element;
    }
}
