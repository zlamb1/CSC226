package week10;

import structures.chapter9.ArrayListPriorityQueue;
import structures.chapter9.IPriorityQueue;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static String printArray(int[] array, Comparator<Integer> comparator) {
        IPriorityQueue<Integer> pq = new ArrayListPriorityQueue<>(comparator);
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i : array) {
            pq.enqueue(i);
        }

        while (!pq.isEmpty()) {
            int i = pq.dequeue();
            stringBuilder.append(i);
            if (!pq.isEmpty()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.append("]").toString();
    }

    public static int getDigitSum(int a) {
        int sum = 0;
        while (a > 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum;
    }

    public static int compareDigitSum(int a, int b) {
        return getDigitSum(b) - getDigitSum(a);
    }

    public static void testPrintArray() {
        int[] array = new int[25];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 1000);
        }
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("printArray: " + printArray(array, Main::compareDigitSum));
    }

    public static void main(String[] args) {
        testPrintArray();
    }
}
