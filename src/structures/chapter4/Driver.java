package structures.chapter4;

public class Driver {
    public static void main(String[] args) {
        LinkedDeque<Integer> deque = new LinkedDeque<>(new Integer[] {
                1, 2, 3, 4, 5, 6, 7
        });
        System.out.println(deque);
        System.out.println("popBack: " + deque.popBack());
        System.out.println(deque);
        System.out.println("popFront: " + deque.popFront());
        System.out.println(deque);
        System.out.println("pushFront(1)");
        deque.pushFront(1);
        System.out.println(deque);
        System.out.println("pushBack(7)");
        deque.pushBack(7);
        System.out.println(deque);
    }
}
