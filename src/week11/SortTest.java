package week11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTest {
    public interface Sort {
        void sort(int[] array);
    }

    public static boolean isSorted(int[] array) {
        return IntStream.range(0, array.length - 1).allMatch(i -> array[i] <= array[i + 1]);
    }

    public static void testSort(Sort sort) {
        testSort(sort, "Sorting", false);
    }

    public static void testSort(Sort sort, String algorithmName) {
        testSort(sort, algorithmName, false);
    }

    public static void testSort(Sort sort, String algorithmName, boolean printArray) {
        int[] array = IntStream.generate(() -> new Random().nextInt(100)).limit(100_000).toArray();
        long startTime = System.nanoTime();
        sort.sort(array);
        long endTime = System.nanoTime();
        System.out.println(algorithmName + " took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println("isSorted: " + isSorted(array));
        if (printArray) {
            System.out.println(Arrays.toString(array));
        }
    }
}
