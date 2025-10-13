package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenerateValidParenthesis {
    public static void main(String[] args) {
        final var testCases = Arrays.asList(-1, 0, 1, 2, 3, 4, 5, 10, null);
        for (final var testCase: testCases) {
            var res = generateValidParenthesis(testCase);
            System.out.printf("Generated %d valid parenthesis for test-case: %d\n", res.size(), testCase);
            res.forEach(System.out::println);
        }
    }

    private static List<String> generateValidParenthesis(Integer n) {
        if (n == null || n <= 0 || n > 10) return Collections.emptyList();
        var listOfValidParenthesis = new ArrayList<String>();
        backtrack(listOfValidParenthesis, new StringBuilder(), 0, 0, n);
        return listOfValidParenthesis;
    }

    private static void backtrack(List<String> list, StringBuilder sb, int open, int close, int n) {
        if (open == n && close == n) {
            list.add(sb.toString());
        } else {
            if (open < n) {
                sb.append('(');
                backtrack(list, sb, open + 1, close, n);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (close < open) {
                sb.append(')');
                backtrack(list, sb, open, close + 1, n);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
