package structures.chapter4;

import structures.chapter2.DLLNode;
import structures.chapter2.LLNode;

public class LinkedDeque<T> implements IDeque<T> {
    private DLLNode<T> rear;
    private int size;

    public LinkedDeque() {
        this.rear = null;
        this.size = 0;
    }

    @Override
    public void pushFront(T element) {

    }

    @Override
    public void pushBack(T element) {

    }

    @Override
    public T popFront() {
        return null;
    }

    @Override
    public T popBack() {
        return null;
    }

    @Override
    public T peekFront() {
        return null;
    }

    @Override
    public T peekBack() {
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
