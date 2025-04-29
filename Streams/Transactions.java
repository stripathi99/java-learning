package Streams;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Transactions {
    record Item(Integer id) {
    }

    record Transaction(List<Item> items) {
    }

    public static void main(String[] args) {
        var itemList = List.of(
                new Item(123),
                new Item(223),
                new Item(323),
                new Item(123),
                new Item(423),
                new Item(123)
        );

        var t1 = new Transaction(itemList);
        var t2 = new Transaction(Collections.singletonList(new Item(123)));

        var transactionList = List.of(t1, t2,
                new Transaction(null),
                new Transaction(Collections.singletonList(new Item(null))));

        // find count of items with id=123 from transactionList
        var count = transactionList.stream()
                .flatMap(t -> t.items().stream())
                //.map(Transaction::items)
                .filter(Objects::nonNull)
                .mapToInt(Item::id)
                .filter(i -> i == 123)
                //.flatMap(List::stream).peek(System.out::println)
                //.filter(i -> Objects.nonNull(i.id()) && i.id() == 123)
                .count();

        System.out.println(count == 4);
    }
}


