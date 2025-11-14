package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class Simple {
    private final static int RANGE = 2_000_000_000, STEP = 2_0000_000;

    public static void main(String[] args) {
        var startTime = System.nanoTime();
        System.out.println(LongStream.range(1, RANGE + 1).sum());
        System.out.printf("it took %d ms to execute.\n", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));

        List<Future<Long>> futures;
        //try (var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            futures = new ArrayList<>(RANGE / STEP);
            startTime = System.nanoTime();

            for (int i = 0; i <= RANGE - STEP; i += STEP) {
                int start = i + 1, end = i + STEP;
                futures.add(executorService.submit(() -> {
                    return LongStream.range(start, end + 1).sum(); // returns a callback
                }));
            }

            executorService.shutdown(); // kinda works like joining all the threads
        }

        long sum = futures.stream().mapToLong(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return 0; // return 0 if error getting value from future
            }
        }).sum();

        System.out.println(sum);
        System.out.printf("it took %d ms to execute.\n", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
    }
}
