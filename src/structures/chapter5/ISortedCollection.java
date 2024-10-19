package structures.chapter5;

import java.util.Comparator;

public interface ISortedCollection<T> extends ICollection<T> {
    Comparator<T> getComparator();
    void setComparator(Comparator<T> comparator);
    void sort(Comparator<T> comparator);
}
