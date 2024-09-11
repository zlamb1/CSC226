package structures.chapter2;

import java.util.ArrayList;

/**
 * Stack ADT implementation that is backed by the ArrayList class
 */

public class Stack<T> implements IStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private final ArrayList<T> list;
    private int index;
    private boolean isResizeable;

    public Stack() {
        this(true);
    }

    public Stack(boolean isResizeable) {
        this(INITIAL_CAPACITY, isResizeable);
    }

    public Stack(int initialCapacity, boolean isResizeable) {
        this.index = -1;
        this.list = new ArrayList<T>(initialCapacity);
        this.isResizeable = isResizeable;
    }

    @Override
    public void push(T t) {
        if (this.isFull()) {
            if (this.isResizeable) {
                this.index++;
                this.list.add(t);
            } else {
                throw new StackOverflowException();
            }
        } else {
            this.index++;
            this.list.set(this.index, t);
        }
    }

    @Override
    public void pop() {
        if (this.index < 0) {
            throw new StackUnderflowException();
        }
        this.index--;
    }

    @Override
    public T top() {
        if (this.index < 0) {
            throw new StackUnderflowException();
        }
        return this.list.get(this.index);
    }

    @Override
    public T peek() {
        return top();
    }

    @Override
    public boolean isEmpty() {
        return this.index < 0;
    }

    @Override
    public boolean isFull() {
        return this.index == this.list.size() - 1;
    }
}
