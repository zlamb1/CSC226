package structures.chapter2;

import java.util.ArrayList;

/**
 * Stack ADT implementation that is backed by the ArrayList class
 */

public class ListStack<T> implements IStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private final ArrayList<T> list;
    private int index;
    private boolean resizeable;

    public ListStack() {
        this(true);
    }

    public ListStack(boolean resizeable) {
        this(INITIAL_CAPACITY, resizeable);
    }

    public ListStack(int initialCapacity, boolean resizeable) {
        this.index = -1;
        this.list = new ArrayList<T>(initialCapacity);
        this.resizeable = resizeable;
    }

    public boolean isResizeable() {
        return resizeable;
    }

    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    @Override
    public void push(T t) {
        if (this.isFull()) {
            if (this.resizeable) {
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
    public T pop() {
        if (this.index < 0) {
            throw new StackUnderflowException();
        }

        return this.list.get(this.index--);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (t != null) {
                stringBuilder.append(t);
            } else {
                stringBuilder.append("null");
            }
            if (i != list.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
