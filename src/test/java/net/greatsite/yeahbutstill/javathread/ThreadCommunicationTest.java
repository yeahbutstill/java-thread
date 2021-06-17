package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void manual() throws InterruptedException {

        var thread1 = new Thread(() -> {
            while (message == null) {
                System.out.println("Transaksi Dibatalkan, Waktu Habis");
            }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> {
            message = "Berhasil Transfer";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();

    }

    @Test
    void waitNotify() throws InterruptedException {

        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Transafer Berhasil";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    @Test
    void waitNotifyAll() throws InterruptedException {

        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread3 = new Thread(() -> {
            synchronized (lock) {
                message = "Transafer Berhasil";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }

}
