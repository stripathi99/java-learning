package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static void main(String[] args) {
        var wordList = List.of(
                // Multi-character anagrams
                "listen", "silent", "enlist", "inlets", "tinsel",
                "acts", "cats", "cast", "scat",
                "weird", "wired", "diewr",
                "teams", "meats", "steam", "tames",

                // Short anagrams
                "eat", "tea", "tan", "ate", "nat", "bat",
                "opt", "pot", "top",
                "yes", "sey",

                // Non-anagrams
                "hello", "world", "python", "java", "swift",
                "apple", "banana", "orange", "grape",

                // Single character strings
                "a", "b", "c", "d", "e",

                // Double character strings
                "aa", "bb", "cc", "dd", "ee",
                "ab", "ba", "cd", "dc",

                // Triple character strings
                "aaa", "bbb", "ccc", "ddd", "eee",
                "abc", "bac", "cab", "acb", "bca", "cba",

                // Quadruple character strings
                "aaaa", "bbbb", "cccc", "dddd", "eeee",
                "abcd", "bacd", "cabd", "dabc", "acbd", "bcad", "bdac", "cdab", "dcab", "adbc", "dbac",

                // Longer strings
                "tom marvolo riddle", "i am lord voldemort",
                "listen to the silent", "silent as a tinsel",

                // Repeated characters
                "aaaab", "baaaa", "bbbbb", "bbbbba",

                // Palindromes
                "madam", "dad", "mom", "radar",

                // Empty strings
                "", "", "",

                // Strings with numbers
                "a1b2c3", "3c2b1a",
                "hello123", "123olleh",

                // Strings with special characters
                "!@#$", "$#@!",
                "hello!", "!olleh",

                // Very long strings
                "abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba"
        );

        GroupAnagrams anagrams = new GroupAnagrams();
        var res = anagrams.groupAnagrams(wordList);
        System.out.println(res);
    }

    private List<List<String>> groupAnagrams(final List<String> words) {
        var map = new HashMap<String, List<String>>();
        for(var s: words) {
            var ch = s.toCharArray();
            Arrays.sort(ch);
            var key = String.valueOf(ch);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
