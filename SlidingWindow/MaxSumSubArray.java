package SlidingWindow;

import java.util.Arrays;

public class MaxSumSubArray {
    public static void main(String[] args) {
        final int[] arr = GenerateNRandomIntArrUtil.generateRandomArray(10, 1, 10);
        final int k = 4;
        final int result = maxSumSubArrayOptimized(arr, k);
        System.out.println(Arrays.toString(arr));
        System.out.println(result);
    }

    private static int maxSumSubArrayBruteForce(final int[] arr, final int k) {
        if(arr.length < k) return 0;
        int maxSum = Integer.MIN_VALUE, currSum;
        for(int i = 0; i < arr.length - k + 1; i++) {
            currSum = 0;
            for(int j = i; j < i + k; j++) currSum += arr[j];
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    private static int maxSumSubArrayOptimized(final int[] arr, final int k) {
        if(arr.length < k) return 0;
        int maxSum = Integer.MIN_VALUE, currSum = 0, start = 0;
        for(int end = 0; end < arr.length; end++) {
            currSum += arr[end];
            if(end - start + 1 >= k) {
                maxSum = Math.max(maxSum, currSum);
                currSum -= arr[start++];
            }
        }
        return maxSum;
    }
}
