package structures.chapter6;

import structures.chapter5.SortedArrayCollection;

import java.util.Comparator;

public class SortedArrayList<T> extends SortedArrayCollection<T> implements IList<T> {
    public SortedArrayList() {
        super();
    }

    public SortedArrayList(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        boundsCheck(index);
        return super.array[index];
    }

    @Override
    public int indexOf(T target) {
        int index = super.binarySearch(target);
        if (index < 0) {
            return -1;
        }
        return index;
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
        throw new UnsupportedOperationException();
    }

    protected void boundsCheck(int index) {
        if (index < 0 || index >= super.size) {
            throw new ArrayOutOfBoundsException(index, super.size);
        }
    }
}
