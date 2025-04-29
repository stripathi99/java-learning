package DynamicProgramming;

public class MinCostWithKSkip {
    public static void main(String[] args) {
        int k = 0;
        var tasks = new int[] {10, 12, 8, 15, 18, 20, 25};
        var result = calculateMinCostBruteForce(tasks, k);
        System.out.println(result);
    }

    private static int calculateMinCostBruteForce(int[] tasks, int k) {
        return 0;
    }
}
