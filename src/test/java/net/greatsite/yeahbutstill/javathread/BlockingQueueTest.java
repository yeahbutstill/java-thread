package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    @Test
    void testArrayBlockingQueue() throws InterruptedException {

        var queue = new ArrayBlockingQueue<String>(5);
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            executor.execute(() -> {

                try {

                    queue.put("Data");
                    System.out.println("Success Put Data");

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            });

        }

        executor.execute(() -> {

            while (true) {

                try {

                    Thread.sleep(2000L);
                    String data = queue.take();
                    System.out.println("Receive : " + data);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void testLinkedBlockingQueue() throws InterruptedException {

        final var queue = new LinkedBlockingQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            executor.execute(() -> {

                try {

                    queue.put("Data");
                    System.out.println("Finish Put Data");

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            });

        }

        executor.execute(() -> {

            while (true) {

                try {

                    Thread.sleep(2000L);
                    var value = queue.take();
                    System.out.println("Receive data : " + value);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
