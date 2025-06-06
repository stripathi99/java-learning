package Multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ThreadLimitTest {
    public static void main(String[] args) {
        var threadCount = new AtomicInteger(0);
        try {
            while(true) {
                var thread = new Thread(() -> {
                   threadCount.incrementAndGet();
                    LockSupport.park();
                });
                thread.start();
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Reached thread limit: " + threadCount);
            outOfMemoryError.printStackTrace();
        } finally {
            Runtime.getRuntime().gc();
            Runtime.getRuntime().exit(0);
        }
    }
}
