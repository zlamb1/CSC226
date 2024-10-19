package structures.chapter5;

import java.util.Comparator;

public class SearchUtility {
    public static <T> int linearSearch(T[] array, T element) throws IllegalArgumentException {
        return SearchUtility.linearSearch(array, array.length, element);
    }

    public static <T> int linearSearch(T[] array, int size, T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < Math.min(size, array.length); i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public static <T> int binarySearch(T[] array, T element) throws IllegalArgumentException {
        return SearchUtility.binarySearch(array, array.length, element, ComparatorUtility.getDefaultComparator());
    }

    public static <T> int binarySearch(T[] array, T element, Comparator<T> comparator) throws IllegalArgumentException {
        return SearchUtility.binarySearch(array, array.length, element, comparator);
    }

    public static <T> int binarySearch(T[] array, int size, T element) throws IllegalArgumentException {
        return SearchUtility.binarySearch(array, size, element, ComparatorUtility.getDefaultComparator());
    }

    public static <T> int binarySearch(T[] array, int size, T element, Comparator<T> comparator) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        int low = 0, high = Math.min(size - 1, array.length - 1), mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int cmp = comparator.compare(element, array[mid]);
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
