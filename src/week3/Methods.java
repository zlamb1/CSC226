package week3;

import structures.chapter2.DLLNode;
import structures.chapter2.DLLStack;
import structures.chapter2.LLNode;
import structures.chapter2.LLStack;

import java.util.ArrayList;

public class Methods {
    private static <T> void reverseLinkedList(LLNode<T> head) {
        ArrayList<LLNode<T>> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.getNext();
        }
        for (int i = nodes.size() - 1; i >= 1; i--) {
            nodes.get(i - 1).setNext(nodes.get(i));
        }
    }
    public static <T> int size(LLNode<T> head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.getNext();
        }
        return count;
    }
    public static String listTweaked(LLNode<Integer> head) {
        // use doubly-linked backed Stacks so that the nodes can be iterated in reverse
        DLLStack<Integer> positiveStack = new DLLStack<>();
        DLLStack<Integer> negativeStack = new DLLStack<>();
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
        DLLNode<Integer> dllCursor = positiveStack.getTail();
        while (dllCursor != null) {
            Integer current = dllCursor.getElement();
            stringBuilder.append(current);
            if (dllCursor.getPrev() != null) {
                stringBuilder.append(" ");
            }
            dllCursor = dllCursor.getPrev();
        }
        if (anyPositive && !negativeStack.isEmpty()) {
            stringBuilder.append(" ");
        }
        dllCursor = negativeStack.getTail();
        while (dllCursor != null) {
            Integer current = dllCursor.getElement();
            stringBuilder.append(current);
            if (dllCursor.getPrev() != null) {
                stringBuilder.append(" ");
            }
            dllCursor = dllCursor.getPrev();
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
