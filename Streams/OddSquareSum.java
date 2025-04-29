package Streams;

import java.util.List;
import java.util.function.Predicate;

public class OddSquareSum {
    public static void main(String[] args) {
        var integerList = List.of(1, 2, 3, 4, 5);
        var sum = integerList.stream()
                .filter(oddCheck.negate())
                .map(i -> i*i)
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);
    }

    static Predicate<Integer> oddCheck = i -> (i % 2) == 0;
}
