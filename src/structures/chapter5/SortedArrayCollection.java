package structures.chapter5;

import java.util.Comparator;

public class SortedArrayCollection<T> extends ArrayCollection<T> implements ISortedCollection<T> {
    protected Comparator<T> comparator;

    public SortedArrayCollection() {
        this.comparator = ComparatorUtility.getDefaultComparator();
    }

    public SortedArrayCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        setComparator(comparator);
        SortUtility.mergeSort(array, comparator);
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        ensureCapacity(size + 1);
        int index = binarySearch(element);
        if (index < 0) index = -index - 1;

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size++;
        return true;
    }

    @Override
    public T get(T element) {
        int index = binarySearch(element);

        if (index > 0) {
            return null;
        }

        return array[index];
    }

    @Override
    public boolean remove(T element) {
        int index = binarySearch(element);

        if (index > 0) {
            return false;
        }

        array[index] = array[size];
        size--;

        return true;
    }

    protected int binarySearch(T element) {
        return SearchUtility.binarySearch(array, size, element, comparator);
    }
}
