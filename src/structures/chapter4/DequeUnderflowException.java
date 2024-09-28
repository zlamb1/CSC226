package structures.chapter4;

public class DequeUnderflowException extends RuntimeException {
    public DequeUnderflowException() {
        super("The deque is empty.");
    }
    public DequeUnderflowException(String message) {
        super(message);
    }
}
