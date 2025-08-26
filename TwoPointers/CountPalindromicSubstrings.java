package TwoPointers;

import java.util.List;

public class CountPalindromicSubstrings {
    public static void main(String[] args) {
        final var list = List.of(
                "noon", "racecar", "madam",
                "level", "apple", "bob");

        for (var str: list) {
            System.out.printf("Count of palindromic substrings in \"%s\" is %d\n", str, countPalindromicSubstrings(str));
        }
    }

    private static int countPalindromicSubstrings(String str) {
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {
                String sub = str.substring(i, j);
                if(sub.length() == 1) count++;
                else {
                    System.out.println(sub);
                    // substring is of size 2 or more
                    if(isPalindrome(sub)) count++;
                }
            }
        }
        return count;
    }

    private static boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        while(start < end) {
            if(str.charAt(start++) != str.charAt(end--)) return false;
        }
        return true;
    }
}
