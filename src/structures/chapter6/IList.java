package structures.chapter6;

import structures.chapter5.ICollection;

import java.util.Comparator;

public interface IList<T> extends ICollection<T> {
    void add(int index, T element);
    T set(int index, T element);
    T get(int index);
    int indexOf(T target);
    T remove(int index);
    void sort(Comparator<T> comp);
}
