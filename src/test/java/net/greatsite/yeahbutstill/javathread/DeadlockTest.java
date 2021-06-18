package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

public class DeadlockTest {

    @Test
    void transfer() throws InterruptedException {

        var blue = new Balance(10_000_000L);
        var gold = new Balance(10_000_000L);
        var platinum = new Balance(10_000_000L);
        var xpresi = new Balance(7_000_000L);
        var tapres = new Balance(10_000_000L);
        var dollar = new Balance(10_000_000L);

        var thread1 = new Thread(() -> {
            try {
                Balance.transfer(blue, gold, 15_000_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var thread2 = new Thread(() -> {
            try {
                Balance.transfer(gold, platinum, 20_000_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var thread3 = new Thread(() -> {
            try {
                Balance.transfer(xpresi, tapres, 10_000_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var thread4 = new Thread(() -> {
            try {
                Balance.transfer(dollar, platinum, 25_000_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var thread5 = new Thread(() -> {
            try {
                Balance.transfer(tapres, gold, 20_000_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        thread5.start();

        System.out.println("Balance 1 : " + blue.getValue());
        System.out.println("Balance 2 : " + gold.getValue());
        System.out.println("Balance 3 : " + platinum.getValue());
        System.out.println("Balance 4 : " + xpresi.getValue());
        System.out.println("Balance 5 : " + tapres.getValue());
        System.out.println("Balance 6 : " + dollar.getValue());

    }
}
