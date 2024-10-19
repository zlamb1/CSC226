package structures.chapter5;

public class LinkedSet<T> extends LinkedCollection<T> implements ISet<T> {
    @Override
    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }
        return super.add(element);
    }

    @Override
    public ISet<T> makeSet() {
        return new LinkedSet<>();
    }
}
