package structures.chapter9;

import java.util.Comparator;

public class Driver {
    public static void main(String[] args) {
        IPriorityQueue<Integer> queue = new HeapPriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
    }
}
