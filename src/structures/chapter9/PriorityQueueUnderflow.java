package structures.chapter9;

public class PriorityQueueUnderflow extends RuntimeException {
    public PriorityQueueUnderflow() {
        super("The priority queue is empty.");
    }
}
