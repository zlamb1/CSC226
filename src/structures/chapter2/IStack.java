package structures.chapter2;

public interface IStack<T> {
    void push(T t);
    T pop();
    T top();
    // alias for top
    T peek();

    boolean isEmpty();
    boolean isFull();
}
