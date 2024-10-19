package structures.chapter5;

public abstract class AbstractCollection<T> implements ICollection<T> {
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }
}
