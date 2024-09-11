package structures.chapter1;

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int minNumber = array[minIndex];
                array[minIndex] = array[i];
                array[i] = minNumber;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = { 27, 15, 83, 12, 104, 28, 57, 30 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
