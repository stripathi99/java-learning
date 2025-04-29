package Streams;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class MapVMapToInt {
    private final static int SIZE = 420_000;
    private static Integer[] nums;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        nums = new Integer[SIZE];
        var rand = new Random(42);
        Arrays.fill(nums, rand.nextInt(1, SIZE));
        System.out.println("\n usingMap: \n");
        test("usingMap");
        System.out.println("\n usingMapInt: \n");
        test("usingMapInt");
    }

    private static void test(final String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MapVMapToInt.class.getDeclaredMethod(methodName);
        for(int i = 1; i <= 3; i++) {
            var startTime = System.currentTimeMillis();
            System.out.printf("Sum: %d", method.invoke(null));
            var endTime = System.currentTimeMillis();
            System.out.printf(". It took %d ms.\n", endTime - startTime);
        }
    }

    private static int usingMapInt() {
        return Arrays.stream(nums).mapToInt(i -> i).sum();
    }

    private static int usingMap() {
        return Arrays.stream(nums).reduce(0, Integer::sum);
    }
}
