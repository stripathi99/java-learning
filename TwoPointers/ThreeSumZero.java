package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumZero {
    public static void main(String[] args) {
        final int[] nums = {-3, 0, 1, 2, -1, 1, -2};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int n = nums.length;
        var res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int start = i + 1, end = n - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < 0) start++;
                else if (sum > 0) end--;
                else {
                    res.add(List.of(nums[i], nums[start++], nums[end--]));
                    while (start < end && nums[start] == nums[start - 1]) start++;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                }
            }
        }

        System.out.println(res);
    }
}
