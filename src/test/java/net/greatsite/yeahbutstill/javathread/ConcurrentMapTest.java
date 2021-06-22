package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {

    @Test
    void testConcurrentMap() throws InterruptedException {

        final var countDown = new CountDownLatch(100);
        final var map = new ConcurrentHashMap<Integer, String>();
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            final var key = i;
            executor.execute(() -> {
               try {
                   Thread.sleep(2000);
                   map.putIfAbsent(key, "Dataa : " + key);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   countDown.countDown();
               }
            });
        }

        executor.execute(() -> {
            try {
                countDown.await();
                map.forEach((integer, s) -> System.out.println(integer + " : " + s));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testCollection() {
        List<String> list = List.of("Jeruk", "Mangga", "Apel", "Pisang");
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
