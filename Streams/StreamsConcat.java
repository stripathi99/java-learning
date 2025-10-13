package Streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamsConcat {
    public static void main(String[] args) {
        var list1 = List.of(1, 3, 5, 7, 9);
        var list2 = List.of(2, 4, 6, 8, 10);

        var res = Stream.concat(list1.stream(), list2.stream())
                .sorted()
                .toList();

        System.out.println(res);
    }
}
