package micellaneous;

public class SecondHighest {
    public static void main(String[] args) {
        var arr = new int[] {1, 3, 5, 7, 9, 0, 2, 4, 6, 8, 9, 11};
        System.out.println(getSecondHighest(arr));
    }

    private static int getSecondHighest(final int[] arr) {
        if (arr.length < 2) throw new IllegalArgumentException("input array should be of length >= 2");
        int first = arr[0], second = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > first) {
                second = first;
                first = arr[i];
            } else if (arr[i] > second && arr[i] != first) {
                second = arr[i];
            }
        }
        return second;
    }
}
