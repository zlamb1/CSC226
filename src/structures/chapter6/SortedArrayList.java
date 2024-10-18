package structures.chapter6;

import structures.chapter5.IComparable;

import java.util.Comparator;

public class SortedArrayList<T> extends ArrayList<T> {
    protected final Comparator<T> comparator;

    public SortedArrayList() {
        this.comparator = (o1, o2) -> {
            try {
                return ((IComparable<T>)o1).compareTo(o2);
            } catch (ClassCastException e) {
                System.out.println(e.getMessage());
                return 0;
            }
        };
    }

    public SortedArrayList(Comparator<T> comparator) {
        this.comparator = comparator;
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
    public boolean add(T element) {
        int index = binarySearch(element);
        if (index < 0) {
            index = Math.abs(index + 1);
        }
        this.ensureCapacity(this.size + 1);
        for (int i = this.size - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = element;
        this.size++;
        return true;
    }

    @Override
    public int indexOf(T element) {
        return Math.max(binarySearch(element), -1);
    }

    @Override
    public T get(T element) {
        int index = binarySearch(element);
        if (index >= 0) {
            return this.array[index];
        }
        return null;
    }

    protected int binarySearch(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = this.size - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int cmp = this.comparator.compare(element, this.array[mid]);
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
