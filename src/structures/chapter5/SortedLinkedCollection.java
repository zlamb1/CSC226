package structures.chapter5;

import structures.chapter2.DLLNode;

import java.util.Comparator;

public class SortedLinkedCollection<T> extends LinkedCollection<T> implements ISortedCollection<T> {
    protected Comparator<T> comparator;

    public SortedLinkedCollection() {
        this.comparator = ComparatorUtility.getDefaultComparator();
    }

    public SortedLinkedCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        setComparator(comparator);
        SortUtility.sortDoublyLinkedList(rear.getNext(), true, comparator); 
    }

    @Override
    public boolean add(T element) {
        if (size == 0) {
            return super.add(element);
        } else {
            DLLNode<T> head = head(), cursor = head, newNode = new DLLNode<>(element, rear, head);
            do {
                if (comparator.compare(element, cursor.getElement()) <= 0) {
                    newNode.setPrev(cursor.getPrev());
                    newNode.setNext(cursor);
                    cursor.getPrev().setNext(newNode);
                    cursor.setPrev(newNode);
                    break;
                } else if (cursor == rear) {
                    rear.setNext(newNode);
                    head.setPrev(newNode);
                    rear = newNode;
                    break;
                }
                cursor = cursor.getNext();
            } while (cursor != head());
        }

        size++;
        return true;
    }
}
