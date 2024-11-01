package structures.chapter7;

import structures.chapter2.ArrayStack;
import structures.chapter2.IStack;
import structures.chapter5.ICollection;
import structures.chapter6.ArrayList;
import week8.IMatch;

import java.util.Comparator;
import java.util.Iterator;

public class BinarySearchTree<T> implements IBinarySearchTree<T> {
    private final Comparator<T> comparator;
    private BinaryNode<T> root;
    private int size;

    private class BSTIterator implements Iterator<T> {
        private final ArrayStack<BinaryNode<T>> nodes;

        public BSTIterator(Traversal traversal) {
            nodes = new ArrayStack<>();
            switch (traversal) {
                case Traversal.Preorder -> preOrderTraversal(root, nodes);
                case Traversal.Inorder -> inOrderTraversal(root, nodes);
                case Traversal.Postorder -> postOrderTraversal(root, nodes);
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

    @Override
    public T get(T element) {
        if (root == null) {
            return null;
        }

        BinaryNode<T> cursor = root;
        while (cursor != null) {
            int cmp = comparator.compare(element, cursor.getValue());
            if (cmp == 0) {
                return cursor.getValue();
            } else if (cmp < 0) {
                cursor = cursor.getLeft();
            } else {
                cursor = cursor.getRight();
            }
        }

        return null;
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

    public T min2() {
        BinaryNode<T> min = inOrderSuccessor(root);
        return min == null ? null : min.getValue();
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

    public ICollection<T> search(IMatch<T> match) {
        if (root == null) return new ArrayList<>();

        ArrayList<T> list = new ArrayList<>();
        ArrayStack<BinaryNode<T>> nodes = new ArrayStack<>();
        // we are not able to perform a binary search since the match function isn't the comparator
        inOrderTraversal(root, nodes);

        while (!nodes.isEmpty()) {
            BinaryNode<T> node = nodes.pop();
            if (match.matches(node.getValue())) {
                list.add(node.getValue());
            }
        }

        return list;
    }

    public int leafCount(boolean useRecursion) {
        if (useRecursion) {
            return recursiveLeafCount(root);
        } else {
            ArrayStack<BinaryNode<T>> nodes = new ArrayStack<>();
            inOrderTraversal(root, nodes);
            int count = 0;
            while (!nodes.isEmpty()) {
                BinaryNode<T> node = nodes.pop();
                if (node.getLeft() == null && node.getRight() == null) {
                    count++;
                }
            }

            return count;
        }
    }

    public int oneChild(boolean useRecursion) {
        if (useRecursion) {
            return recursiveOneChild(root);
        } else {
            ArrayStack<BinaryNode<T>> nodes = new ArrayStack<>();
            inOrderTraversal(root, nodes);
            int count = 0;
            while (!nodes.isEmpty()) {
                BinaryNode<T> node = nodes.pop();
                if (node.getLeft() == null ^ node.getRight() == null) {
                    count++;
                }
            }

            return count;
        }
    }

    public int height(boolean useRecursion) {
        if (root == null) return 0;

        if (useRecursion) {
            return recursiveHeight(root, 1);
        } else {
            int maxHeight = 1;
            ArrayStack<Pair<BinaryNode<T>, Integer>> nodes = new ArrayStack<>();
            nodes.push(new Pair<>(root, 1));
            while (!nodes.isEmpty()) {
                Pair<BinaryNode<T>, Integer> pair = nodes.pop();
                int nextHeight = pair.getSecond() + 1;
                BinaryNode<T> node = pair.getFirst();
                if (node.getLeft() != null) {
                    maxHeight = Math.max(maxHeight, nextHeight);
                    nodes.push(new Pair<>(node.getLeft(), nextHeight));
                }
                if (node.getRight() != null) {
                    maxHeight = Math.max(maxHeight, nextHeight);
                    nodes.push(new Pair<>(node.getRight(), nextHeight));
                }
            }

            return maxHeight;
        }
    }

    protected int recursiveLeafCount(BinaryNode<T> root) {
        if (root == null) return 0;

        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        } else {
            return recursiveLeafCount(root.getLeft()) + recursiveLeafCount(root.getRight());
        }
    }

    protected int recursiveOneChild(BinaryNode<T> root) {
        if (root == null) return 0;

        if (root.getLeft() == null ^ root.getRight() == null) {
            return 1;
        } else {
            return recursiveOneChild(root.getLeft()) + recursiveOneChild(root.getRight());
        }
    }

    protected int recursiveHeight(BinaryNode<T> root, int height) {
        if (root == null) return height - 1;
        return Math.max(recursiveHeight(root.getLeft(), height + 1), recursiveHeight(root.getRight(), height + 1));
    }

    protected void preOrderTraversal(BinaryNode<T> root, IStack<BinaryNode<T>> nodes) {
        if (root != null) {
            preOrderTraversal(root.getRight(), nodes);
            preOrderTraversal(root.getLeft(), nodes);
            nodes.push(root);
        }
    }

    protected void inOrderTraversal(BinaryNode<T> root, IStack<BinaryNode<T>> nodes) {
        if (root != null) {
            inOrderTraversal(root.getRight(), nodes);
            nodes.push(root);
            inOrderTraversal(root.getLeft(), nodes);
        }
    }

    protected void postOrderTraversal(BinaryNode<T> root, IStack<BinaryNode<T>> nodes) {
        if (root != null) {
            nodes.push(root);
            postOrderTraversal(root.getRight(), nodes);
            postOrderTraversal(root.getLeft(), nodes);
        }
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
