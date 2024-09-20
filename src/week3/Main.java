package week3;

import structures.chapter2.ArrayStack;
import structures.chapter2.LLStack;


public class Main {
    private static LLStack<Integer> llTestStack1 = new LLStack<>(new Integer[] {
            -1, 3, 5, -2, 0, 4
    });

    private static LLStack<Integer> llTestStack2 = new LLStack<>(new Integer[] {
            4, 2, 5, 8
    });

    private static ArrayStack<Integer> arrTestStack1 = new ArrayStack<>(new Integer[] {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    });

    private static ArrayStack<Integer> arrTestStack2 = new ArrayStack<>(new Integer[] {
            5, 6
    });

    private static ArrayStack<Integer> arrTestStack3 = new ArrayStack<>(new Integer[] {
            1, 2
    });

    public static void testLinkedListStaticMethods() {
        System.out.println("Linked-list Static Methods");
        System.out.println("testStack1");
        System.out.println("toString: " + llTestStack1);
        System.out.println("size: " + Methods.size(llTestStack1.getHead()));
        System.out.println("listTweaked: " + Methods.listTweaked(llTestStack1.getHead()));
        System.out.println("displayListInReverse: " + Methods.displayListInReverse(llTestStack1.getHead()));
        System.out.println("testStack2");
        System.out.println("toString: " + llTestStack2);
        System.out.println("size: " + Methods.size(llTestStack2.getHead()));
        System.out.println("listTweaked: " + Methods.listTweaked(llTestStack2.getHead()));
        System.out.println("displayListInReverse: " + Methods.displayListInReverse(llTestStack2.getHead()));
    }

    public static void testArrayStackMethods() {
        System.out.println("ArrayStack Methods");
        System.out.println("testStack1");
        System.out.println("toString: " + arrTestStack1);
        arrTestStack1.popSome(2);
        System.out.println("popSome(2): " + arrTestStack1);
        arrTestStack1.swapTopTwo();
        System.out.println("swapTopTwo: " + arrTestStack1);
        arrTestStack1.reverseStack();
        System.out.println("reverseStack(): " + arrTestStack1);
        System.out.println("testStack2");
        System.out.println("toString: " + arrTestStack2);
        arrTestStack2.popSome(3);
        System.out.println("popSome(3): " + arrTestStack2);
        arrTestStack2.swapTopTwo();
        System.out.println("swapTopTwo: " + arrTestStack2);
        arrTestStack2.reverseStack();
        System.out.println("reverseStack(): " + arrTestStack2);
        System.out.println("testStack3");
        System.out.println("toString: " + arrTestStack3);
        arrTestStack3.popSome(1);
        System.out.println("popSome(1): " + arrTestStack3);
        arrTestStack3.swapTopTwo();
        System.out.println("swapTopTwo: " + arrTestStack3);
        arrTestStack3.reverseStack();
        System.out.println("reverseStack(): " + arrTestStack3);
    }

    public static void main(String[] args) {
        testLinkedListStaticMethods();
        System.out.println();
        testArrayStackMethods();
    }

}
