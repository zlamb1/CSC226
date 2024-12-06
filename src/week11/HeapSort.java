package week11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class HeapSort {
    public static void heapify(int[] array, int index, int len) {
        if (index < 0 || index >= array.length) return;

        int leftIndex = index * 2 + 1, rightIndex = index * 2 + 2, largest = index;

        if (leftIndex < len && array[leftIndex] > array[largest]) {
            largest = leftIndex;
        }

        if (rightIndex < len && array[rightIndex] > array[largest]) {
            largest = rightIndex;
        }

        if (largest != index) {
            int root = array[index];
            array[index] = array[largest];
            array[largest] = root;
            heapify(array, largest, len);
        }
    }

    public static void heapSort(int[] array) {
        int len = array.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(array, i, len);
        }

        int size = 1;
        while (size <= len) {
            int max = array[0], end = len - size;
            array[0] = array[end];
            array[end] = max;
            heapify(array, 0, len - size);
            size++;
        }
    }

    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                arr[j + 1] = arr[j];
                if (tmp > arr[j]) {
                    arr[j + 1] = tmp;
                    break;
                } else if (j == 0) {
                    arr[0] = tmp;
                }
            }
        }
    }

    static void binaryInsertionSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i+1] >= arr[i]) continue;
            int loc = findLocation(i, arr);
            int temp = arr[i+1];
            for(int j = i + 1; j > loc; j--) {
                arr[j] = arr[j-1];
            }
            arr[loc] = temp;
        }
    }

    static int findLocation(int i, int[] arr) {
        int toMove = arr[i+1];
        int left = 0, right = i;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = toMove - arr[mid];
            if (cmp > 0) {
                left = mid + 1;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] array = IntStream.generate(() -> new Random().nextInt(100)).limit(100_000).toArray();

        long startTime = System.nanoTime();
        insertionSort(array);
        long endTime = System.nanoTime();
        System.out.println("Insertion sort took " + (endTime - startTime) / 1_000_000 + "ms");

        array = IntStream.generate(() -> new Random().nextInt(100)).limit(100_000).toArray();
        startTime = System.nanoTime();
        binaryInsertionSort(array);
        endTime = System.nanoTime();
        System.out.println("Binary insertion sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }
}
