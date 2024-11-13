package structures.chapter8;

public class MapOverflowException extends RuntimeException {
    public MapOverflowException() {
        super("The map is full.");
    }
}
