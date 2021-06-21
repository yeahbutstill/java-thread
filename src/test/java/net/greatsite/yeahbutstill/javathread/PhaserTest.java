package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    void testCountDownLatch() throws InterruptedException {

        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(20);

        phaser.bulkRegister(5);
        phaser.bulkRegister(5);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Start Task");
                    Thread.sleep(2000);
                    System.out.println("End Task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("All Task Finished");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void testCyclicBarrier() throws InterruptedException {

        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(20);

        phaser.bulkRegister(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("WAIT");
                phaser.arriveAndAwaitAdvance();
                System.out.println("DDNE");
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
