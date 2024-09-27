package structures.chapter4;

public class QueueUnderflowException extends RuntimeException {
    public QueueUnderflowException() {
        super("The queue is empty.");
    }

    public QueueUnderflowException(String message) {
        super(message);
    }
}
