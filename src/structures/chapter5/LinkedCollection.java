package structures.chapter5;

import structures.chapter2.DLLNode;

import java.util.Iterator;

public class LinkedCollection<T> implements ICollection<T> {
    protected DLLNode<T> rear;
    protected int size;

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
            return this.cursor != null && count < size;
        }

        @Override
        public T next() {
            T element = this.cursor.getElement();
            this.cursor = this.cursor.getNext();
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
        this.size = 0;
    }

    public LinkedCollection(T[] array) {
        this.size = 0;
        for (T element : array) {
            this.add(element);
        }
    }

    @Override
    public boolean add(T element) {
        if (this.rear == null) {
            this.rear = new DLLNode<>(element);
            this.rear.setPrev(this.rear);
            this.rear.setNext(this.rear);
        } else {
            DLLNode<T> newNode = new DLLNode<>(element, this.rear, this.rear.getNext());
            this.rear.getNext().setPrev(newNode);
            this.rear.setNext(newNode);
            this.rear = newNode;
        }
        this.size++;
        return true;
    }

    @Override
    public T get(T element) {
        DLLNode<T> node = this.linearSearch(element);
        if (node == null) {
            return null;
        }
        return node.getElement();
    }

    @Override
    public boolean contains(T element) {
        DLLNode<T> node = this.linearSearch(element);
        return node != null;
    }

    @Override
    public boolean remove(T element) {
        if (this.size == 0 || element == null) {
            return false;
        }
        if (this.size == 1) {
            this.rear = null;
        } else {
            DLLNode<T> node = this.linearSearch(element);
            if (node == null) {
                return false;
            }
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            if (node == this.rear) {
                this.rear = node.getPrev();
            }
        }
        this.size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.rear == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        if (this.size == 0) {
            return array;
        }
        int counter = 0;
        DLLNode<T> head = this.rear.getNext();
        DLLNode<T> cursor = head;
        do {
            array[counter] = cursor.getElement();
            cursor = cursor.getNext();
            counter++;
        } while (cursor != head);
        return array;
    }

    @Override
    public void clear() {
        this.rear = null;
        this.size = 0;
    }

    @Override
    public boolean addAll(ICollection<T> other) {
        boolean modified = false;
        for (T element : other) {
            if (this.add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(ICollection<T> other) {
        return false;
    }

    @Override
    public boolean removeAll(ICollection<T> other) {
        boolean modified = false;
        for (T element : other) {
            if (this.remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        T[] array = this.toArray();
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
        return this.rear.getNext();
    }

    protected DLLNode<T> linearSearch(T element) {
        if (this.rear == null || element == null) {
            return null;
        }
        DLLNode<T> cursor = this.rear;
        do {
            if (cursor.getElement().equals(element)) {
                return cursor;
            }
            cursor = cursor.getPrev();
        } while (cursor != this.rear);
        return null;
    }
}
