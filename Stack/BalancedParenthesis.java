package Stack;

import java.util.ArrayDeque;
import java.util.Map;

class BalancedParenthesis {

    final static Map<Character, Character> map = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    public static void main(String[] args) {
        System.out.println(checkForBalancedParenthesis("{}()[({)]"));
        System.out.println(checkForBalancedParenthesis("]]"));
        System.out.println(checkForBalancedParenthesis("{[()]}[](){}"));
        System.out.println(checkForBalancedParenthesis("{[(]}[](){}"));
    }

    private static boolean checkForBalancedParenthesis(String str) {
        final var stack = new ArrayDeque<Character>();

        for (final char c : str.toCharArray()) {
            // push open bracket into the stack
            // c == '(' || c == '{' || c == '['
            if (map.containsValue(c)) stack.push(c);
            else {
                // trying to push a close bracket into an empty stack,
                // return false as there's a mismatch
                if(stack.isEmpty()) return false;

                // matching brackets found
                // pop the current element from the stack
                if (map.get(c) == stack.peek()) stack.pop();
            }
        }

        // if the stack is empty, means there matching brackets.
        // if not, then there remains an open bracket
        return stack.isEmpty();
    }
}