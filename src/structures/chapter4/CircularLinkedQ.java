package structures.chapter4;

import structures.chapter2.LLNode;

public class CircularLinkedQ<T> implements IQueue<T> {
    private LLNode<T> rear;
    private int size;

    public CircularLinkedQ() {
        this.rear = null;
        this.size = 0;
    }

    public CircularLinkedQ(T[] array) {
        this.rear = null;
        this.size = array.length;
        for (T element : array) {
            this.enqueue(element);
        }
    }

    @Override
    public void enqueue(T element) {
        if (this.isEmpty()) {
            this.rear = new LLNode<>(element);
            this.rear.setNext(this.rear);
        } else {
            LLNode<T> tmp = new LLNode<>(element, this.rear.getNext());
            this.rear.setNext(tmp);
            this.rear = tmp;
        }
        this.size++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new QueueUnderflowException();
        }
        // there is only one element
        if (this.rear == this.rear.getNext()) {
            T element = this.rear.getElement();
            this.rear = null;
            return element;
        }
        T element = this.rear.getNext().getElement();
        this.rear.setNext(this.rear.getNext().getNext());
        this.size--;
        return element;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.rear.getNext().getElement();
    }

    @Override
    public boolean isEmpty() {
        return this.rear == null;
    }

    @Override
    public int getSize() {
         return this.size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if (!this.isEmpty()) {
            LLNode<T> front = this.rear.getNext();
            LLNode<T> cursor = front;
            do {
                stringBuilder.append(cursor.getElement().toString());
                cursor = cursor.getNext();
                if (cursor != front) {
                    stringBuilder.append(", ");
                }
            } while (cursor != front);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
