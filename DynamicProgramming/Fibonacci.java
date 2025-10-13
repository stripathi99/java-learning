package DynamicProgramming;

public class Fibonacci {
    public static void main(String[] args) throws InterruptedException {
        final int fib = 50;
        Thread t1 = Thread.ofPlatform().start(() -> {
            var start = System.currentTimeMillis();
            System.out.printf("\nBF - fib(%d) = %d\n", fib, calculateFibonacci(fib));
            var end = System.currentTimeMillis();
            System.out.printf("It took %dms to compute.\n", end - start);
        });

        Thread t2 = Thread.ofPlatform().start(() -> {
            var start = System.currentTimeMillis();
            System.out.printf("\nDP - fib(%d) = %d\n", fib, calculateFibonacciBottomsUp(fib));
            var end = System.currentTimeMillis();
            System.out.printf("It took %dms to compute.\n", end - start);
        });

        Thread t3 = Thread.ofPlatform().start(() -> {
            var start = System.currentTimeMillis();
            System.out.printf("\nOP - fib(%d) = %d\n", fib, calculateFibonacci_Optimized(fib));
            var end = System.currentTimeMillis();
            System.out.printf("It took %dms to compute.\n", end - start);
        });

        t1.join();
        t2.join();
        t3.join();
    }

    private static long calculateFibonacci(final int num) {
        if (num < 2) return num;
        return calculateFibonacci(num - 1) + calculateFibonacci(num - 2);
    }

    private static long calculateFibonacciBottomsUp(final int num) {
        var memo = new long[num];

        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i < num; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

       return memo[num - 1];
    }

    private static long calculateFibonacci_Optimized(final int num) {
        long first = 1, second = 1;
        for(int i = 2; i < num; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
