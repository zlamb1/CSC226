package structures.chapter8;

import structures.chapter6.ArrayList;

import java.util.Iterator;

/**
 * A hash map implementation that internally uses an ArrayList and open addressing.
 * @param <K> Key Type
 * @param <V> Value Type
 */

public class HashMap<K, V> implements IBoundedMap<K, V> {
    public enum ProbingStrategy {
        LINEAR,
        QUADRATIC
    }

    protected static int DEFAULT_CAPACITY = 16;

    protected ArrayList<MapEntry<K, V>> table;
    protected int size = 0;
    protected LoadFactor loadFactor = new LoadFactor(0.1, 1.0, false, true);
    protected ProbingStrategy probingStrategy = ProbingStrategy.LINEAR;

    public HashMap() {
        table = new ArrayList<>(null, DEFAULT_CAPACITY);
    }

    public HashMap(ProbingStrategy probingStrategy) {
        this();
        this.probingStrategy = probingStrategy;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (!resize() && size == table.size()) {
            throw new MapOverflowException();
        }
        
        int index = Math.abs(key.hashCode()) % table.size(), cursor = 0;
        MapEntry<K, V> entry = table.get(index);
        while (entry != null && !entry.isTombstone() && !entry.getKey().equals(key)) {
            // TODO: solve infinite loops with quadratic probing
            cursor++;
            entry = table.get(getProbeIndex(index, cursor));
        }

        MapEntry<K, V> oldEntry = table.get(getProbeIndex(index, cursor));
        table.set(getProbeIndex(index, cursor), new MapEntry<>(key, value));

        if (oldEntry == null || oldEntry.isTombstone()) {
            size++;
        } else {
            return oldEntry.getValue();
        }

        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int index = Math.abs(key.hashCode()) % table.size(), cursor = 0;
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (!entry.isTombstone() && entry.getKey().equals(key)) {
                return entry.getValue();
            }

            cursor++;
            if (cursor == table.size()) break;
            entry = table.get(getProbeIndex(index, cursor));
        }

        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int index = Math.abs(key.hashCode()) % table.size(), cursor = 0;
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (!entry.isTombstone() && entry.getKey().equals(key)) {
                int next = getProbeIndex(index, cursor + 1);
                if (table.get(next) == null) {
                    table.set(getProbeIndex(index, cursor), null);
                } else {
                    entry.setTombstone(true);
                }

                size--;
                resize();

                return entry.getValue();
            }

            cursor++;
            entry = table.get(getProbeIndex(index, cursor));
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == table.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return table.iterator();
    }

    public void setLoadFactor(LoadFactor loadFactor) {
        this.loadFactor = loadFactor;
        resize();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        int i = 0;
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isTombstone()) {
                if (i != 0) stringBuilder.append(", ");
                stringBuilder.append(entry);
                i++;
            }
        }
        return stringBuilder.append("}").toString();
    }

    protected int getProbeIndex(int index, int cursor) {
        return switch (probingStrategy) {
            case LINEAR -> (index + cursor) % table.size();
            case QUADRATIC -> (index + (cursor * cursor)) % table.size();
        };
    }

    public boolean resize(int capacity) {
        if (size > capacity) {
            throw new IllegalArgumentException("Cannot resize HashMap to capacity smaller than current size.");
        }

        ArrayList<MapEntry<K, V>> newTable = new ArrayList<>(null, capacity);

        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isTombstone()) {
                // rehash
                int index = Math.abs(entry.getKey().hashCode()) % capacity, cursor = 0;
                MapEntry<K, V> _entry = table.get(getProbeIndex(index, cursor));
                while (_entry != null) {
                    cursor++;
                    _entry = newTable.get(getProbeIndex(index, cursor));
                }
                newTable.set(getProbeIndex(index, cursor), entry);
            }
        }

        table = newTable;
        return true;
    }

    protected boolean resize() {
        if (shouldGrow()) {
            return resize(getLargerCapacity(size));
        } else if (shouldShrink()) {
            return resize(getSmallerCapacity(size));
        } else {
            return false;
        }
    }

    protected int getLargerCapacity(int size) {
        int capacity = table.size();
        while (((double) size / capacity) >= loadFactor.getUpperBound()) {
            capacity *= 2;
        }
        return capacity;
    }

    protected int getSmallerCapacity(int size) {
        int capacity = table.size();
        while (((double) size / capacity) <= loadFactor.getLowerBound()) {
            capacity /= 2;
        }
        return capacity;
    }

    protected boolean shouldGrow() {
        double load = (double) size / table.size();
        return loadFactor.shouldUseUpperBound() && load >= loadFactor.getUpperBound();
    }

    protected boolean shouldShrink() {
        double load = (double) size / table.size();
        return loadFactor.shouldUseLowerBound() && load <= loadFactor.getLowerBound();
    }
}