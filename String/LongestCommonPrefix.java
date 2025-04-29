package String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().freeMemory());
        List<String> stringList = new ArrayList<>();
        stringList.add("club");
        stringList.add("clap");
        stringList.add("clove");

        System.out.println(getLongestCommonPrefix(stringList));
        System.out.println(getLongestCommonPrefixUsingSorting(stringList));
        //Runtime.getRuntime().gc();
        //System.out.println(Runtime.getRuntime().freeMemory());
    }

    private static String getLongestCommonPrefixUsingSorting(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        char[] first = list.get(0).toCharArray(), last = list.get(list.size() - 1).toCharArray();
        for(int i = 0; i < first.length; i++) {
            if(first[i] == last[i]) sb.append(first[i]);
            else break;
        }
        return sb.toString();
    }

    private static String getLongestCommonPrefix(List<String> list) {
        String res = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            res = getCommonPrefix(res, list.get(i));
        }
        return res;
    }

    private static String getCommonPrefix(String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < minLength; i++) {
            if(s1.charAt(i) == s2.charAt(i)) sb.append(s1.charAt(i));
            else break;
        }
        return sb.toString();
    }
}
