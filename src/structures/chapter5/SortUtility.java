package structures.chapter5;

import structures.chapter2.DLLNode;
import structures.chapter2.LLNode;

import java.util.Arrays;
import java.util.Comparator;

public class SortUtility {

    /**
     * [worst|average|best] time-complexity: O(nlog(n))<br/>
     * space-complexity: O(n)
     * @param array The array to be sorted.
     * @param comparator The comparison method to be used.
     * @param <T> The type of array to be sorted.
     */
    public static <T> void mergeSort(T[] array, Comparator<T> comparator) {
        if (array.length < 2) {
            return;
        }

        int mid = (int) Math.ceil(array.length / 2.0);
        T[] left = Arrays.copyOfRange(array, 0, mid), right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        int l = 0, r = 0;
        while (l < left.length || r < right.length) {
            if (l < left.length && (r >= right.length || comparator.compare(left[l], right[r]) < 0)) {
                array[l + r] = left[l];
                l++;
            } else {
                array[l + r] = right[r];
                r++;
            }
        }
    }

    public static <T> DLLNode<T> sortDoublyLinkedList(DLLNode<T> head, boolean isCircularlyLinked, Comparator<T> comparator) {
        if (head == null) {
            return null;
        }

        DLLNode<T> newHead = new DLLNode<>(head.getElement());
        DLLNode<T> cursor = head;

        if (isCircularlyLinked) {
            newHead.setPrev(newHead);
            newHead.setNext(newHead);
            do {
                DLLNode<T> innerCursor = newHead;
                do {
                    if (comparator.compare(cursor.getElement(), innerCursor.getElement()) < 0) {
                        DLLNode<T> newNode = new DLLNode<>(cursor.getElement(), innerCursor.getPrev(), innerCursor);
                        innerCursor.getPrev().setNext(newNode);
                        innerCursor.setPrev(newNode);
                        if (innerCursor == newHead) {
                            newHead = newNode;
                        }
                        break;
                    } else if (innerCursor.getNext() == newHead) {
                        DLLNode<T> newNode = new DLLNode<>(cursor.getElement(), innerCursor, newHead);
                        newHead.setPrev(newNode);
                        innerCursor.setNext(newNode);
                        break;
                    }
                    innerCursor = innerCursor.getNext();
                } while (innerCursor != newHead);
                cursor = cursor.getNext();
            } while (cursor != head);
        } else {
            while (cursor != null) {
                DLLNode<T> precursor = null, innerCursor = newHead;
                while (innerCursor != null) {
                    if (comparator.compare(cursor.getElement(), innerCursor.getElement()) < 0) {
                        if (precursor == null) {
                            DLLNode<T> newNode = new DLLNode<>(cursor.getElement(), null, innerCursor);
                            innerCursor.setPrev(newNode);
                            newHead = newNode;
                        } else {
                            DLLNode<T> newNode = new DLLNode<>(cursor.getElement(), precursor, innerCursor);
                            innerCursor.setPrev(newNode);
                            precursor.setNext(newNode);
                        }
                        break;
                    } else if (innerCursor.getNext() == null) {
                        innerCursor.setNext(new DLLNode<>(cursor.getElement()));
                        break;
                    }

                    precursor = innerCursor;
                    innerCursor = innerCursor.getNext();
                }

                cursor = cursor.getNext();
            }
        }

        return newHead;
    }

}
