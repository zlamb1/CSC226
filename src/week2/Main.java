package week2;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Main {
    public static <T> T testFunction(Callable<T> callable) {
        long start = System.nanoTime();
        T result;
        try {
            result = callable.call();
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
        long duration = System.nanoTime() - start;
        System.out.println("Execution time: " + (duration / 1000) + "μs");
        return result;
    }

    public interface ISumUpToK {
        String call(int[] arr, int k);
    }

    public interface IMoveZerosToFront {
        int[] call(int[] arr);
    }

    public interface ISmallestDistanceNeighbors {
        int call(int[] arr);
    }

    public interface IRemoveRepeats {
        int[] call(int[] arr);
    }

    public static void testSumUpToK(String title, ISumUpToK sumUpToK) {
        System.out.println(title + " -> ");
        int[][] testInputs = {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 30, 20, 10 },
                { 1, 2, 0, 4, 5, 6, 7 }
        };
        int[] testInputs2 = { 5, 50, 6 };
        for (int i = 0; i < testInputs.length; i++) {
            System.out.println("Test " + (i + 1) + "). " + Arrays.toString(testInputs[i]));
            int finalI = i;
            System.out.println("Results: " + testFunction(() -> sumUpToK.call(testInputs[finalI], testInputs2[finalI])));
        }
        System.out.println();
    }

    public static void testMoveZerosToFront(String title, IMoveZerosToFront moveZerosToFront) {
        System.out.println(title + " -> ");
        int[][] testInputs = {
                { 0, 0, 0, 1, 2, 3 },
                { 1, 0, 2, 0, 3, 0 },
                { 1, 0, 3, 0, 0, 7, 6 },
                { 1, 2, 3, 0, 0, 0 }
        };
        for (int i = 0; i < testInputs.length; i++) {
            System.out.println("Test " + (i + 1) + "). " + Arrays.toString(testInputs[i]));
            int finalI = i;
            System.out.println("Results: " + Arrays.toString(testFunction(() -> moveZerosToFront.call(testInputs[finalI]))));
        }
        System.out.println();
    }

    public static void testSmallestDistanceNeighbors(String title, ISmallestDistanceNeighbors smallestDistanceNeighbors) {
        System.out.println(title + " -> ");
        int[][] testInputs = {
                { 2, 6, 8, 1, 3, 9, 7, 10 },
                { 0, 3, 7, 9, 10 },
                { 1, 10 }
        };
        for (int i = 0; i < testInputs.length; i++) {
            System.out.println("Test " + (i + 1) + "). " + Arrays.toString(testInputs[i]));
            int finalI = i;
            System.out.println("Results: " + testFunction(() -> smallestDistanceNeighbors.call(testInputs[finalI])));
        }
        System.out.println();
    }

    public static void testRemoveRepeats(String title, IRemoveRepeats removeRepeats) {
        System.out.println(title + " -> ");
        int[][] testInputs = {
                { 3, 3, 3, 3 },
                { 3, 2, 7, 2, 2, 6, 7 },
                { 1, 9, 2, 3, 8, 1, 2, 7, 4, 1, 9, 4, 2, 0, 9, 4, 0, 1, 9, 2, 4 }
        };
        for (int i = 0; i < testInputs.length; i++) {
            System.out.println("Test " + (i + 1) + "). " + Arrays.toString(testInputs[i]));
            int finalI = i;
            System.out.println("Results: " + Arrays.toString(testFunction(() -> removeRepeats.call(testInputs[finalI]))));
        }
        int[] randomData = new int[1000000];
        for (int i = 0; i < randomData.length; i++) {
            randomData[i] = (int) (Math.random() * 1000);
        }
        System.out.println("Test 4). ");
        testFunction(() -> removeRepeats.call(randomData));
        System.out.println();
    }

    public static void main(String[] args) {
        testSumUpToK("sumUpToK", Methods::sumUpToK);
        testSumUpToK("sumUpToK2", Methods::sumUpToK2);
        testSumUpToK("sumUpToK3", Methods::sumUpToK3);
        testMoveZerosToFront("moveZerosToFront", Methods::moveZerosToFront);
        testSmallestDistanceNeighbors("smallestDistanceNeighbors", Methods::smallestDistanceNeighbors);
        testRemoveRepeats("removeRepeats", Methods::removeRepeats);
        testRemoveRepeats("removeRepeats2", Methods::removeRepeats2);
    }
}
