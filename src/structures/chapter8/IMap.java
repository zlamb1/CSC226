package structures.chapter8;

public interface IMap<K, V> extends Iterable<MapEntry<K, V>> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean contains(K key);
    boolean isEmpty();
    int size();
}
