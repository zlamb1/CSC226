package structures.chapter8;

public abstract class AbstractMapEntry<K, V> implements IMapEntry<K, V> {
    protected K key;
    protected V value;

    public AbstractMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
