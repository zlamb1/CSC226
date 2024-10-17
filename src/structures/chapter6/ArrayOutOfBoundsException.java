package structures.chapter6;

public class ArrayOutOfBoundsException extends RuntimeException {
    public ArrayOutOfBoundsException(int index, int size) {
        super("Out of bounds index " + index + " accessed for array of size " + size);
    }
    public ArrayOutOfBoundsException(String message) {
        super(message);
    }
}
