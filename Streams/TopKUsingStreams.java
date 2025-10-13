package Streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKUsingStreams {
    private final static List<String> list = List.of(
            "apple", "banana", "apple", "orange", "banana",
            "apple", "grape", "banana", "kiwi", "orange",
            "apple", "banana", "melon", "grape", "apple",
            "banana", "pear", "orange", "banana", "peach",
            "apple", "banana", "orange", "plum", "apple"
    );

    public static void main(String[] args) {
        int k = 3;
        var res = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(
                        Comparator
                                .comparingLong(Map.Entry<String, Long>::getValue)
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                //.map(Map.Entry::getKey)
                .limit(k)
                .toList();

        // O(nlogn)
        System.out.println(res);

        topKUsingMinHeap(k);
    }

    // O(nlogk) solution using min-heap
    private static void topKUsingMinHeap(int k) {
        Map<String, Integer> map = new HashMap<>();
        list.forEach(s -> map.put(s, map.computeIfAbsent(s, x -> 0) + 1));
        var minHeap = new PriorityQueue<String>(Comparator.comparingInt(map::get));

        map.keySet().forEach(entry -> {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        });

        var res = new ArrayList<>(minHeap).reversed();
        System.out.println(res);
    }
}
