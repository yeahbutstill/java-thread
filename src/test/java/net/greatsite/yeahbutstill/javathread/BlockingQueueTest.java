package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

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

    @Test
    void testPriorityBlockingQueue() throws InterruptedException {
        final var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            var index = i;
            executor.execute(() -> {
               queue.put(index);
               System.out.println("Success Put Data : " + index);
            });

        }

        executor.execute(() ->  {
           while (true) {
               try {
                   Thread.sleep(2000);
                   var value = queue.take();
                   System.out.println("Receive data : " + value);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testDelayQueue() throws InterruptedException {
        final var queue = new DelayQueue<ScheduledFuture<String>>();
        final var executor = Executors.newFixedThreadPool(20);
        final var executorScheduled = Executors.newScheduledThreadPool(10);

        for (int i = 0; i <= 10; i++) {
            final var index = i;
            queue.put(executorScheduled.schedule(() -> "Delayed " + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true) {
                try {
                    var data = queue.take();
                    System.out.println("Receive data : " + data.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
