package structures.chapter9;


import java.util.Comparator;

public class ArrayPriorityQueue<T> implements IBoundedPriorityQueue<T> {
    protected static double DEF_GROWTH_FACTOR = 2;

    protected Comparator<T> comparator;
    protected T[] array;
    protected int size;
    protected double growthFactor = DEF_GROWTH_FACTOR;

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
        array = (T[]) new Object[16];
        size = 0;
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (size == array.length) {
            ensureCapacity(size + 1);
        }

        array[size++] = element;
        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (size == 0) {
            throw new PriorityQueueUnderflow();
        }

        T element = array[0];
        for (T current : array) {
            int cmp = comparator.compare(current, element);
            if (cmp > 0) {
                element = current;
            }
        }

        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    public double getGrowthFactor() {
        return growthFactor;
    }

    public void setGrowthFactor(double growthFactor) {
        this.growthFactor = growthFactor;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= array.length) {
            return;
        }

        int newCapacity = array.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) Math.ceil(newCapacity * growthFactor);
        }

        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }
}
