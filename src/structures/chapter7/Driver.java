package structures.chapter7;

import java.util.Iterator;

public class Driver {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((a, b) -> a - b);
        bst.add(100);
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(200);
        bst.add(150);
        bst.add(300);
        bst.add(5);

        System.out.println("min: " + bst.min());
        System.out.println("min2: " + bst.min2());
        System.out.println("leafCount(false): " + bst.leafCount(false));
        System.out.println("leafCount(true): " + bst.leafCount(true));
        System.out.println("oneChild(false): " + bst.oneChild(false));
        System.out.println("oneChild(true): " + bst.oneChild(true));
        System.out.println("height(false): " + bst.height(false));
        System.out.println("height(true): " + bst.height(true));
    }

}
