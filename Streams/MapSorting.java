package Streams;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorting {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Ram", 281_000);
        map.put("Pam", 372_000);
        map.put("Sam", 190_000);
        map.put("Mat", 463_000);
        map.put("Tim", 554_000);
        map.put("Pet", 645_000);
        map.put("Get", 736_000);
        map.put("Agt", 1_018_000);
        map.put("Tes", 927_000);
        map.put("Est", 1_018_000);
        map.put("Dex", 209_000);

        var sortedMap = map.entrySet()
                .stream()
                .sorted(
                        Comparator
                                .comparingInt(Map.Entry<String, Integer>::getValue).reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1, // collision resolution
                                LinkedHashMap::new
                        )
                );

        sortedMap.forEach((key, value) -> System.out.printf("%s : %d\n", key, value));
    }
}