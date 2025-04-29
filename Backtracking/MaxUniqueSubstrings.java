package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxUniqueSubstrings {
    private static List<List<String>> res;
    private static Set<String> set;

    public static void main(String[] args) {
        res = new ArrayList<>();
        set = new HashSet<>();
        final var str = "abcabc";
        System.out.printf("Generating all unique substrings of string \"%s\"\n", str);
        backtrack(str, 0);
        res.forEach(System.out::println);
    }

    private static void backtrack(String str, int idx) {
        if (idx == str.length()) {
            res.add(new ArrayList<>(set));
            return;
           }
        for (int i = idx + 1; i <= str.length(); i++) {
            String subStr = str.substring(idx, i);
            if (set.add(subStr)) {
                backtrack(str, i);
                set.remove(subStr);
            }
        }
    }
}
