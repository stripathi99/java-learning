package micellaneous;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class OldestNonRepeatingCharInAStream {
    private static Map<Character, Integer> map;
    private static Deque<Character> deque;

    public static void main(String[] args) {
        map = new HashMap<>();
        deque = new ArrayDeque<>();
        final var str = "abacbdecf";
        for (final char c : str.toCharArray()) {
            System.out.println(findOldestChar(c));
        }
    }

    private static char findOldestChar(final char c) {
        map.merge(c, 1, Integer::sum);
        deque.offer(c);
        while (!deque.isEmpty() && map.get(deque.peek()) > 1) deque.poll();
        return !deque.isEmpty() ? deque.peek() : ' ';
    }
}
