package net.greatsite.yeahbutstill.javathread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {

    final private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Long value = 0L;

    public void increment() {

        try {
            lock.writeLock().lock();
            value++;
        } finally {
            lock.writeLock().unlock();
        }

    }

    public Long getValue() {

        try {
            lock.readLock().lock();
            return value;
        } finally {
            lock.readLock().unlock();
        }


    }

}
