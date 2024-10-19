package structures.chapter6;

import structures.chapter5.SortedLinkedCollection;

import java.util.Comparator;

public class SortedLinkedList<T> extends SortedLinkedCollection<T> implements IList<T> {
    public SortedLinkedList() {
        super();
    }

    public SortedLinkedList(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int indexOf(T target) {
        return 0;
    }

    @Override
    public T remove(int index) {
        return null;
    }
}
