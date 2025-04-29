package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GenerateStringPermutationWithDuplicates {
    public static void main(String[] args) {
        final var str = "aaabb";
        var set = new HashSet<String>();
        System.out.printf("Generating unique permutations of string \"%s\"\n", str);

        backtrack(str, new StringBuilder(), set, new boolean[str.length()], 0);

        var res = new ArrayList<>(set);
        System.out.printf("Total number of permutations: %d\n", res.size());
        res.forEach(System.out::println);
    }

    private static void backtrack(String str, StringBuilder sb,
                                  Set<String> set, boolean[] visited, int idx) {
        if (idx == str.length()) {
            set.add(sb.toString());
        }

        for (int i = 0; i < str.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(str.charAt(i));

                backtrack(str, sb, set, visited, idx + 1);

                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
