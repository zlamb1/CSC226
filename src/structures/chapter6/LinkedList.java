package structures.chapter6;

import structures.chapter2.LLNode;
import structures.chapter5.ICollection;

import java.util.Iterator;

public class LinkedList<T> implements IList<T> {
    private LLNode<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(int index, T element) {
        checkBounds(index);
        LLNode<T> newNode = new LLNode<>(element);
        if (this.size == 1) {
            this.head.setNext(newNode);
        } else {
            LLNode<T> cursor = this.head;
            int indexCursor = 0;
            while (cursor != null) {
                if (indexCursor == index - 1) {
                    newNode.setNext(cursor.getNext());
                    cursor.setNext(newNode);
                    break;
                }
                cursor = cursor.getNext();
            }
        }
        this.size++;
    }

    @Override
    public T set(int index, T element) {
        checkBounds(index);
        T oldElement = null;
        LLNode<T> cursor = this.head;
        int indexCursor = 0;
        while (cursor != null) {
            if (indexCursor == index) {
                oldElement = cursor.getElement();
                cursor.setElement(element);
                break;
            }
            cursor = cursor.getNext();
            indexCursor++;
        }
        return oldElement;
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        LLNode<T> cursor = this.head;
        int indexCursor = 0;
        while (cursor != null) {
            if (indexCursor == index) {
                return cursor.getElement();
            }
            cursor = cursor.getNext();
            indexCursor++;
        }
        return null;
    }

    @Override
    public int indexOf(T target) {
        LLNode<T> cursor = this.head;
        int indexCursor = 0;
        while (cursor != null) {
            if (cursor.getElement().equals(target)) {
                return indexCursor;
            }
            cursor = cursor.getNext();
            indexCursor++;
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean add(T element) {
        if (this.size == 0) {
            this.head = new LLNode<>(element);
        } else {
            LLNode<T> newNode = new LLNode<>(element);
            LLNode<T> cursor = this.head;
            while (cursor != null) {
                if (cursor.getNext() == null) {
                    cursor.setNext(newNode);
                }
                cursor = cursor.getNext();
            }
        }
        this.size++;
        return true;
    }

    @Override
    public T get(T element) {
        LLNode<T> cursor = this.head;
        while (cursor != null) {
            if (cursor.getElement().equals(element)) {
                return cursor.getElement();
            }
            cursor = cursor.getNext();
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public boolean remove(T element) {
        int indexOf = this.indexOf(element);
        if (indexOf >= 0) {
            this.remove(indexOf);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean addAll(ICollection<T> other) {
        return false;
    }

    @Override
    public boolean retainAll(ICollection<T> other) {
        return false;
    }

    @Override
    public boolean removeAll(ICollection<T> other) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    protected void checkBounds(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayOutOfBoundsException(index, this.size);
        }
    }
}
