package week10;

import structures.chapter9.ArrayListPriorityQueue;
import structures.chapter9.IPriorityQueue;

import java.util.Comparator;

public class Main {
    public static String printArray(int[] array, Comparator<Integer> comparator) {
        IPriorityQueue<Integer> pq = new ArrayListPriorityQueue<>(comparator);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i : array) {
            pq.enqueue(i);
        }

        int counter = 0;
        while (!pq.isEmpty()) {
            int i = pq.dequeue();
            stringBuilder.append(i);
            counter++;
            if (!pq.isEmpty()) {
                stringBuilder.append(", ");
            }
            if (counter != 0 && counter % 5 == 0) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
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
            array[i] = (int)Math.ceil(Math.random() * 1000);
        }
        System.out.println("Array: \n" + printArray(array, Comparator.reverseOrder()));
        System.out.println("printArray: \n" + printArray(array, Main::compareDigitSum));
    }

    public static void main(String[] args) {
        testPrintArray();
    }
}
