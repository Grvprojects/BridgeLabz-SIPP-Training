
import java.util.Arrays;
import java.util.HashSet;

public class FinalChallenge {
    public static int findFirstMissingPositive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (num > 0) set.add(num);
        }

        for (int i = 1; i <= arr.length + 1; i++) {
            if (!set.contains(i)) return i;
        }

        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};
        System.out.println("First missing positive: " + findFirstMissingPositive(arr));
        System.out.println("Index of 3: " + binarySearch(arr, 3));
    }
}
