package DesignPatterns;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private final static int MAX_CAPACITY = 10_000;
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>(MAX_CAPACITY);

        var startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_CAPACITY; i++) {
            threads.add(Thread.ofVirtual().start(() -> {
                SingletonInstance singletonInstance = SingletonInstance.getInstance();
            }));
        }

        var endTime = System.currentTimeMillis();
        System.out.printf("it took %dms to execute.\n", endTime - startTime);
    }
}

class SingletonInstance {
    private static volatile SingletonInstance singletonInstance;

    private SingletonInstance() {}

    public static SingletonInstance getInstance() {
        if (singletonInstance == null) {
            synchronized (SingletonInstance.class) {
                if (singletonInstance == null) {
                    singletonInstance = new SingletonInstance();
                }
            }
        }

        return singletonInstance;
    }
}
