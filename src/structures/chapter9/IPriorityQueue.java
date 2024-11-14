package structures.chapter9;

public interface IPriorityQueue<T> {
    void enqueue(T element) throws PriorityQueueOverflow;
    T dequeue() throws PriorityQueueUnderflow;
    boolean isEmpty();
    int size();
}
