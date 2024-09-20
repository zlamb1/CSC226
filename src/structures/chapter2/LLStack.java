package structures.chapter2;

public class LLStack<T> implements IStack<T> {
    LLNode<T> head;

    public LLStack() {
        this.head = null;
    }

    public LLStack(T firstElement) {
        this.head = new LLNode<T>(firstElement);
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
        StringBuilder stringBuilder = new StringBuilder("[");
        LLNode<T> cursor = this.head;
        while (cursor != null) {
            T element = cursor.getElement();
            if (element != null) {
                stringBuilder.append(element);
            }
            cursor = cursor.getNext();
            if (cursor != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
