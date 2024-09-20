package week3;

import structures.chapter2.DLLStack;
import structures.chapter2.LLStack;

public class Main {
    private static LLStack<Integer> testStack1 = new LLStack<>(new Integer[] {
            -1, 3, 5, -2, 0, 4
    });

    private static LLStack<Integer> testStack2 = new LLStack<>(new Integer[] {
            4, 2, 5, 8
    });

    public static void main(String[] args) {
        System.out.println("testStack1");
        System.out.println("toString: " + testStack1);
        System.out.println("size: " + Methods.size(testStack1.getHead()));
        System.out.println("listTweaked: " + Methods.listTweaked(testStack1.getHead()));
        System.out.println("displayListInReverse: " + Methods.displayListInReverse(testStack1.getHead()));
        System.out.println("testStack2");
        System.out.println("toString: " + testStack2);
        System.out.println("size: " + Methods.size(testStack2.getHead()));
        System.out.println("listTweaked: " + Methods.listTweaked(testStack2.getHead()));
        System.out.println("displayListInReverse: " + Methods.displayListInReverse(testStack2.getHead()));
    }


}
