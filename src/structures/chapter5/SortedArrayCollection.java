package structures.chapter5;

import java.util.Comparator;

public class SortedArrayCollection<T> extends ArrayCollection<T> {
    protected Comparator<T> comparator;

    public SortedArrayCollection() {
        this.comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                try {
                    return ((IComparable<T>) o1).compareTo(o2);
                } catch (ClassCastException e) {
                    System.out.println(e.getMessage());
                    return 0;
                }
            }
        };
    }

    public SortedArrayCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        super.ensureCapacity(super.size + 1);
        int index = this.binarySearch(element);
        if (index < 0) index = -index - 1;
        for (int i = super.size - 1; i >= index; i--) {
            super.array[i + 1] = super.array[i];
        }
        super.array[index] = element;
        super.size++;
        return true;
    }

    @Override
    public T get(T element) {
        int index = this.binarySearch(element);
        if (index > 0) {
            return null;
        }
        return super.array[index];
    }

    @Override
    public boolean remove(T element) {
        int index = this.binarySearch(element);
        if (index > 0) {
            return false;
        }
        super.array[index] = super.array[super.size];
        super.size--;
        return true;
    }

    protected int binarySearch(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = super.size - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int cmp = comparator.compare(element, super.array[mid]);
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
}
