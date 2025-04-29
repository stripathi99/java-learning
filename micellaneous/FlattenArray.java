package micellaneous;

import java.util.Arrays;
import java.util.stream.Stream;

public class FlattenArray {
    public static void main(String[] args) {
        Object[] array = { 1, 2,
                new Object[] { 3, 4, new Object[] { 5 }, 6, 7 },
                8, 9, 10, null,
                new Object[] {null, 11, 12} };
        System.out.println(Arrays.toString(flatten(array)));
    }

    private static Integer[] flatten(Object[] objectArray) {
        return Arrays.stream(objectArray)
                .flatMap(FlattenArray::apply)
                .toArray(Integer[]::new);
    }

    private static Stream<? extends Integer> apply(Object obj) {
        return switch (obj) {
            case Integer i -> Stream.of(i);
            case Object[] objArr -> Arrays.stream(flatten(objArr));
            case null, default -> Stream.empty();
        };
    }

//    private static Integer[] flatten(Object[] array) {
//        var result = new ArrayList<Integer>();
//        flattenHelper(array, result);
//        return result.toArray(Integer[]::new);
//    }
//
//    private static void flattenHelper(Object[] array, ArrayList<Integer> result) {
//        for (Object o: array) {
//            if (o instanceof Integer i) result.add(i);
//            else if (o instanceof Object[] objArray) flattenHelper(objArray, result);
//        }
//    }

//    private static Integer[] flatten(final Object[] array) {
//        final var result = new ArrayList<Integer>();
//        for (Object o: array) {
//            if (o instanceof Object[] objArr) {
//                result.addAll(Arrays.asList(flatten(objArr)));
//            } else result.add((Integer) o);
//        }
//        return result.toArray(Integer[]::new);
//    }
}
