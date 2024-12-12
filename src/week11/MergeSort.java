package week11;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int[] left = Arrays.copyOfRange(array, 0, array.length / 2), right = Arrays.copyOfRange(array, array.length / 2, array.length);
        mergeSort(left);
        mergeSort(right);

        int l = 0, r = 0;
        while (l < left.length || r < right.length) {
            if (r >= right.length || (l < left.length && left[l] <= right[r])) {
                array[l + r] = left[l];
                l++;
            } else {
                array[l + r] = right[r];
                r++;
            }
        }

    }

    public static void main(String[] args) {
        SortTest.testSort(MergeSort::mergeSort, "Merge sort");
    }
}
