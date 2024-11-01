package structures.chapter5;

import java.util.Iterator;

public interface ICollection<T> extends Iterable<T> {
    default boolean add(T element) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default T get(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        for (T el : this) {
            if (el.equals(element)) {
                return el;
            }
        }

        return null;
    }

    default boolean contains(T element) throws IllegalArgumentException {
        return get(element) != null;
    }

    default boolean remove(T element) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    @SuppressWarnings("unchecked")
    default T[] toArray() {
        T[] array = (T[]) new Object[size()];
        int i = 0;
        for (T el : this) {
            array[i] = el;
            i++;
        }
        return array;
    }

    void clear();

    default boolean addAll(ICollection<T> other) {
        boolean modified = false;
        for (T el : other) {
            if (add(el)) {
                modified = true;
            }
        }
        return modified;
    }

    default boolean retainAll(ICollection<T> other) {
        boolean modified = false;
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            T el = iter.next();
            if (!other.contains(el)) {
                iter.remove();
                modified = true;
            }
        }
        return modified;
    }

    default boolean removeAll(ICollection<T> other) {
        boolean modified = false;
        for (T el : other) {
            if (remove(el)) {
                modified = true;
            }
        }
        return modified;
    }
}