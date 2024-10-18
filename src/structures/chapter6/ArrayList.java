package structures.chapter6;

import structures.chapter5.ArrayCollection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<T> extends ArrayCollection<T> implements IList<T> {
    public ArrayList() {
        super();
    }

    public ArrayList(T[] array) {
        super(array);
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index = 0;
        private ArrayList<T> list;

        public ArrayListIterator(ArrayList<T> list) {
            this.list = list;
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
             this.list.remove(--this.index);
        }
    }

    @Override
    public void add(int index, T element) {
        this.boundsCheck(index);
        super.ensureCapacity(super.size + 1);
        for (int i = super.size - 1; i >= index; i--) {
            super.array[i + 1] = super.array[i];
        }
        super.array[index] = element;
        super.size++;
    }

    @Override
    public T set(int index, T element) {
        this.boundsCheck(index);
        T oldElement = super.array[index];
        super.array[index] = element;
        return oldElement;
    }

    @Override
    public T get(int index) {
        this.boundsCheck(index);
        return super.array[index];
    }

    @Override
    public int indexOf(T target) {
        return super.linearSearch(target);
    }

    @Override
    public T remove(int index) {
        this.boundsCheck(index);
        T element = super.array[index];
        for (int i = index + 1; i < super.size; i++) {
            super.array[i - 1] = super.array[i];
        }
        super.size--;
        return element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        Arrays.sort(super.array, comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator(this);
    }

    protected void boundsCheck(int index) {
        if (index < 0 || index >= super.size) {
            throw new ArrayOutOfBoundsException(index, super.size);
        }
    }
}
