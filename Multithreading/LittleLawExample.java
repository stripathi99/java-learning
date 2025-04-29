package Multithreading;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class LittleLawExample {

    private static final int NUM_OF_TASKS = 10_000;
    private static final int AVG_RESPONSE_TIME = 500; // in milliseconds

    public static void main(String[] args) {
        // simulate I/O bound work
        Runnable ioBoundTask = () -> {
            try {
                Thread.sleep(Duration.ofMillis(AVG_RESPONSE_TIME));
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        };

        benchmark("Virtual_Threads", Executors.newVirtualThreadPerTaskExecutor(), ioBoundTask);
        benchmark("Fixed_1000", Executors.newFixedThreadPool(1000), ioBoundTask);
        benchmark("Fixed_4000", Executors.newFixedThreadPool(4000), ioBoundTask);
    }

    private static void benchmark(String type, ExecutorService executorService, Runnable task) {
        Instant start, end;
        AtomicLong completedTasks = new AtomicLong();
        start = Instant.now();
        try (executorService) {
            IntStream.range(0, NUM_OF_TASKS).forEach(i -> executorService.submit(() -> {
                task.run();
                completedTasks.incrementAndGet();
            }));
        }
        end = Instant.now();
        var duration = Duration.between(start, end).toMillis();
        var throughput = completedTasks.get() / duration * 1000; // tasks per second
        System.out.printf("\n\t%s - Time: %dms. Throughput: %d/s", type, duration, throughput);
    }
}
