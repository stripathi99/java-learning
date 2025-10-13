package Streams;

import java.util.Map;
import java.util.stream.Collectors;

public class MapPartitioning {
    public static void main(String[] args) {
        var map = Map.of(
                "Ram", 281_000,
                "Pam", 372_000,
                "Sam", 190_000,
                "Mat", 463_000,
                "Tim", 554_000,
                "Pet", 645_000,
                "Gus", 736_000,
                "Agt", 1_018_000,
                "Tes", 927_000,
                "Est", 1_018_000
        );

        var res1 = map.entrySet().stream()
                .collect(
                        Collectors
                                .partitioningBy(e -> e.getValue() > 500_000)
                );

        System.out.println(res1);

        var res2 = map.entrySet().stream()
                .collect(
                        Collectors
                                .groupingBy(e -> e.getValue() > 500_000 ? "High" : "Low")
                );

        System.out.println(res2);
    }
}
