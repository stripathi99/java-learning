package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        System.out.println(groupAnagrams(wordList));
        System.out.println(groupAnagramsUsingStreams(wordList));
    }

    private static List<List<String>> groupAnagrams(final List<String> words) {
        var map = new HashMap<String, List<String>>();
        for(var s: words) {
            if (s.isEmpty()) continue;
            map.computeIfAbsent(wordSortUtil(s), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private static List<List<String>> groupAnagramsUsingStreams(final List<String> words) {
        return words.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.groupingBy(GroupAnagrams::wordSortUtil))
//                .collect(
//                        Collectors.toMap(
//                                GroupAnagrams::wordSortUtil,
//                                word -> new ArrayList<String>(),
//                                (list1, list2) -> {
//                                    list1.addAll(list2);
//                                    return list1;
//                                }
//                        )
//                )
                .values().stream()
                .toList();
    }

    private static String wordSortUtil(final String word) {
        char[] ch = word.strip().toCharArray();
        Arrays.sort(ch);
        return String.valueOf(ch);
    }
}
