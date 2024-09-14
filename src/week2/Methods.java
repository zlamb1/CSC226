package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Methods {
    private static int getOccurrences(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                count++;
            }
        }
        return count;
    }
    // O(n^2)
    // f(n) = n^2
    public static String sumUpToK(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] + arr[j] == k) {
                    return arr[i] + " " + arr[j];
                }
            }
        }
        return "";
    }
    // O(n*k)
    // f(n) = ceil(k / 2)(n + 1) + 1
    // caveat: this method cannot handle negative values of k
    public static String sumUpToK2(int[] arr, int k) {
        HashMap<Integer, Integer> numOccurrences = new HashMap<>();
        for (int i = 0; i <= k / 2; i++) {
            int diff = k - i;
            if (!numOccurrences.containsKey(i))
                numOccurrences.put(i, getOccurrences(arr, i));
            if (!numOccurrences.containsKey(diff))
                numOccurrences.put(diff, getOccurrences(arr, diff));
            if (i == diff) {
                if (numOccurrences.get(i) > 1) {
                    return i + " " + i;
                }
            } else if (numOccurrences.get(i) > 0 && numOccurrences.get(diff) > 0) {
                return i + " " + diff;
            }
        }
        return "";
    }
    // O(n)
    // f(n) = 3n + 1
    public static String sumUpToK3(int[] arr, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            int diff = k - i;
            if (set.contains(diff)) {
                return i + " " + diff;
            }
            set.add(i);
        }
        return "";
    }
    // O(n)
    // f(n) = 2n + 3
    public static int[] moveZerosToFront(int[] arr) {
        int zeroCount = 0, next = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[next] = arr[i];
                next--;
            } else {
                zeroCount++;
            }
        }
        for (int i = 0; i < zeroCount; i++) {
            arr[i] = 0;
        }
        return arr;
    }
    // O(n)
    // f(n) = 8n + 2
    static int smallestDistanceNeighbors(int[] arr) {
        int minDistance = Integer.MAX_VALUE, index = -1;
        if (arr.length == 2) return 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int dist = Math.abs(arr[i] - arr[i - 1]);
            if (dist < minDistance) {
                index = i - 1;
                minDistance = dist;
            }
            dist = Math.abs(arr[i] - arr[i + 1]);
            if (dist < minDistance) {
                index = i;
                minDistance = dist;
            }
        }
        return index;
    }
    // O(n^2)
    // f(n) = 3n + 2
    static int[] removeRepeats(int[] arr) {
        ArrayList<Integer> set = new ArrayList<>();
        for (int i : arr) {
            if (!set.contains(i)) {
                set.add(i);
            }
        }
        int[] result = new int[set.size()];
        for (int i = 0; i < set.size(); i++) {
            result[i] = set.get(i);
        }
        return result;
    }
    // O(n)
    // f(n) = 2n + 1
    static int[] removeRepeats2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        return set.stream().mapToInt(Number::intValue).toArray();
    }
}
