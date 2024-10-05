package structures.chapter4;

import structures.chapter2.DLLNode;

public class LinkedDeque<T> implements IDeque<T> {
    private DLLNode<T> rear;
    private int size;

    public LinkedDeque() {
        this.rear = null;
        this.size = 0;
    }

    public LinkedDeque(T[] array) {
        this();
        for (T t : array) {
            this.pushBack(t);
        }
    }

    @Override
    public void pushFront(T element) {
        DLLNode<T> newNode = new DLLNode<>(element, this.rear, this.rear);
        if (this.size == 0) {
            this.rear = newNode;
            this.rear.setPrev(this.rear);
            this.rear.setNext(this.rear);
        } else {
            newNode.setNext(this.rear.getNext());
            this.rear.getNext().setPrev(newNode);
            this.rear.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public void pushBack(T element) {
        DLLNode<T> newNode = new DLLNode<>(element);
        switch (this.size) {
            case 0:
                this.rear = newNode;
                this.rear.setPrev(this.rear);
                this.rear.setNext(this.rear);
                break;
            case 1:
                newNode.setPrev(this.rear);
                newNode.setNext(this.rear);
                this.rear.setPrev(newNode);
                this.rear.setNext(newNode);
                this.rear = newNode;
                break;
            default:
                DLLNode<T> head = this.rear.getNext();
                newNode.setPrev(this.rear);
                newNode.setNext(head);
                head.setPrev(newNode);
                this.rear.setNext(newNode);
                this.rear = newNode;
                break;
        }
        this.size++;
    }

    @Override
    public T popFront() {
        if (this.isEmpty()) throw new DequeUnderflowException();
        DLLNode<T> oldHead = this.rear.getNext();
        if (this.size == 1) {
            this.rear = null;
        } else {
            DLLNode<T> newHead = oldHead.getNext();
            newHead.setPrev(this.rear);
            this.rear.setNext(newHead);
        }
        this.size--;
        return oldHead.getElement();
    }

    @Override
    public T popBack() {
        if (this.isEmpty()) throw new DequeUnderflowException();
        DLLNode<T> head = this.rear.getNext();
        T element = this.rear.getElement();
        if (this.size == 1) {
            this.rear = null;
        } else {
            DLLNode<T> newRear = this.rear.getPrev();
            newRear.setNext(head);
            head.setPrev(newRear);
            this.rear = newRear;
        }
        this.size--;
        return element;
    }

    @Override
    public T peekFront() {
        return switch (this.size) {
            case 0  -> null;
            case 1  -> this.rear.getElement();
            default -> this.rear.getNext().getElement();
        };
    }

    @Override
    public T peekBack() {
        if (this.isEmpty()) return null;
        return this.rear.getElement();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        switch (this.size) {
            case 0: break;
            case 1: stringBuilder.append(rear.getElement()); break;
            default:
                DLLNode<T> head = this.rear.getNext();
                DLLNode<T> cursor = head;
                do {
                    stringBuilder.append(cursor.getElement());
                    cursor = cursor.getNext();
                    if (cursor != head) {
                        stringBuilder.append(", ");
                    }
                } while (cursor != head);
                break;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
