package structures.chapter9;

import java.util.PriorityQueue;

public interface IBoundedPriorityQueue<T> extends IPriorityQueue<T> {
    boolean isFull();
}
