package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateStringPermutations {
    public static void main(String[] args) {
        final var str = "abc";
        var res = new ArrayList<String>();
        System.out.printf("Generating permutations of string \"%s\"\n", str);
        backtrack(str.toCharArray(), res, 0);
        System.out.printf("Total number of permutations: %d\n", res.size());
        res.forEach(System.out::println);
    }

    private static void backtrack(char[] charArr, List<String> list, int idx) {
        if (idx == charArr.length) {
            list.add(String.valueOf(charArr));
        } else {
            for (int i = idx; i < charArr.length; i++) {
                swap(charArr, i, idx);
                backtrack(charArr, list, idx + 1);
                swap(charArr, i, idx);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
