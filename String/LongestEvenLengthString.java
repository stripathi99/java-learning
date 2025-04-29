package String;

public class LongestEvenLengthString {
    private static final String str = """
            Be not afraid of greatness some are born great some achieve greatness and some have greatness thrust upon them
            """;

    public static void main(String[] args) {
        var res = getLongestEvenLengthString(str);
        System.out.printf("\n\'%s\' is the longest even length string with length of %d.\n", res.str, res.len);
    }

    private static LongestEvenLengthStringRecord getLongestEvenLengthString(final String str) {
        String result = null;
        int maxLength = -1;
        for(String s: str.split(" ")) {
            int length = s.length();
            if (length % 2 == 0 && length > maxLength) {
                maxLength = length;
                result = s.intern();
            }
        }
        return new LongestEvenLengthStringRecord(result, maxLength);
    }

    private record LongestEvenLengthStringRecord(String str, int len) {}
}
