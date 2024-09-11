package structures.chapter2;

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super("The stack is empty.");
    }
}
