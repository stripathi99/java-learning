package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumberCombination {
    private static final Map<Integer, String> digitMap = Map.of(
            2, "abc",
            3, "def",
            4, "ghi",
            5, "jkl",
            6, "mno",
            7, "pqrs",
            8, "tuv",
            9, "wxyz",
            0, " ");

    public static void main(String[] args) {
        final var input = "6268";
        var res = new ArrayList<String>();
        System.out.printf("Generating char combinations for input digit: %s\n", input);
        backtrack(res, input, new StringBuilder(), input.length(), 0);
        System.out.printf("Total number of combinations: %d\n", res.size());
        res.forEach(System.out::println);
    }

    private static void backtrack(List<String> list, String str, StringBuilder sb, int n, int idx) {
        if (idx == n) {
            list.add(sb.toString());
        } else {
            var currentDigitLetters = digitMap.get(str.charAt(idx) - '0');
            for (int i = 0; i < currentDigitLetters.length(); i++) {
                sb.append(currentDigitLetters.charAt(i));
                backtrack(list, str, sb, n, idx + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
