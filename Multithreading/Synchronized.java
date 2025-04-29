package Multithreading;

public class Synchronized {
    public static void main(String[] args) throws InterruptedException {
        Test t1 = new Test();
        Test t2 = new Test();

        Thread thread1 = new Thread(() -> t1.test());
        Thread thread2 = new Thread(() -> t2.test());

        thread1.setName("thread-1");
        thread2.setName("thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}


class Test {
    public static synchronized void test() {
        try {
            System.out.println(Thread.currentThread().getName() + " going to sleep.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " just woke up.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}