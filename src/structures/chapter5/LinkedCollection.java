package structures.chapter5;

import structures.chapter2.DLLNode;

import java.util.Iterator;

public class LinkedCollection<T> extends AbstractCollection<T> {
    protected DLLNode<T> rear;

    private class LinkedIterator implements Iterator<T> {
        private DLLNode<T> cursor = null;
        int count = 0;

        public LinkedIterator() {
            if (rear != null) {
                cursor = rear.getNext();
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != null && count < size;
        }

        @Override
        public T next() {
            T element = cursor.getElement();
            cursor = cursor.getNext();
            count++;
            return element;
        }

        @Override
        public void remove() {
            if (size == 1) {
                rear = null;
                cursor = null;
            } else {
                DLLNode<T> node = cursor.getPrev();
                node.getPrev().setNext(cursor);
                cursor.setPrev(node.getPrev());
                if (node == rear) {
                    rear = node.getPrev();
                }
            }
            size--;
            count--;
        }
    }

    public LinkedCollection() {
        this.rear = null;
    }

    public LinkedCollection(T[] array) {
        for (T element : array) {
            add(element);
        }
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (rear == null) {
            rear = new DLLNode<>(element);
            rear.setPrev(rear);
            rear.setNext(rear);
        } else {
            DLLNode<T> newNode = new DLLNode<>(element, rear, rear.getNext());
            rear.getNext().setPrev(newNode);
            rear.setNext(newNode);
            rear = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (size == 0) {
            return false;
        }

        if (size == 1) {
            rear = null;
        } else {
            DLLNode<T> node = linearSearch(element);
            if (node == null) {
                return false;
            }
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            if (node == rear) {
                rear = node.getPrev();
            }
        }

        size--;
        return true;
    }

    @Override
    public void clear() {
        super.clear();
        rear = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        T[] array = toArray();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected DLLNode<T> head() {
        return rear.getNext();
    }

    protected DLLNode<T> linearSearch(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (rear == null) {
            return null;
        }

        DLLNode<T> cursor = rear;

        do {
            if (cursor.getElement().equals(element)) {
                return cursor;
            }
            cursor = cursor.getPrev();
        } while (cursor != rear);

        return null;
    }
}
