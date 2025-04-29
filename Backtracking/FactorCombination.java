package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class FactorCombination {
    private static List<List<Integer>> res;
    public static void main(String[] args) {
        final var n = 1080;
        if (n%2 != 0) throw new IllegalArgumentException("n must be an even number");
        System.out.printf("Factor Combinations of %d\n", n);
        res = new ArrayList<>();
        backtrack(n, 2, new ArrayList<>());
        System.out.printf("Total number of combinations: %d\n", res.size());
        res.forEach(System.out::println);
    }

    private static void backtrack(int n, int start, List<Integer> list) {
        for (int i = start; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                var temp = new ArrayList<>(list);
                temp.add(n / i);
                res.add(temp);
                backtrack(n / i, i + 1, list);
                list.removeLast();
            }
        }
    }
}
