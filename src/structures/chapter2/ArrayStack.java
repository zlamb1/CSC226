package structures.chapter2;

public class ArrayStack<T> implements IStack<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double GROWTH_FACTOR = 2;

    private T[] array;
    private int index;
    private boolean resizeable;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        this(initialCapacity, true);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity, boolean resizeable) {
        this.array = (T[]) new Object[initialCapacity];
        this.index = -1;
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
                this.ensureCapacity(this.array.length + 1);
                this.index++;
                this.array[this.index] = t;
            } else {
                throw new StackOverflowException();
            }
        } else {
            this.index++;
            this.array[this.index] = t;
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
        return this.array[this.index];
    }

    @Override
    public T peek() {
        return this.top();
    }

    @Override
    public boolean isEmpty() {
        return this.index == -1;
    }

    @Override
    public boolean isFull() {
        return this.index >= this.array.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i <= this.index; i++) {
            stringBuilder.append(this.array[i]);
            if (i != this.index) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        int newCapacity = this.array.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) Math.ceil(newCapacity * GROWTH_FACTOR);
        }
        if (this.array.length < newCapacity) {
            T[] tmp = (T[]) new Object[newCapacity];
            for (int i = 0; i <= index; i++) {
                tmp[i] = this.array[i];
            }
            this.array = tmp;
        }
    }
}
