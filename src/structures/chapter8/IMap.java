package structures.chapter8;

import javax.naming.OperationNotSupportedException;

public interface IMap<K, V> extends Iterable<IMapEntry<K, V>> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean contains(K key);
    boolean isEmpty();
    int size();
    void setLoadFactor(LoadFactor loadFactor);

    default void resize(int newCapacity) {
        throw new UnsupportedOperationException();
    }

    default double getAverageBucketSize() {
        throw new UnsupportedOperationException();
    }

    default int getEmptyBuckets() {
        throw new UnsupportedOperationException();
    }
}
