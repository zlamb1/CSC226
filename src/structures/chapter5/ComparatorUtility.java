package structures.chapter5;

import java.util.Comparator;

public class ComparatorUtility {
    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> getDefaultComparator() {
        return new Comparator<T>() {
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
}
