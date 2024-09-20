package week3;

import structures.chapter2.LLNode;
import structures.chapter2.LLStack;

public class Methods {
    public static <T> int size(LLNode<T> head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.getNext();
        }
        return count;
    }
    public static String listTweaked(LLNode<Integer> head) {
        LLStack<Integer> positiveStack = new LLStack<>();
        LLStack<Integer> negativeStack = new LLStack<>();
        LLNode<Integer> cursor = head;
        while (cursor != null) {
            int element = cursor.getElement();
            if (element < 0) {
                negativeStack.push(element);
            } else if (cursor.getElement() > 0) {
                positiveStack.push(element);
            }
            cursor = cursor.getNext();
        }
        boolean anyPositive = !positiveStack.isEmpty();
        StringBuilder stringBuilder = new StringBuilder();
        cursor = positiveStack.getHead();
        while (cursor != null) {
            Integer current = cursor.getElement();
            stringBuilder.append(current);
            if (cursor.getNext() != null) {
                stringBuilder.append(" ");
            }
            cursor = cursor.getNext();
        }
        if (anyPositive && !negativeStack.isEmpty()) {
            stringBuilder.append(" ");
        }
        cursor = negativeStack.getHead();
        while (cursor != null) {
            Integer current = cursor.getElement();
            stringBuilder.append(current);
            if (cursor.getNext() != null) {
                stringBuilder.append(" ");
            }
            cursor = cursor.getNext();
        }
        return stringBuilder.toString();
    }
    public static String displayListInReverse(LLNode<Integer> head) {
        LLStack<Integer> reverseStack = new LLStack<>();
        LLNode<Integer> cursor = head;
        while (cursor != null) {
            reverseStack.push(cursor.getElement());
            cursor = cursor.getNext();
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!reverseStack.isEmpty()) {
            int current = reverseStack.top();
            reverseStack.pop();
            stringBuilder.append(current);
            if (!reverseStack.isEmpty()) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
