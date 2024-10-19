package structures.chapter6;

import structures.chapter2.DLLNode;
import structures.chapter5.LinkedCollection;
import structures.chapter5.SortUtility;

import java.util.Comparator;

public class LinkedList<T> extends LinkedCollection<T> implements IList<T> {
    public LinkedList() {
        super();
    }

    public LinkedList(T[] array) {
        super(array);
    }

    @Override
    public void add(int index, T element) {
        this.boundsCheck(index);
        DLLNode<T> cursor = super.head(), newNode = new DLLNode<>(element);
        int current = 0;
        do {
            if (current == index) {
                newNode.setPrev(cursor.getPrev());
                newNode.setNext(cursor);
                cursor.getPrev().setNext(newNode);
                cursor.setPrev(newNode);
                break;
            }
            cursor = cursor.getNext();
            current++;
        } while (cursor != super.head());
        super.size++;
    }

    @Override
    public T set(int index, T element) {
        this.boundsCheck(index);
        T oldElement = null;
        DLLNode<T> cursor = super.head();
        int current = 0;
        do {
            if (current == index) {
                oldElement = cursor.getElement();
                cursor.setElement(element);
                break;
            }
            cursor = cursor.getNext();
            current++;
        } while (cursor != super.head());
        return oldElement;
    }

    @Override
    public T get(int index) {
        this.boundsCheck(index);
        DLLNode<T> cursor = super.head();
        int current = 0;
        do {
            if (current == index) {
                return cursor.getElement();
            }
            cursor = cursor.getNext();
            current++;
        } while (cursor != super.head());
        return null;
    }

    @Override
    public int indexOf(T target) {
        DLLNode<T> cursor = super.head();
        int current = 0;
        do {
            if (cursor.getElement().equals(target)) {
                return current;
            }
            cursor = cursor.getNext();
            current++;
        } while (cursor != super.head());
        return -1;
    }

    @Override
    public T remove(int index) {
        this.boundsCheck(index);

        if (super.size == 1) {
            T element = super.rear.getElement();
            super.rear = null;
            super.size--;
            return element;
        }

        DLLNode<T> cursor = super.head();
        int current = 0;
        T element = null;
        do {
            if (current == index) {
                element = cursor.getElement();
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                break;
            }
            cursor = cursor.getNext();
            current++;
        } while (cursor != super.head());

        super.size--;
        return element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        rear = SortUtility.sortDoublyLinkedList(rear, true, comparator).getNext();
    }

    protected void boundsCheck(int index) {
        if (index < 0 || index >= super.size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
