package Streams;

import java.util.Comparator;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {
        List<String> students = List.of("Ram", "Sam", "Shyam", "Shubham", "Arpita", "Rita", "Ankita");
        List<String> res = students.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder())
                ).toList();
        System.out.println(res);
    }
}
