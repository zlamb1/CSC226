package structures.chapter4;

public class ArrayQueue<T> implements IQueue<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private T[] array;
    private int front = -1, rear = -1, size = 0;

    @SuppressWarnings("unchecked cast")
    public ArrayQueue() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayQueue(T[] array) {
        this.array = array;
        this.front = 0;
        this.rear = array.length - 1;
        this.size = array.length;
    }

    @Override
    public void enqueue(T element) {
        if (this.isEmpty()) {
            this.front = this.rear = 0;
        } else if (this.rear == this.array.length - 1) {
            if (this.front > 0) {
                this.rear = 0;
            } else {
                this.ensureCapacity(this.array.length + 1);
                this.rear++;
            }
        } else {
            this.rear++;
        }
        this.array[this.rear] = element;
        this.size++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new QueueUnderflowException();
        }
        T element = this.array[this.front];
        if (this.front == this.rear) {
            this.front = this.rear = -1;
        } else if (this.front == this.array.length - 1) {
            this.front = 0;
        } else {
            this.front++;
        }
        this.size--;
        return element;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.array[this.front];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.front == -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int cursor = this.front;
        for (int i = 0; i < this.size; i++) {
            stringBuilder.append(this.array[cursor].toString());
            if (i != this.size - 1) {
                stringBuilder.append(", ");
            }
            cursor = ++cursor % this.array.length;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @SuppressWarnings("unchecked cast")
    private void ensureCapacity(int capacity) {
        if (capacity > this.array.length) {
            T[] newArray = (T[]) new Object[capacity];
            int cursor = this.front;
            for (int i = 0; i < this.size; i++) {
                newArray[i] = this.array[cursor];
                cursor = ++cursor % this.array.length;
            }
            this.array = newArray;
        }
    }
}
