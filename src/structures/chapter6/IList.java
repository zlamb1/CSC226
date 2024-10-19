package structures.chapter6;

import structures.chapter5.ICollection;

import java.util.Comparator;

public interface IList<T> extends ICollection<T> {
    default void add(int index, T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default T set(int index, T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void sort(Comparator<T> comp) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    T get(int index);
    int indexOf(T target);
    T remove(int index);
}
