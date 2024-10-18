package structures.chapter6;

import structures.chapter5.SortedLinkedCollection;

import java.util.Comparator;
import java.util.Iterator;

public class Driver {
    private static void testArrayList() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[] {
                1, 2, 3, 4, 5
        });

        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(6);
        System.out.println(list);

        for (int i : list) {
            System.out.println(i);
        }

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 3 && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }

        System.out.println(list);
    }

    public static void testSortedArrayList() {
        SortedArrayList<Integer> list = new SortedArrayList<>((o1, o2) -> o1 - o2);
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list);
        list.add(5);
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
    }

    private static void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[] {
                1, 2, 3, 4, 5
        });

        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(6);
        System.out.println(list);

        for (int i : list) {
            System.out.println(i);
        }

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 3 && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }

        System.out.println(list);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list);

        list.sort((o1, o2) -> o1 - o2);
        System.out.println(list);
    }

    private static void testSortedLinkedCollection() {
        SortedLinkedCollection<Integer> list = new SortedLinkedCollection<>((o1, o2) -> o1 - o2);
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list);
        list.add(5);
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
    }

    private static void testSortedLinkedList() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>((o1, o2) -> o1 - o2);
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list);
        list.add(5);
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
    }

    public static void main(String[] args) {
        testLinkedList();
    }
}
