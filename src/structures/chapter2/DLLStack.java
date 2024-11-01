package structures.chapter2;

public class DLLStack<T> implements IStack<T> {
    DLLNode<T> head, tail;

    public DLLStack() {
        this.head = null;
        this.tail = null;
    }

    public DLLStack(T firstElement) {
        this.head = new DLLNode<T>(firstElement);
        this.tail = this.head;
    }

    public DLLStack(DLLNode<T> head) {
        this.head = head;
        this.tail = head;
        while (this.tail.getNext() != null) {
            this.tail = this.tail.getNext();
        }
    }

    public DLLStack(T[] array) {
        this.head = null;
        this.tail = null;
        for (int i = array.length - 1; i >= 0; i--) {
            this.push(array[i]);
        }
    }

    public DLLNode<T> getHead() {
        return this.head;
    }

    public DLLNode<T> getTail() {
        return this.tail;
    }

    @Override
    public void push(T t) {
        DLLNode<T> oldHead = this.head;
        this.head = new DLLNode<>(t, null, oldHead);
        if (oldHead == null) {
            this.tail = this.head;
        } else {
            oldHead.setPrev(this.head);
        }
    }

    @Override
    public T pop() {
        if (this.head == null) {
            throw new StackUnderflowException();
        }

        T element = this.head.getElement();
        this.head = this.head.getNext();

        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.setPrev(null);
        }

        return element;
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
}
