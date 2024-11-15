package structures.chapter8;

public abstract class AbstractMap<K, V> implements IMap<K, V> {
    protected int size;
    protected LoadFactor loadFactor = new LoadFactor(0.0, 0.75, false, true);

    @Override
    public void setLoadFactor(LoadFactor loadFactor) {
        this.loadFactor = loadFactor;
        ensureLoadFactor();
    }

    @Override
    public boolean contains(K key) {
        return get(key) == null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public abstract boolean ensureLoadFactor();
    public abstract void resize(int newCapacity);
}
