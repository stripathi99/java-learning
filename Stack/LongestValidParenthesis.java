package Stack;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

public class LongestValidParenthesis {
    public static void main(String[] args) {
        final var listOfTestCases = List.of(
                "()",
                ")(())",
                "(((()))))())"
        );

        var map = listOfTestCases.stream()
                    .collect(Collectors.toMap(s -> s,
                            LongestValidParenthesis::calculateLongestValidParenthesis));

        for(final var entry: map.entrySet()) System.out.printf("""
                %d is the length of longest valid parenthesis for the given input: %s
                """, entry.getValue(), entry.getKey());
    }

    private static int calculateLongestValidParenthesis(final String s) {
        int longest = 0;
        final var stack = new ArrayDeque<Integer>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                stack.pop();
                if(stack.isEmpty()) stack.push(i);
                else longest = Math.max(longest, i - stack.peek());
            } else stack.push(i);
        }
        return longest;
    }
}
