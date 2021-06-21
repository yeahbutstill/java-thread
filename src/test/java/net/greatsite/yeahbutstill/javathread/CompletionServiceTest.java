package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    private final Random random = new Random();

    @Test
    void test() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(20);
        var completionService = new ExecutorCompletionService<String>(executor);

        // Submit task
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final var index = i;
                completionService.submit(() -> {
                    Thread.sleep(random.nextInt(2000));
                    return "Task-" + index;
                });
            }
        });

        // Poll task
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                    if (future == null) {
                        break;
                    } else {
                        System.out.println(future.get());
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
