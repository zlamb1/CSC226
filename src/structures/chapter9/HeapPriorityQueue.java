package structures.chapter9;

import java.util.Comparator;

public class HeapPriorityQueue<T> implements IBoundedPriorityQueue<T> {
    protected final static double DEFAULT_GROWTH_FACTOR = 2.0;
    protected final static int DEFAULT_CAPACITY = 16;

    protected Comparator<T> comparator;
    protected T[] array;
    protected int size = 0, lastIndex = -1;

    @SuppressWarnings("unchecked")
    public HeapPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(T element) throws PriorityQueueOverflow {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (size == array.length) {
            ensureCapacity(size + 1);
        }

        array[++lastIndex] = element;
        reheapUp(lastIndex);

        size++;
    }

    @Override
    public T dequeue() throws PriorityQueueUnderflow {
        if (size == 0) {
            throw new PriorityQueueUnderflow();
        }

        T element = array[0];
        array[0] = array[lastIndex--];
        reheapDown(0);

        size--;
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return getNodeAsString(0, "");
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        int newCapacity = array.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) Math.ceil(newCapacity * DEFAULT_GROWTH_FACTOR);
        }

        T[] newArray = (T[]) new Object[newCapacity];

        for (int i = 0; i <= lastIndex; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    protected void reheapDown(int index) {
        if (index < 0 || index >= lastIndex) {
            return;
        }

        T element = array[index];
        int left = getLeft(index), right = getRight(index);

        if (left > lastIndex) {
            return;
        }

        int largest = left;
        int leftCmp = comparator.compare(array[left], element), rightCmp = comparator.compare(array[right], element);
        int largestCmp = Math.max(leftCmp, rightCmp);

        if (rightCmp > leftCmp) {
            largest = right;
        }

        if (largestCmp > 0) {
            array[index] = array[largest];
            array[largest] = element;
            reheapDown(largest);
        }
    }

    protected void reheapUp(int index) {
        if (index <= 0 || index > lastIndex) {
            return;
        }

        T element = array[index];
        int parent = getParent(index);

        if (parent >= 0) {
            int cmp = comparator.compare(element, array[parent]);
            if (cmp > 0) {
                array[index] = array[parent];
                array[parent] = element;
                reheapUp(parent);
            }
        }
    }

    protected int getParent(int index) {
        return (index - 1) / 2;
    }

    protected int getLeft(int index) {
        return index * 2 + 1;
    }

    protected int getRight(int index) {
        return index * 2 + 2;
    }

    protected String getNodeAsString(int index, String prefix) {
        if (index < 0 || index > lastIndex) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        T element = array[index];

        if (element != null) {
            stringBuilder.append(element);
            if (stringBuilder.length() % 2 == 0) {
                stringBuilder.insert(0, "0");
            }
            int halfLen = (int) (stringBuilder.length() / 2.0);
            prefix += StringUtility.repeat(' ', halfLen);

            int left = getLeft(index), right = getRight(index);
            if (left <= lastIndex) {
                stringBuilder.append("\n");
                if (right <= lastIndex) {
                    stringBuilder
                        .append(prefix)
                        .append("├─")
                        .append(getNodeAsString(left,prefix + "│ "))
                        .append("\n")
                        .append(prefix)
                        .append("└─")
                        .append(getNodeAsString(right, prefix + "  "));
                } else {
                    stringBuilder.append(prefix).append("└─").append(getNodeAsString(left, prefix));
                }
            }
        }

        return stringBuilder.toString();
    }
}
