package com.example.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 测试邮戳锁
 * 邮戳锁将读锁细分为乐观锁和悲观锁
 * 邮戳的意思是该锁会返回一个版本号，通过乐观锁机制对比版本号来判断是否需要升级为悲观锁
 * 邮戳锁不可重入
 */
public class StampedLockTest {
    public static StampedLock lock = new StampedLock();
    public static Integer count = 0;

    public static void produce() {
        long stamp = lock.writeLock();
        System.out.println(Thread.currentThread().getName() + " get write lock");
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + " produced " + count);
        } finally {
            System.out.println(Thread.currentThread().getName() + " release write lock");
            lock.unlock(stamp);
        }
    }

    public static void consume() {
        long stamp = lock.tryOptimisticRead();
        int temp = count;
        System.out.println(Thread.currentThread().getName() + " get optimistic read lock with count: " + temp);

        if (!lock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + " get pessimistic read lock");
            stamp = lock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + " consumed " + count);
            } finally {
                System.out.println(Thread.currentThread().getName() + " release read lock");
                lock.unlock(stamp);
            }
        }
        else {
            System.out.println(Thread.currentThread().getName() + " consumed" + temp);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(() -> StampedLockTest.produce(), "PRODUCER-" + i);
            Thread consumer = new Thread(() -> StampedLockTest.consume(), "CONSUMER" + i);

            producer.start();
            TimeUnit.SECONDS.sleep(1);
            consumer.start();
        }
    }
}
