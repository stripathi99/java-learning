package SlidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;

public class SlidingWindowMaximum {
    private static final int MIN_RANGE = -1000, MAX_RANGE = 1000;
    private static final int N = 100_000, K = 30_000;
    private static int[] arr;

    public static void main(String[] args) {
        System.out.println("Generating random numbers...");
        arr = GenerateNRandomIntArrUtil.generateRandomArray(N, MIN_RANGE, MAX_RANGE);
        System.out.println("Generated random numbers.");
        var startTime = System.currentTimeMillis();
        //bruteForceApproach();
        optimizedApproachUsingDeque();
        var endTime = System.currentTimeMillis();
        System.out.printf("It took %dms to execute.\n", endTime - startTime);
    }

    private static void optimizedApproachUsingDeque() {
        if (K < 2) return;
        final int[] result = new int[N - K + 1];
        final var deque = new ArrayDeque<Integer>();
        for (int i = 0; i < N; i++) {
            // Remove indices from the front, out of the current window
            if (!deque.isEmpty() && deque.peekFirst() == i - K) deque.pollFirst();

            // Remove indices from the back, whose values are less than nums[i]
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) deque.pollLast();

            // Offer the current index to the deque
            deque.offerLast(i);

            // Remove indices whose values are less than nums[i]
            if (i >= K - 1 && !deque.isEmpty()) result[i - K + 1] = arr[deque.peekFirst()];
        }

        if (result.length <= 100) System.out.println(Arrays.toString(result));
        else System.out.printf("Too many elements (%d) to print.\n", result.length);
    }

    private static void bruteForceApproach() {
        if (K < 2) return;
        final int[] result = new int[N - K + 1];
        for (int windowEnd = 0; windowEnd < N - K + 1; windowEnd++) {
            int max = Integer.MIN_VALUE;
            for (int windowStart = windowEnd; windowStart < windowEnd + K; windowStart++) {
                max = Math.max(max, arr[windowStart]);
            }
            result[windowEnd] = max;
        }

        if (result.length <= 100) System.out.println(Arrays.toString(result));
        else System.out.printf("Too many elements (%d) to print.\n", result.length);
    }
}
