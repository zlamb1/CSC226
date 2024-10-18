package structures.chapter5;

import structures.chapter2.DLLNode;

import java.util.Comparator;

public class SortedLinkedCollection<T> extends LinkedCollection<T> {
    protected Comparator<T> comparator;

    public SortedLinkedCollection() {
        this.comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                try {
                    return ((IComparable<T>) o1).compareTo(o2);
                } catch (ClassCastException e) {
                    System.out.println(e.getMessage());
                    return 0;
                }
            }
        };
    }

    public SortedLinkedCollection(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(T element) {
        if (super.size == 0) {
            return super.add(element);
        } else {
            DLLNode<T> head = super.head(), cursor = head, newNode = new DLLNode<>(element, super.rear, head);
            do {
                if (comparator.compare(element, cursor.getElement()) <= 0) {
                    newNode.setPrev(cursor.getPrev());
                    newNode.setNext(cursor);
                    cursor.getPrev().setNext(newNode);
                    cursor.setPrev(newNode);
                    break;
                } else if (cursor == super.rear) {
                    super.rear.setNext(newNode);
                    head.setPrev(newNode);
                    super.rear = newNode;
                    break;
                }
                cursor = cursor.getNext();
            } while (cursor != super.head());
        }
        super.size++;
        return true;
    }
}
