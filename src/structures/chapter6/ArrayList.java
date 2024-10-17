package structures.chapter6;

import structures.chapter5.ICollection;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements IList<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private T[] array;
    private int size;

    private class ArrayIterator implements Iterator<T> {
        private int index;
        private final ArrayList<T> arrayList;

        public ArrayIterator(ArrayList<T> arrayList) {
            this.index = 0;
            this.arrayList = arrayList;
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
            arrayList.remove(--this.index);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    @Override
    public void add(int index, T element) {
        checkBounds(index);
        this.ensureCapacity(this.size + 1);
        for (int i = this.size - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = element;
        this.size++;
    }

    @Override
    public T set(int index, T element) {
        checkBounds(index);
        T oldElement = this.array[index];
        this.array[index] = element;
        return oldElement;
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return this.array[index];
    }

    @Override
    public int indexOf(T target) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T oldElement = this.array[index];
        for (int i = index + 1; i < this.size; i++) {
            this.array[i - 1] = this.array[i];
        }
        this.size--;
        return oldElement;
    }

    @Override
    public boolean add(T element) {
        this.ensureCapacity(this.size + 1);
        this.array[this.size] = element;
        this.size++;
        return true;
    }

    @Override
    public T get(T element) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(element)) {
                return this.array[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public boolean remove(T element) {
        int indexOf = this.indexOf(element);
        if (indexOf >= 0) {
            this.remove(indexOf);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size >= this.array.length;
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
        return false;
    }

    @Override
    public boolean retainAll(ICollection<T> other) {
        return false;
    }

    @Override
    public boolean removeAll(ICollection<T> other) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(this);
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

    protected void checkBounds(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayOutOfBoundsException(index, this.size);
        }
    }

    @SuppressWarnings("unchecked")
    protected boolean ensureCapacity(int minCapacity) {
        if (this.array.length < minCapacity) {
            T[] newArray = (T[]) new Object[minCapacity];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
            return true;
        }
        return false;
    }
}
