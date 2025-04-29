package DynamicProgramming;

import java.util.Arrays;
import java.util.Random;

public class Knapsack {
    private final static int SIZE = 10_000;

    public static void main(String[] args) {
        int[] profits = new int[SIZE], weights = new int[SIZE];
        int capacity = SIZE >> 2;
        //var dp = new Integer[SIZE][capacity + 1];

        var random = new Random(42);
        Arrays.fill(profits, random.nextInt(1, SIZE));
        Arrays.fill(weights, random.nextInt(1, SIZE / 10));

        var start = System.currentTimeMillis();
        int maxProfit = calculateProfitBottomUp(profits, weights, capacity, SIZE);
        var end = System.currentTimeMillis();
        System.out.printf("Max profit: %d. It took %dms to compute.", maxProfit, end - start);
    }

//    private static int calculateProfitBruteForce(int[] profits, int[] weights, int capacity, int n, int i) {
//        if(capacity <= 0 || i >= n) return 0;
//        int profit1 = 0;
//        if(weights[i] <= capacity) {
//            profit1 = profits[i] + calculateProfitBruteForce(profits, weights, capacity - weights[i], n, i + 1);
//        }
//
//        int profit2 = calculateProfitBruteForce(profits, weights, capacity, n, i + 1);
//        return Math.max(profit1, profit2);
//    }

//    private static int calculateProfitTopDown(int[] profits, int[] weights, Integer[][] dp, int capacity, int n, int i) {
//        if(capacity <= 0 || i >= n) return 0;
//        if(dp[i][capacity] != null) return dp[i][capacity];
//
//        int profit1 = 0;
//        if(weights[i] <= capacity) {
//            profit1 = profits[i] + calculateProfitTopDown(profits, weights, dp, capacity - weights[i], n, i + 1);
//        }
//
//        int profit2 = calculateProfitTopDown(profits, weights, dp, capacity, n, i + 1);
//        dp[i][capacity] = Math.max(profit1, profit2);
//        return dp[i][capacity];
//    }

    private static int calculateProfitBottomUp(int[] profits, int[] weights, int capacity, int n) {
        //int[][] dp = new int[2][capacity + 1];
        int[] dp = new int[capacity + 1];
        //for(int i = 0; i < SIZE; i++) dp[i][0] = 0;
        for(int c = 0; c <= capacity; c++) if(weights[0] <= c) dp[c] = profits[0];
        for(int i = 1; i < n; i++) {
            for(int c = capacity; c >= 0; c--) {
                int profit1 = 0;
                if(weights[i] <= c) profit1 = profits[i] + dp[c - weights[i]];
                dp[c] = Math.max(profit1, dp[c]);
            }
        }

        return dp[capacity];
    }
}
