package micellaneous;

import java.util.List;

public class GenericWildcard {
    public static void main(String[] args) {
        var listOfInt = List.of(1, 2, 3, 4);
        var listOfFloat = List.of(1.2, 2.3, 5.4);

        display(listOfInt);
        display(listOfFloat);
    }

    private static void display(final List<? extends Number> list) {
        list.forEach(System.out::println);
    }
}
