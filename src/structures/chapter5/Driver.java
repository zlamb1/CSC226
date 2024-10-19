package structures.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class Driver {
    public static void testArrayCollection() {
        ArrayCollection<Integer> coll = new ArrayCollection<>(), coll2 = new ArrayCollection<>();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        System.out.println(coll);
        coll.remove(2);
        System.out.println(coll);
        for (int el : coll) {
            System.out.println(el);
        }
        coll.add(5);
        coll.add(6);
        coll.add(7);
        System.out.println(coll);
        Iterator<Integer> iter = coll.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println(coll);
        coll.add(5);
        coll.add(6);
        coll.add(7);
        coll2.add(5);
        coll2.add(6);
        coll.retainAll(coll2);
        System.out.println(coll);
        coll.removeAll(coll2);
        System.out.println(coll);
        coll.add(5);
        coll.add(6);
        coll.add(7);
        coll.add(7);
        coll.removeAll(7);
        coll.add(7);
        System.out.println(coll);
    }

    public static class ComparableInteger implements IComparable<ComparableInteger> {
        public int i;

        public ComparableInteger(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(ComparableInteger o) {
            return this.i - o.i;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ComparableInteger ci) {
                return this.i == ci.i;
            }
            return false;
        }

        @Override
        public String toString() {
            return this.i + "";
        }
    }

    public static void testSortedArrayCollection() {
        SortedArrayCollection<ComparableInteger> coll = new SortedArrayCollection<>(), coll2 = new SortedArrayCollection<>();
        coll.add(new ComparableInteger(3));
        coll.add(new ComparableInteger(2));
        coll.add(new ComparableInteger(1));
        System.out.println(coll);
        coll.add(new ComparableInteger(1));
        coll.add(new ComparableInteger(10));
        coll.add(new ComparableInteger(7));
        System.out.println(coll);
        coll.remove(new ComparableInteger(1));
        coll.remove(new ComparableInteger(3));
        System.out.println(coll);
        coll2.add(new ComparableInteger(2));
        coll2.add(new ComparableInteger(7));
        coll.retainAll(coll2);
        System.out.println(coll);
        Iterator<ComparableInteger> iter = coll.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            iter.remove();
        }
        System.out.println(coll);
    }

    public static void testLinkedCollection() {
        LinkedCollection<Integer> coll = new LinkedCollection<>(), coll2 = new LinkedCollection<>();
        System.out.println(coll);
        coll.add(1);
        coll.add(2);
        coll.add(3);
        System.out.println(coll);
        coll.remove(3);
        System.out.println(coll);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        System.out.println(coll);
        Iterator<Integer> iter = coll.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            iter.remove();
        }
        System.out.println(coll);
    }

    public static void testLinkedSet() {
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set);
    }

    public static void testMergeSort() {
        Integer[] array = new Integer[] {
                5, 8, 3, 2, 10, 9, 1, 7, 6, 4
        };
        SortUtility.mergeSort(array, (i1, i2) -> i1 - i2);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        testMergeSort();
    }
}
