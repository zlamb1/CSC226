package structures.chapter8;

import structures.chapter6.ArrayList;

import javax.sound.midi.SysexMessage;
import java.util.Iterator;

/**
 * A hash map implementation that internally uses an ArrayList and linear probing.
 * @param <K> Key Type
 * @param <V> Value Type
 */

public class HashMap<K, V> implements IBoundedMap<K, V> {
    protected static int DEFAULT_CAPACITY = 16;

    protected ArrayList<MapEntry<K, V>> table;
    protected int size = 0;
    protected LoadFactor loadFactor = new LoadFactor(0.25, 0.75);

    public HashMap() {
        table = new ArrayList<>(null, DEFAULT_CAPACITY);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (shouldResize()) {
            resize(getMinCapacity(size + 1));
        } else if (size == table.size()) {
            throw new MapOverflowException();
        }
        
        int index = Math.abs(key.hashCode()) % table.size(), cursor = index;
        MapEntry<K, V> entry = table.get(cursor);
        while (entry != null && !entry.isTombstone() && !entry.getKey().equals(key)) {
            cursor = ++cursor % table.size();
            entry = table.get(cursor);
        }

        MapEntry<K, V> oldEntry = table.get(cursor);
        table.set(cursor, new MapEntry<>(key, value));

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

        int index = Math.abs(key.hashCode()) % table.size(), cursor = index;
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (!entry.isTombstone() && entry.getKey().equals(key)) {
                return entry.getValue();
            }

            cursor = ++cursor % table.size();
            if (cursor == index) break;
            entry = table.get(cursor);
        }

        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int index = Math.abs(key.hashCode()) % table.size(), cursor = index;
        MapEntry<K, V> entry = table.get(cursor);
        while (entry != null) {
            if (!entry.isTombstone() && entry.getKey().equals(key)) {
                int next = ++cursor % table.size();
                if (table.get(next) == null) {
                    table.set(cursor, null);
                } else {
                    entry.setTombstone(true);
                }

                if (shouldResize()) {
                    resize(getMinCapacity(size - 1));
                }

                size--;
                return entry.getValue();
            }
            cursor = ++cursor % table.size();
            entry = table.get(cursor);
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

    protected void resize(int newCapacity) {
        if (size > newCapacity) {
            throw new IllegalArgumentException("Cannot resize HashMap to capacity smaller than current size.");
        }

        ArrayList<MapEntry<K, V>> newTable = new ArrayList<>(null, newCapacity);

        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isTombstone()) {
                // rehash
                int index = Math.abs(entry.getKey().hashCode()) % newCapacity, cursor = index;
                while (newTable.get(cursor) != null) {
                    cursor = ++cursor % newCapacity;
                }
                newTable.set(cursor, entry);
            }
        }

        table = newTable;
    }

    protected int getMinCapacity(int size) {
        int capacity = table.size();

        if (size > capacity) {
            while (capacity < size) {
                capacity *= 2;
            }
        } else {
            while ((capacity / 2) > size) {
                capacity /= 2;
            }
        }

        return Math.max(capacity, 1);
    }

    protected boolean shouldResize() {
        double load = (double) size / table.size();
        return (loadFactor.shouldUseLowerBound() && load <= loadFactor.getLowerBound()) || (loadFactor.shouldUseUpperBound() && load >= loadFactor.getUpperBound());
    }

    public void setLoadFactor(LoadFactor loadFactor) {
        this.loadFactor = loadFactor;
        if (shouldResize()) {
            resize(getMinCapacity(size));
        }
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
}