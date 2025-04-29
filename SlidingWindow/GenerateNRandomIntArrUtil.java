package SlidingWindow;

import java.util.Random;

public class GenerateNRandomIntArrUtil {
    private static final Random random = new Random(42);
    public static int[] generateRandomArray(int n, int min, int max) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = random.nextInt(min, max + 1);
        return arr;
    }
}
