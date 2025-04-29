package String;

import java.util.List;

public class EncodeDecode {
    public static void main(String[] args) {
        List.of("aa", "aaabb", "", " qqqwwb bbbmmmm ")
            .stream()
            .map(String::trim)
            .forEach(EncodeDecode::printEncodeDecode);
        printEncodeDecode(null);
    }

    private static void printEncodeDecode(String str) {
        System.out.println("original : " + str);
        try {
            String encodedString = encode(str);
            String decodedString = (encodedString.equals(str)) ? str : decode(encodedString);
            System.out.println("encoded  : " + encodedString);
            System.out.println("decoded  : " + decodedString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String encode(String str) throws RuntimeException {
        if (str == null || str.isEmpty())
            throw new RuntimeException("no string to encode");

        StringBuilder sb = new StringBuilder();
        int counter = 1, n = str.length();

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            while (i < n - 1 && c == str.charAt(i + 1)) {
                counter++;
                i++;
            }

            sb.append(c);
            sb.append(counter);
            counter = 1;
        }

        return sb.length() < n ? sb.toString() : str;
    }

    private static String decode(String str) throws RuntimeException {
        if (str == null || str.isEmpty())
            throw new RuntimeException("no string to decode");

        StringBuilder sb = new StringBuilder();
        int n = str.length();

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c) && i != 0) {
                int counter = str.charAt(i) - '0';
                while (counter > 0 && !Character.isDigit(str.charAt(i - 1))) {
                    sb.append(str.charAt(i - 1));
                    counter--;
                }
            }
        }

        return sb.length() == 0 ? str : sb.toString();
    }
}