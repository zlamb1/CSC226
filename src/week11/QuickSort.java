package week11;

public class QuickSort {
    public static int split(int[] array, int start, int end) {
        int value = array[start], _start = start;

        while (start <= end) {
            boolean cont = true;
            while (cont) {
                if (array[start] <= value) {
                    start++;
                    cont = start <= end;
                } else break;
            }

            while (cont) {
                if (array[end] > value) {
                    end--;
                    cont = start <= end;
                } else break;
            }

            if (start < end) {
                int tmp = array[start];
                array[start++] = array[end];
                array[end--] = tmp;
            }
        }

        // swap split value with value at split index
        array[_start] = array[end];
        array[end] = value;

        return end;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (end - start > 1) {
            int splitIndex = split(array, start, end);
            quickSort(array, start, splitIndex - 1);
            quickSort(array, splitIndex + 1, end);
        }
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        SortTest.testSort(QuickSort::quickSort, "Quick sort");
    }
}
