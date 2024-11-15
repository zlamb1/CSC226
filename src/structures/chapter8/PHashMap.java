package structures.chapter8;

import structures.chapter6.ArrayList;

import javax.naming.OperationNotSupportedException;

/**
 * A hash map implementation that internally uses an ArrayList and open addressing.
 * @param <K> Key Type
 * @param <V> Value Type
 */

public class PHashMap<K, V> extends AbstractArrayMap<K, V, PHashMap.PMapEntry<K, V>> {
    public static class PMapEntry<K, V> extends AbstractMapEntry<K, V> {
        protected boolean tombstone;

        public PMapEntry(K key, V value) {
            super(key, value);
        }

        public boolean isTombstone() {
            return tombstone;
        }

        public void setTombstone(boolean tombstone) {
            this.tombstone = tombstone;
        }

        @Override
        public String toString() {
            return key + ": " + value;
        }
    }

    public enum ProbingStrategy {
        LINEAR,
        QUADRATIC
    }

    protected ProbingStrategy probingStrategy;

    public PHashMap() {
        this(ProbingStrategy.LINEAR);
    }

    public PHashMap(ProbingStrategy probingStrategy) {
        super();
        this.probingStrategy = probingStrategy;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (!ensureLoadFactor() && size == table.size()) {
            throw new MapOverflowException();
        }

        int index = Math.abs(key.hashCode()) % table.size(), cursor = 0;
        PMapEntry<K, V> entry = table.get(index);
        while (entry != null && !entry.isTombstone() && !entry.getKey().equals(key)) {
            // TODO: solve infinite loops with quadratic probing
            cursor++;
            entry = table.get(getProbeIndex(index, cursor));
        }

        PMapEntry<K, V> oldEntry = table.get(getProbeIndex(index, cursor));
        table.set(getProbeIndex(index, cursor), new PMapEntry<>(key, value));

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
        PMapEntry<K, V> entry = table.get(index);
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
        PMapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (!entry.isTombstone() && entry.getKey().equals(key)) {
                int next = getProbeIndex(index, cursor + 1);
                if (table.get(next) == null) {
                    table.set(getProbeIndex(index, cursor), null);
                } else {
                    entry.setTombstone(true);
                }

                size--;
                ensureLoadFactor();

                return entry.getValue();
            }

            cursor++;
            entry = table.get(getProbeIndex(index, cursor));
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        int i = 0;
        for (PMapEntry<K, V> entry : table) {
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
}