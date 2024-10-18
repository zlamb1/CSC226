package week6;

import structures.chapter6.ArrayList;

public class Main {
    public static void main(String[] args) {
        // test-case #1
        ArrayList<Integer> list = new ArrayList<>();
        list.retainAll(list);
        System.out.println(list);

        // test-case #2
        ArrayList<Integer> list2 = new ArrayList<>(new Integer[] { 1, 2, 3 }), list3 = new ArrayList<>(new Integer[] { 1, 2, 3 });
        list2.retainAll(list3);
        System.out.println(list2);

        // test-case #3
        list3.remove(0);
        list2.retainAll(list3);
        System.out.println(list2);

        // test-case #4
        list2 = new ArrayList<>(new Integer[] { 1, 2, 3 });
        list3.remove(1);
        list2.retainAll(list3);
        System.out.println(list2);

        // test-case #5
        list2 = new ArrayList<>(new Integer[] { 1, 2, 3 });
        list3 = new ArrayList(new Integer[] { 4 });
        list2.retainAll(list3);
        System.out.println(list2);
    }
}
