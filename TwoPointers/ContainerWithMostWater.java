package TwoPointers;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        final int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        final int maxArea = maxAreaOptimized(height, height.length);
        System.out.println(maxArea);
    }

    // Time: O(n)
    private static int maxAreaOptimized(int[] height, int n) {
        int maxArea = -1, left = 0, right = n - 1, area;
        while (left < right) {
            area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }

    // Time: O(n^2)
    private static int maxAreaBruteForce(int[] height, int n) {
        int maxArea = 0, area;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
