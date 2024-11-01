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

        System.out.println(bst.remove(100));

        Iterator<Integer> iter = bst.getIterator(IBinarySearchTree.Traversal.Inorder);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
