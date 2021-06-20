package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void testFuture() throws InterruptedException, ExecutionException {

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };
        Future<String> future = executor.submit(callable);
        System.out.println("Selesai Future");

        while (!future.isDone()) {
            System.out.println("Waiting future");
            Thread.sleep(1000);
        }

        String value = future.get();
        System.out.println(value);

    }

    @Test
    void testFutureCancle() throws ExecutionException, InterruptedException {

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai Future");

        Thread.sleep(2000);
        future.cancel(true);

        String value = future.get();
        System.out.println(value);

    }

    @Test
    void invokeAll() throws ExecutionException, InterruptedException {

        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callable = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
            Thread.sleep(operand * 500L);
            return String.valueOf(operand);
        }).collect(Collectors.toList());

        List<Future<String>> futures = executor.invokeAll(callable);
        for (Future<String> stringFuture : futures) {
            System.out.println(stringFuture.get());
        }

    }

    @Test
    void invokeAny() throws ExecutionException, InterruptedException {

        var executor = Executors.newFixedThreadPool(20);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
        }).collect(Collectors.toList());

        var futures = executor.invokeAny(callables);
        System.out.println(futures);

    }
}
