package structures.chapter4;

public interface IQueue<T> {
    void enqueue(T element);
    T dequeue();
    T peekFront();
    boolean isEmpty();
}
