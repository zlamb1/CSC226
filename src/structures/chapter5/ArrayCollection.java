package structures.chapter5;

import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection<T> extends AbstractCollection<T> implements IBoundedCollection<T> {
    protected static final int DEFAULT_CAPACITY = 16;
    protected T[] array;

    private final class ArrayIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return array[index++];
        }

        @Override
        public void remove() {
            size--;
            array[--index] = array[size];
        }
    }

    public ArrayCollection() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayCollection(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    @SuppressWarnings("unchecked")
    public ArrayCollection(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayCollection(T defaultValue, int capacity) {
        this.array = (T[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = defaultValue;
        }
        size = capacity;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        ensureCapacity(size + 1);
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        int index = linearSearch(element);
        if (index < 0) {
            return false;
        }
        size--;
        array[index] = array[size];
        return true;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int removeAll(T element) {
        int count = 0;
        while (this.remove(element)) {
            count++;
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public boolean ensureCapacity(int minCapacity) {
        if (array.length < minCapacity) {
            int newCapacity = array.length;
            while (newCapacity < minCapacity) {
                newCapacity = (int) Math.ceil(newCapacity * 2.0);
            }
            T[] newArray = (T[]) new Object[newCapacity];
            if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            return true;
        }
        return false;
    }

    protected int linearSearch(T element) {
        return SearchUtility.linearSearch(array, size, element);
    }
}
