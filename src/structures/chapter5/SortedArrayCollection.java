package structures.chapter5;

import java.util.Iterator;

public class SortedArrayCollection<T extends IComparable<T>> implements ICollection<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private T[] array;
    private int size;

    private class SortedArrayIterator implements Iterator<T> {
        private SortedArrayCollection<T> coll;
        private int index = 0;

        public SortedArrayIterator(SortedArrayCollection<T> coll) {
            this.coll = coll;
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public T next() {
            return array[index++];
        }

        @Override
        public void remove() {
            index--;
            for (int i = index + 1; i < size; i++) {
                array[i - 1] = array[i];
            }
            size--;
        }
    }

    @SuppressWarnings("unchecked")
    public SortedArrayCollection() {
        this.array = (T[]) new IComparable[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean add(T element) {
        int index = binarySearch(element);
        if (index < 0) {
            index = -index - 1;
        }
        this.ensureCapacity();
        for (int i = this.size - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = element;
        this.size++;
        return true;
    }

    @Override
    public T get(T element) {
        if (this.size == 0 || element == null) {
            return null;
        }
        int index = binarySearch(element);
        if (index < 0) {
            return null;
        }
        return this.array[index];
    }

    @Override
    public boolean contains(T element) {
        if (this.size == 0 || element == null) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T element) {
        if (this.size == 0) {
            return false;
        }
        int index = binarySearch(element);
        if (index < 0) {
            return false;
        }
        for (int i = index + 1; i < this.size; i++) {
            this.array[i - 1] = this.array[i];
        }
        this.size--;
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
        for (T element : other) {
            this.add(element);
        }
        return false;
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
        return new SortedArrayIterator(this);
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

    protected int binarySearch(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = this.size - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int cmp = element.compareTo(this.array[mid]);
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

    @SuppressWarnings("unchecked")
    protected boolean ensureCapacity() {
        if (this.size < this.array.length - 1) {
            return false;
        }
        T[] newArray = (T[]) new IComparable[this.array.length * 2];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
        return true;
    }
}
