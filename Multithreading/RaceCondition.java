package Multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class RaceCondition {
    private final static int NUM = 10_000;
    private final static int[] counter = {0};

    public static void main(String[] args) throws InterruptedException {
        final var lock = new ReentrantLock();

        var t1 = Thread.ofPlatform().unstarted(() -> setCounter(1, lock));
        var t2 = Thread.ofPlatform().unstarted(() -> setCounter(-1, lock));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.printf("Counter = %d", counter[0]);
    }

    private static void setCounter(final int delta, ReentrantLock lock) {
        lock.lock();
        try {
            for (int i = 0; i < NUM; i++) counter[0] += delta;
        } finally {
            lock.unlock();
        }
    }
}
