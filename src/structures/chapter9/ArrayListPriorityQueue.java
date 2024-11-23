package structures.chapter9;

import structures.chapter6.ArrayList;

import java.util.Comparator;

public class ArrayListPriorityQueue<T> extends ListPriorityQueue<T> {
    public ArrayListPriorityQueue(Comparator<T> comparator) {
        super(comparator);
        list = new ArrayList<>(16);
    }
}
