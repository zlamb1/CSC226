package structures.chapter9;

import java.util.Comparator;

public class Driver {
    public static void testHeap() {
        IPriorityQueue<Integer> queue = new HeapPriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
    }

    public static void testBSTPQ() {
        IPriorityQueue<Integer> queue = new BSTPriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0; i <= 5; i++) {
            queue.enqueue(5 + i);
            queue.enqueue(5 - i);
        }
        System.out.println(queue);
        for (int i = 0; i <= 11; i++) {
            System.out.println(queue.dequeue());
            System.out.println(queue);
        }
    }

    public static void main(String[] args) {
        testBSTPQ();
    }
}
