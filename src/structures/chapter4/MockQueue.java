package structures.chapter4;

import java.util.LinkedList;
import java.util.Queue;

public class MockQueue<T> implements IQueue<T> {
    private final Queue<T> queue;

    public MockQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void enqueue(T element) {
        queue.offer(element);
    }

    @Override
    public T dequeue() {
        return queue.poll();
    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return this.queue.toString();
    }
}
