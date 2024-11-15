package structures.chapter8;

import structures.chapter6.ArrayList;

import java.util.Iterator;

public abstract class AbstractArrayMap<K, V, E extends IMapEntry<K, V>> extends AbstractMap<K, V> implements IBoundedMap<K, V> {
    public class ArrayMapIterator implements Iterator<IMapEntry<K, V>> {
        private final Iterator<E> iterator;

        public ArrayMapIterator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public IMapEntry<K, V> next() {
            return iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

    protected static int DEFAULT_CAPACITY = 16;
    protected ArrayList<E> table;

    public AbstractArrayMap() {
        table = new ArrayList<>(null, DEFAULT_CAPACITY);
    }

    @Override
    public boolean isFull() {
        return table.size() == size;
    }

    @Override
    public boolean ensureLoadFactor() {
        if (shouldGrow()) {
            resize(getLargerCapacity(size));
        } else if (shouldShrink()) {
            resize(getSmallerCapacity(size));
        } else {
            return false;
        }

        return true;
    }

    @Override
    public Iterator<IMapEntry<K, V>> iterator() {
        return new ArrayMapIterator(table.iterator());
    }

    @Override
    public void resize(int newCapacity) {
        if (size > newCapacity) {
            throw new IllegalArgumentException("Cannot resize HashMap to capacity smaller than current size.");
        }

        ArrayList<E> list = table;
        table = new ArrayList<>(null, newCapacity);
        size = 0;

        for (E entry : list) {
            if (entry != null) {
                put(entry);
            }
        }
    }

    protected void put(E entry) {
        put(entry.getKey(), entry.getValue());
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
