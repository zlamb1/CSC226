package structures.chapter7;

import structures.chapter5.ICollection;

import java.util.Iterator;

public interface IBinarySearchTree<T> extends ICollection<T> {
    enum Traversal {
        Preorder,
        Inorder,
        Postorder
    }

    T min();
    T max();

    Iterator<T> getIterator(Traversal orderType);
}
