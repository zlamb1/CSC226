package structures.chapter8;

import structures.chapter6.ArrayList;

/**
 * A hash map implementation that internally uses an ArrayList and separate chaining.
 * @param <K> Key Type
 * @param <V> Value Type
 */

public class CHashMap<K, V> extends AbstractArrayMap<K, V, CHashMap.CMapEntry<K, V>> {
    public static class CMapEntry<K, V> extends AbstractMapEntry<K, V> {
        protected CMapEntry<K, V> next;

        public CMapEntry(K key, V value) {
            super(key, value);
        }

        public boolean hasNext() {
            return next != null;
        }

        public CMapEntry<K, V> getNext() {
            return next;
        }

        public void setNext(CMapEntry<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    public CHashMap() {
        super();
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (!ensureLoadFactor() && size == table.size()) {
            throw new MapOverflowException();
        }

        int index = Math.abs(key.hashCode()) % table.size();
        CMapEntry<K, V> entry = table.get(index);

        if (entry == null) {
            table.set(index, new CMapEntry<>(key, value));
            size++;
            return null;
        } else {
            CMapEntry<K, V> cursor = entry;
            while (cursor != null) {
                if (cursor.getKey().equals(key)) {
                    V oldValue = cursor.getValue();
                    cursor.setValue(value);
                    return oldValue;
                }
                if (cursor.getNext() == null) {
                    cursor.setNext(new CMapEntry<>(key, value));
                    size++;
                    return null;
                }
                cursor = cursor.getNext();
            }
        }

        return null;
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % table.size();
        CMapEntry<K, V> entry = table.get(index);

        if (entry == null) {
            return null;
        }

        CMapEntry<K, V> cursor = entry;
        while (cursor != null) {
            if (cursor.getKey().equals(key)) {
                return cursor.getValue();
            }
            cursor = cursor.getNext();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode()) % table.size();
        CMapEntry<K, V> entry = table.get(index);

        if (entry == null) {
            return null;
        }

        CMapEntry<K, V> cursor = entry, precursor = null;
        while (cursor != null) {
            if (cursor.getKey().equals(key)) {
                V value = cursor.getValue();
                if (precursor == null) {
                    table.set(index, cursor.getNext());
                } else {
                    precursor.setNext(cursor.getNext());
                }
                size--;
                return value;
            }
            precursor = cursor;
            cursor = cursor.getNext();
        }

        return null;
    }

    @Override
    public boolean resize(int newCapacity) {
        if (size > newCapacity) {
            throw new IllegalArgumentException("Cannot resize HashMap to capacity smaller than current size.");
        }

        ArrayList<CMapEntry<K, V>> newTable = new ArrayList<>(null, newCapacity);

        for (CMapEntry<K, V> entry : table) {
            if (entry != null) {
                CMapEntry<K, V> cursor = entry;
                while (cursor != null) {
                    int index = Math.abs(cursor.getKey().hashCode()) % table.size();
                    CMapEntry<K, V> at = newTable.get(index);
                    if (at == null) {
                        newTable.set(index, cursor.getNext());
                    } else {
                        CMapEntry<K, V> _cursor = cursor;
                        while (_cursor.getNext() != null) {
                            _cursor = _cursor.getNext();
                        }
                        _cursor.setNext(cursor);
                    }

                    cursor = cursor.getNext();
                }
            }
        }

        table = newTable;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < table.size(); i++) {
            CMapEntry<K, V> entry = table.get(i);
            if (entry != null) {
                stringBuilder.append(i).append(" ");
                CMapEntry<K, V> cursor = entry;
                while (cursor != null) {
                    stringBuilder.append(cursor);
                    if (cursor.hasNext()) {
                        stringBuilder.append(", ");
                    }
                    cursor = cursor.getNext();
                }
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
