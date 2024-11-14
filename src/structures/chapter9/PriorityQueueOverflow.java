package structures.chapter9;

public class PriorityQueueOverflow extends RuntimeException {
    public PriorityQueueOverflow() {
        super("The priority queue is full.");
    }
}
