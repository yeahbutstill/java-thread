package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

class ThreadTest {

    @Test
    void testMainThread() {

        var name = Thread.currentThread().getName();
        System.out.println(name);

    }

    @Test
    void createThread() {

        Runnable runnable = () -> System.out.println("Hello from Thread : " + Thread.currentThread().getName());

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesai");

    }

    @Test
    void threadSleep() {

        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesai");

        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void threadJoin() {

        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu Selesai");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program Selesai");

    }

    @Test
    void threadInterrupt() {

        Runnable runnable = () -> {

            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }

        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu Selesai");
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program Selesai");

    }

    @Test
    void threadInterruptManual() {

        Runnable runnable = () -> {

            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("Runnable : " + i);
            }

        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu Selesai");
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program Selesai");

    }

    @Test
    void threadName() {

        var thread = new Thread(() -> {
            System.out.println("Runnable in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Product");
        thread.start();

    }

    @Test
    void threadState() {

        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Product");
        System.out.println(thread.getState());

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());

    }
}
