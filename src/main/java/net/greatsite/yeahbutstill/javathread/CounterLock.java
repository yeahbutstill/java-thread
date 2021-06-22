package net.greatsite.yeahbutstill.javathread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    final private Lock lock = new ReentrantLock();
    private Long value = 0L;

    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }

    public Long getValue() {
        return value;
    }

}
