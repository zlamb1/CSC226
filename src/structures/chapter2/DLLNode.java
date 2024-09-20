package structures.chapter2;

/**
 * Doubly-linked list ADT implementation
 */

public class DLLNode<T> {
    private T element;
    private DLLNode<T> prev, next;

    public DLLNode(T element) {
        this(element, null, null);
    }

    public DLLNode(T element, DLLNode<T> prev, DLLNode<T> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public DLLNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    public DLLNode<T> getNext() {
        return next;
    }

    public void setNext(DLLNode<T> next) {
        this.next = next;
    }
}
