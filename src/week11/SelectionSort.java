package week11;

public class SelectionSort {
    public static int getMinIndex(int[] array, int start, int end) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        while (start <= end) {
            int cur = array[start];
            if (cur < min) {
                min = cur;
                minIndex = start;
            }
            start++;
        }
        return minIndex;
    }

    public static int getMinIndex(int[] array, int start) {
        return getMinIndex(array, start, array.length - 1);
    }

    public static void selectionSort(int[] array) {
        int current = 0;
        while (current < array.length - 1) {
            int tmp = array[current], minIndex = getMinIndex(array, current);
            array[current] = array[minIndex];
            array[minIndex] = tmp;
            current++;
        }
    }

    public static void main(String[] args) {
        SortTest.testSort(SelectionSort::selectionSort, "Selection sort");
    }
}
