package week8;

import structures.chapter7.BinarySearchTree;

public class Main {

    public static void testBST() {
        //        100
        //      /     \
        //     20     200
        //    /  \   /   \
        //   10  30 100  300
        //  /               \
        // 5                400

        BinarySearchTree<Integer> bst = new BinarySearchTree<>((a, b) -> a - b);
        bst.add(100);
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(200);
        bst.add(150);
        bst.add(300);
        bst.add(5);
        bst.add(400);

        System.out.println("min: " + bst.min());
        System.out.println("min2: " + bst.min2());
        System.out.println("max: " + bst.max());
        System.out.println("leafCount(false): " + bst.leafCount(false));
        System.out.println("leafCount(true): " + bst.leafCount(true));
        System.out.println("oneChild(false): " + bst.oneChild(false));
        System.out.println("oneChild(true): " + bst.oneChild(true));
        System.out.println("height(false): " + bst.height(false));
        System.out.println("height(true): " + bst.height(true));
    }

    public static void testMovieCollection() {
        MovieCollection coll = new MovieCollection();
        coll.add(new Movie("Cars", "John Lasseter", "Animation", 2006));
        coll.add(new Movie("Toy Story", "John Lasseter", "Animation", 1995));
        coll.add(new Movie("Bolt", "Byron Howard", "Animation", 2008));
        coll.add(new Movie("Pulp Fiction", "Quentin Tarantino", "Comedy", 1994));

        System.out.println("moviesIn(\"Animation\"): " + coll.moviesIn("Animation"));
        System.out.println("moviesIn(\"Comedy\"): " + coll.moviesIn("Comedy"));
        System.out.println("moviesBetween(2000, 2024): " + coll.moviesBetween(2000, 2024));
        System.out.println("moviesBetween(1990, 1999): " + coll.moviesBetween(1990, 1999));
        System.out.println("moviesDirectedBy(\"John Lasseter\"): " + coll.moviesDirectedBy("John Lasseter"));
        System.out.println("moviesDirectedBy(\"Quentin Tarantino\"): " + coll.moviesDirectedBy("Quentin Tarantino"));
    }

    public static void main(String[] args) {
        testBST();
        testMovieCollection();
    }

}
