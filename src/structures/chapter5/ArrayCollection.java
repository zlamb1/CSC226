package structures.chapter5;

import java.util.Iterator;

public class ArrayCollection<T> implements ICollection<T> {
    protected static final int DEFAULT_CAPACITY = 16;
    protected T[] array;
    protected int size;

    private final class ArrayIterator implements Iterator<T> {
        private int index = 0;

        public ArrayIterator() {

        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public T next() {
            return array[this.index++];
        }

        @Override
        public void remove() {
            size--;
            array[--this.index] = array[size];
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayCollection() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayCollection(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        this.ensureCapacity(this.size + 1);
        this.array[this.size] = element;
        this.size++;
        return true;
    }

    @Override
    public T get(T element) {
        int index = this.linearSearch(element);
        if (index == -1) {
            return null;
        }
        return this.array[index];
    }

    @Override
    public boolean contains(T element) {
        return this.get(element) != null;
    }

    @Override
    public boolean remove(T element) {
        int index = this.linearSearch(element);
        if (index < 0) {
            return false;
        }
        this.size--;
        this.array[index] = this.array[this.size];
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.array.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T[] toArray() {
        return this.array.clone();
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public boolean addAll(ICollection<T> other) {
        boolean modified = false;
        for (T el : other) {
            if (this.add(el)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(ICollection<T> other) {
        boolean modified = false;
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            T el = iter.next();
            if (!other.contains(el)) {
                iter.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(ICollection<T> other) {
        boolean modified = false;
        for (T el : other) {
            if (this.remove(el)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[i]);
            if (i != this.size - 1) {
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
    protected boolean ensureCapacity(int minCapacity) {
        if (this.array.length < minCapacity) {
            int newCapacity = this.array.length;
            while (newCapacity < minCapacity) {
                newCapacity = (int) Math.ceil(newCapacity * 2.0);
            }
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
            return true;
        }
        return false;
    }

    protected int linearSearch(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
