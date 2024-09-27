package week4;

import structures.chapter4.CircularLinkedQ;
import structures.chapter4.IQueue;

public class Main {
    public static void main(String[] args) {
        IQueue<Integer> queue = new CircularLinkedQ<>(new Integer[] {
                1, 2, 3, 4, 5
        });
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue);
        queue.enqueue(6);
        System.out.println(queue);
    }
}
