package BinarySearch;

public class MinRotatedSortedArray {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6, 7, -1, 0, 1, 2};
        int left = 0, right = arr.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;

            // min element lies on the right side of mid
            if(arr[mid] > arr[right]) {
                left = mid + 1;
            } else right = mid; // otherwise, the min element lies on the left side or at mid
        }

        // left points to the minimum element

        System.out.printf("Min element is: %d\n", arr[left]);
    }
}
