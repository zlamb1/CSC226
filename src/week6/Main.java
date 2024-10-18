package week6;

import structures.chapter5.Point;
import structures.chapter6.ArrayList;

public class Main {
    private static void retainAllTests() {
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
        list3 = new ArrayList<>(new Integer[] { 4 });
        list2.retainAll(list3);
        System.out.println(list2);
    }

    private static void predicateIfTests() {
        java.util.ArrayList<Point> list = new java.util.ArrayList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));
        System.out.println(list);
        list.removeIf(p -> p.equals(new Point(1, 1)));
        System.out.println(list);
        list.removeIf(p -> p.compareTo(new Point(0, 0)) < 0);
        System.out.println(list);
    }

    private static void replaceAllTests() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.replaceAll(x -> -x);
        System.out.println(list);
    }

    public static void main(String[] args) {
        retainAllTests();
        System.out.println();
        predicateIfTests();
        System.out.println();
        replaceAllTests();
    }
}
