package structures.chapter8;

public interface IMapEntry<K, V> {
    K getKey();
    V getValue();
    void setValue(V value);

    default int getBucketLength() {
        return 1;
    }
}
