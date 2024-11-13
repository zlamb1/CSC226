package structures.chapter8;

public class MapEntry<K, V> {
    protected K key;
    protected V value;
    protected boolean tombstone;

    public MapEntry(K key, V value) {
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

    public boolean isTombstone() {
        return tombstone;
    }

    public void setTombstone(boolean tombstone) {
        this.tombstone = tombstone;
    }

    @Override
    public String toString() {
        return key.toString() + ": " + value.toString();
    }
}
