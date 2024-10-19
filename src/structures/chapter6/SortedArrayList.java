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
    public T get(int index) {
        boundsCheck(index);
        return array[index];
    }

    @Override
    public int indexOf(T target) {
        int index = binarySearch(target);
        if (index < 0) {
            return -1;
        }
        return index;
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

    protected void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
