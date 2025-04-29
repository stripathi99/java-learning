package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class FourSum {
    private final static int SIZE = 1_000;

    private static int[] generateLargeArray() {
        var nums = new int[SIZE];
        var random = new Random();
        for(int i = 0; i < SIZE; i++) {
            nums[i] = random.nextInt(SIZE) - SIZE / 2; // Generate numbers between -SIZE/2 and SIZE/2
        }
        return nums;
    }

    // n = nums.length
    private static int generateTarget(int[] nums, int n) {
        var random = new Random();

        int i = random.nextInt(n);
        int j = random.nextInt(n);
        int k = random.nextInt(n);
        int l = random.nextInt(n);

        // Ensure indices are distinct
        while (i == j || i == k || i == l || j == k || j == l || k == l) {
            i = random.nextInt(n);
            j = random.nextInt(n);
            k = random.nextInt(n);
            l = random.nextInt(n);
        }

        return nums[i] + nums[j] + nums[k] + nums[l];
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
//                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
//        };
//
//        int target = 8;
        var arr = generateLargeArray();
        var target = generateTarget(arr, SIZE);

        List<List<Integer>> res;

        for(int i = 0; i < 5; i++) {
            var startTime = System.nanoTime();
            res = searchQuadrupletsUsingBruteForce(arr, target, arr.length);
            var endTime = System.nanoTime();
            //System.out.printf("Res: %s. It took %dms to execute with O(n^4) as time complexity.\n", res, (endTime - startTime) / 1_000_000);
            System.out.printf("It took %dms to execute with O(n^4) as time complexity.\n", (endTime - startTime) / 1_000_000);
        }

        for(int i = 0; i < 5; i++) {
            var startTime = System.nanoTime();
            res = searchQuadrupletsOptimized(arr, target, arr.length);
            var endTime = System.nanoTime();
            //System.out.printf("Res: %s. It took %dms to execute with O(n^3) as time complexity.\n", res, (endTime - startTime) / 1_000_000);
            System.out.printf("It took %dms to execute with O(n^3) as time complexity.\n", (endTime - startTime) / 1_000_000);
        }
    }

    private static List<List<Integer>> searchQuadrupletsUsingBruteForce(int[] arr, int target, int len) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        int sum;
        Arrays.sort(arr);
        var set = new HashSet<List<Integer>>();
        for(int i = 0; i < len - 3; i++) {
            for(int j = i + 1; j < len - 2; j++) {
                for(int k = j + 1; k < len - 1; k++) {
                    for(int l = k + 1; l < len; l++) {
                        sum = arr[i] + arr[j] + arr[k] + arr[l];
                        if(sum == target) {
                            var list = List.of(arr[i], arr[j], arr[k], arr[l]);
                            if(!set.contains(list)) {
                                quadruplets.add(list);
                                set.add(list);
                            }
                        }
                    }
                }
            }
        }
        return quadruplets;
    }

    private static List<List<Integer>> searchQuadrupletsOptimized(int[] arr, int target, int len) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        for(int first = 0; first < len - 3; first++) {
            if(first > 0 && arr[first] == arr[first - 1]) continue; // skip duplicates
            for(int second = first + 1; second < len - 2; second++) {
                if(second > first + 1 && arr[second] == arr[second - 1]) continue; // skip duplicates
                int third = second + 1, fourth = len - 1;
                while(third < fourth) {
                    int sum = arr[first] + arr[second] + arr[third] + arr[fourth];
                    if (sum < target) third++;
                    else if (sum > target) fourth--;
                    else {
                        res.add(List.of(arr[first], arr[second], arr[third++], arr[fourth--]));
                        // skip duplicates
                        while(third < fourth && arr[third] == arr[third - 1]) third++;
                        while(third < fourth && arr[fourth] == arr[fourth + 1]) fourth--;
                    }
                }
            }
        }
        return res;
    }
}
