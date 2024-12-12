package week11;

public class BubbleSort {
    public static void bubbleSort(int[] array) {
        int current = 0, i = array.length - 1;
        while (current < array.length - 1) {
            boolean sorted = true;
            while (i > current) {
                int cur = array[i];
                int next = array[i - 1];
                if (cur < next) {
                    sorted = false;
                    array[i] = next;
                    array[i - 1] = cur;
                }
                i--;
            }
            if (sorted) break;
            i = array.length - 1;
            current++;
        }
    }

    public static void main(String[] args) {
        SortTest.testSort(BubbleSort::bubbleSort, "Bubble sort");
    }
}
