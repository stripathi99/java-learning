package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class Simple {
    private final static int RANGE = 2_000_000_000, STEP = 2_0000_000;

    // private final static int NUM_OF_THREADS =
    // Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        var startTime = System.currentTimeMillis();
        System.out.println(LongStream.range(1, RANGE + 1).sum());
        var endTime = System.currentTimeMillis();
        System.out.printf("it took %dms to execute.\n", endTime - startTime);

        List<Future<Long>> futures;
        //try (var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            futures = new ArrayList<>(RANGE / STEP);
            startTime = System.currentTimeMillis();

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

        endTime = System.currentTimeMillis();
        System.out.println(sum);
        System.out.printf("it took %dms to execute.\n", endTime - startTime);
    }
}
