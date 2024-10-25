package week7;

import java.util.Arrays;

public class Main {

    public static boolean isUpperBound(int[] arr, int item, int index) {
        if (index >= arr.length) {
            return true;
        }
        if (arr[index] > item) {
            return false;
        } else {
            return isUpperBound(arr, item, index + 1);
        }
    }

    public static boolean isUpperBound(int[] arr, int item) {
        return isUpperBound(arr, item, 0);
    }

    public static String noX(String str) {
        int index = str.indexOf("x");
        if (index < 0) {
            return str;
        } else {
            String newString = str.substring(0, index) + str.substring(index + 1);
            return noX(newString);
        }
    }

    public static void reverse(int[] arr, int size) {
        if (size > 0) {
            int tmp = arr[size - 1];
            arr[size - 1] = arr[arr.length - size];
            arr[arr.length - size] = tmp;
            reverse(arr, size - 1);
        }
    }

    public static void reverse(int[] arr) {
        reverse(arr, arr.length / 2);
    }

    public static void diagonalPrint(String str, int index, boolean reverse) {
        if (index < str.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            int numSpaces = reverse ? str.length() - index - 1 : index;
            stringBuilder.append(" ".repeat(Math.max(0, numSpaces)));
            stringBuilder.append(str.charAt(index));
            System.out.println(stringBuilder);
            diagonalPrint(str, index + 1, reverse);
        }
    }

    public static void diagonalPrint(String str, boolean reverse) {
        diagonalPrint(str, 0, reverse);
    }

    public static int countHanoiMoves(int n) {
        if (n == 1) {
            return 1;
        }

        return 2 * countHanoiMoves(n - 1) + 1;
    }

    public static void main(String[] args) {
        System.out.println("------------------");
        int[] array1 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(Arrays.toString(array1));
        System.out.println("isUpperBound(3): " + isUpperBound(array1, 3));
        System.out.println("isUpperBound(5): " + isUpperBound(array1, 5));
        System.out.println("isUpperBound(6): " + isUpperBound(array1, 6));
        System.out.println("------------------");
        System.out.println("noX(xaxb): " + noX("xaxb"));
        System.out.println("noX(abc): " + noX("abc"));
        System.out.println("noX(xx): " + noX("xx"));
        System.out.println("------------------");
        int[] array2 = { 1, 2, 3, 4, 5, 6 };
        reverse(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println("------------------");
        diagonalPrint("hello", false);
        diagonalPrint("hello", true);
        System.out.println("------------------");
        System.out.println("countHanoiMoves(3): " + countHanoiMoves(3));
        System.out.println("countHanoiMoves(8): " + countHanoiMoves(8));
        System.out.println("countHanoiMoves(8): " + countHanoiMoves(12));
    }

}
