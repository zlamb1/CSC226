package structures.chapter2;

/**
 * Singly linked list ADT implementation
 */

public class LLNode<T> {
    private T element;
    private LLNode<T> next;

    public LLNode(T element) {
        this(element, null);
    }

    public LLNode(T element, LLNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public LLNode<T> getNext() {
        return next;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }
}
