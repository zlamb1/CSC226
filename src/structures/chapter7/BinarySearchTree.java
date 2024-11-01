package structures.chapter7;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T> implements IBinarySearchTree<T> {
    private final Comparator<T> comparator;
    private BinaryNode<T> root;
    private int size;

    private class BSTIterator implements Iterator<T> {
        private Traversal traversal;
        private Stack<BinaryNode<T>> nodes;

        public BSTIterator(Traversal traversal) {
            this.traversal = traversal;
            nodes = new Stack<>();
            switch (traversal) {
                case Traversal.Preorder -> preOrderTraversal(root);
                case Traversal.Inorder -> inOrderTraversal(root);
                case Traversal.Postorder -> postOrderTraversal(root);
            }
        }

        private void preOrderTraversal(BinaryNode<T> root) {
            if (root != null) {
                preOrderTraversal(root.getRight());
                preOrderTraversal(root.getLeft());
                nodes.add(root);
            }
        }

        private void inOrderTraversal(BinaryNode<T> root) {
            if (root != null) {
                inOrderTraversal(root.getRight());
                nodes.add(root);
                inOrderTraversal(root.getLeft());
            }
        }

        private void postOrderTraversal(BinaryNode<T> root) {
            if (root != null) {
                nodes.add(root);
                postOrderTraversal(root.getRight());
                postOrderTraversal(root.getLeft());
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            BinaryNode<T> node = nodes.pop();
            return node.getValue();
        }
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    @Override
    public boolean add(T element) {
        if (root == null) {
            root = new BinaryNode<>(element);
        } else {
            BinaryNode<T> cursor = root;
            while (cursor != null) {
                int cmp = comparator.compare(element, cursor.getValue());
                if (cmp == 0) {
                    cursor.setOccurrences(cursor.getOccurrences() + 1);
                    break;
                } else if (cmp < 0) {
                    BinaryNode<T> left = cursor.getLeft();
                    if (left == null) {
                        cursor.setLeft(new BinaryNode<>(element));
                        break;
                    }
                    cursor = left;
                } else {
                    BinaryNode<T> right = cursor.getRight();
                    if (right == null) {
                        cursor.setRight(new BinaryNode<>(element));
                        break;
                    }
                    cursor = cursor.getRight();
                }
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (root == null) {
            return false;
        }

        Pair<BinaryNode<T>, Boolean> pair = remove(root, element);
        root = pair.getFirst();

        if (!pair.getSecond()) {
            return false;
        }

        size--;
        return true;
    }

    public Pair<BinaryNode<T>, Boolean> remove(BinaryNode<T> root, T element) {
        boolean modified;
        if (root == null) {
            return new Pair<>(null, false);
        } else {
            int cmp = comparator.compare(element, root.getValue());
            if (cmp == 0) {
                if (root.getOccurrences() > 1) {
                    root.setOccurrences(root.getOccurrences() - 1);
                    return new Pair<>(root, true);
                } else if (root.getLeft() == null && root.getRight() == null) {
                    return new Pair<>(null, true);
                } else if (root.getLeft() == null) {
                    return new Pair<>(root.getRight(), true);
                } else if (root.getRight() == null) {
                    return new Pair<>(root.getLeft(), true);
                } else {
                    BinaryNode<T> min = inOrderSuccessor(root.getRight());
                    root.setValue(min.getValue());
                    root.setRight(remove(root.getRight(), min.getValue()).getFirst());
                    modified = true;
                }
            } else if (cmp < 0) {
                Pair<BinaryNode<T>, Boolean> pair = remove(root.getLeft(), element);
                modified = pair.getSecond();
                root.setLeft(pair.getFirst());
            } else {
                Pair<BinaryNode<T>, Boolean> pair = remove(root.getRight(), element);
                modified = pair.getSecond();
                root.setRight(pair.getFirst());
            }
        }

        return new Pair<>(root, modified);
    }

    @Override
    public T min() {
        BinaryNode<T> cursor = root;
        while (cursor != null) {
            if (cursor.getLeft() == null) {
                return cursor.getValue();
            }
            cursor = cursor.getLeft();
        }

        return null;
    }

    @Override
    public T max() {
        BinaryNode<T> cursor = root;
        while (cursor != null) {
            if (cursor.getRight() == null) {
                return cursor.getValue();
            }
            cursor = cursor.getRight();
        }

        return null;
    }

    @Override
    public Iterator<T> getIterator(Traversal orderType) {
        return new BSTIterator(orderType);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return getIterator(Traversal.Inorder);
    }

    protected BinaryNode<T> inOrderSuccessor(BinaryNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() != null) {
            return inOrderSuccessor(node.getLeft());
        } else {
            return node;
        }
    }
}
