package structures.chapter5;

public interface ICollection<T> extends Iterable<T> {
    boolean add(T element);
    T get(T element);
    boolean contains(T element);
    boolean remove(T element);
    boolean isEmpty();
    boolean isFull();
    int size();
    T[] toArray();
    void clear();
    boolean addAll(ICollection<T> other);
    boolean retainAll(ICollection<T> other);
    boolean removeAll(ICollection<T> other);
}