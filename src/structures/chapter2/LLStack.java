package structures.chapter2;

import java.util.ArrayList;

public class LLStack<T> implements IStack<T> {
    LLNode<T> head;

    public LLStack() {
        this.head = null;
    }

    public LLStack(T firstElement) {
        this.head = new LLNode<T>(firstElement);
    }

    public LLStack(LLNode<T> head) {
        this.head = head;
    }

    public LLStack(T[] array) {
        this.head = null;
        for (T t : array) {
            this.push(t);
        }
    }

    public LLNode<T> getHead() {
        return this.head;
    }

    @Override
    public void push(T t) {
        this.head = new LLNode<T>(t, this.head);
    }

    @Override
    public void pop() {
        if (this.head == null) {
            throw new StackUnderflowException();
        }
        this.head = this.head.getNext();
    }

    @Override
    public T top() {
        if (this.head == null) {
            throw new StackUnderflowException();
        }
        return this.head.getElement();
    }

    @Override
    public T peek() {
        return top();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        ArrayList<LLNode<T>> nodes = new ArrayList<>();
        LLNode<T> cursor = this.head;
        while (cursor != null) {
            nodes.add(cursor);
            cursor = cursor.getNext();
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = nodes.size() - 1; i >= 0; i--) {
            stringBuilder.append(nodes.get(i).getElement());
            if (i != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
