package structures.chapter4;

public interface IDeque<T> {
    void pushFront(T item);
    void pushBack(T item);
    T popFront();
    T popBack();
    T peekFront();
    T peekBack();
    int getSize();
    boolean isEmpty();
}
