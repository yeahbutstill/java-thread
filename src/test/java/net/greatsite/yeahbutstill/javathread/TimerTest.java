package net.greatsite.yeahbutstill.javathread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    void delayedJob() throws InterruptedException {

        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Transaksi Berhasil");
            }
        };

        // asychronos delay milliseconds
        var timer = new Timer();
        timer.schedule(task, 2000);

        Thread.sleep(3000);

    }

    @Test
    void periodicJob() throws InterruptedException {

        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Transaksi Berhasil");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 2000, 2000);

        Thread.sleep(10_000L);

    }

}
