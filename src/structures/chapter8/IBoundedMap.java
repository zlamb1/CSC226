package structures.chapter8;

public interface IBoundedMap<K, V> extends IMap<K, V> {
    boolean isFull();
}
