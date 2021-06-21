package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    void test() throws InterruptedException {

        // Run per 20 pekerjaan yang dieksekusi
        final var semaphore = new Semaphore(20);
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
               try {
                   // mengacquire menunggu 1000 thread sampai semaphorenya release
                   semaphore.acquire();
                   Thread.sleep(1000);
                   System.out.println("Finish");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   semaphore.release();
               }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
