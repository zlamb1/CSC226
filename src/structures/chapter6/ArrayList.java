package structures.chapter6;

import structures.chapter5.ArrayCollection;
import structures.chapter5.SortUtility;

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
        private final ArrayList<T> list;

        public ArrayListIterator(ArrayList<T> list) {
            this.list = list;
        }

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
             list.remove(--index);
        }
    }

    @Override
    public void add(int index, T element) {
        boundsCheck(index);
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
    }

    @Override
    public T set(int index, T element) {
        boundsCheck(index);
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    @Override
    public T get(int index) {
        boundsCheck(index);
        return array[index];
    }

    @Override
    public int indexOf(T target) {
        return linearSearch(target);
    }

    @Override
    public T remove(int index) {
        boundsCheck(index);
        T element = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size--;
        return element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        SortUtility.mergeSort(array, comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator(this);
    }

    protected void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
