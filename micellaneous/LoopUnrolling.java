package micellaneous;

import java.util.Random;

public class LoopUnrolling {

    private static final int COUNT = 500_000_000;
    private static final int[] randArray = new int[COUNT];
    private static final Random rand = new Random(42);

    public static void main(String[] args) {
        init();
        for (int i = 0; i < 5; i++) {
            calculateSumUsingLoopUnrolling();
        }
    }

    private static void calculateSumUsingLoopUnrolling() {
        int i; long start, end, duration, sum = 0L, currSum;
        start = System.nanoTime();
        for(i = 0; i < COUNT; i += 5) {
            currSum = randArray[i]
                    + randArray[i + 1]
                    + randArray[i + 2]
                    + randArray[i + 3]
                    + randArray[i + 4];
            sum += currSum;
        }
        end = System.nanoTime();
        duration = (end - start) / 1_000_000;
        System.out.printf("Sum: %d. It took %dms.\n", sum, duration);
    }

    private static void init() {
        int nextInt;
        for (int i = 0; i < COUNT; i += 5) {
            nextInt = rand.nextInt(1, 100);
            randArray[i] = nextInt;
            randArray[i + 1] = nextInt + 1;
            randArray[i + 2] = nextInt + 2;
            randArray[i + 3] = nextInt + 3;
            randArray[i + 4] = nextInt + 4;
        }
    }
}
