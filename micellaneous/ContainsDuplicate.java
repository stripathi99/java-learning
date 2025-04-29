package micellaneous;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {
    public static void main(String[] args) {
        var nums = new int[] {1, 2, 3, 1, 2, 4, 9, 5, 6, 7, 8, 9, 3};
        System.out.printf("Contains duplicate: %b%n", containsDuplicateUsingSorting(nums));
        System.out.printf("Contains duplicate: %b%n", containsDuplicateUsingSet(nums));
    }

    // Time: O(n)
    // Space: O(n)
    private static boolean containsDuplicateUsingSet(final int[] nums) {
        if (nums == null || nums.length < 2) return false;
        final var set = new HashSet<Integer>();
        for(final var num: nums) if (!set.add(num)) return true;
        return false;
    }

    // Time: O(nlogn) + O(n) => O(nlogn)
    // Space: O(1)
    private static boolean containsDuplicateUsingSorting(final int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) return true;
        }
        return false;
    }
}
