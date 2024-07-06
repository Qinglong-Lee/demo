package com.example.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过 Reentrantlock.tryLock() 避免死锁
 * 此方法会尝试获取锁，如果锁被其他线程占有不会无限等待，而是可以指定直接放弃获指定等待时间
 * 达到避免死锁的目的
 */
public class TryLockTest {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static void lock12 () {
        try {
            if(lock1.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " got lock1!");
                TimeUnit.SECONDS.sleep(1);

//                此处如果 lock2 被线程 2 占有，则等待 1s，这样当线程 2 因尝试获取 lock1 无果，就会放弃进而释放 lock2
//                接下来当前线程就可以获取到 lock2了
//                在等待过程中也是可以被中断的
                if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " got lock2!");
                    TimeUnit.SECONDS.sleep(1);
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " didn't get lock2!");
                }
            }
            else {
                System.out.println(Thread.currentThread().getName() + " didn't get lock1!");
            }
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }

    public static void lock21 () {
        try {
            if(lock2.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " got lock2!");
                TimeUnit.SECONDS.sleep(1);

                if (lock1.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " got lock1!");
                    TimeUnit.SECONDS.sleep(1);
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " didn't get lock1!");
                }
            }
            else {
                System.out.println(Thread.currentThread().getName() + " didn't get lock2!");
            }
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            TryLockTest.lock12();
        });
        Thread t2 = new Thread(() -> {
            TryLockTest.lock21();
        });

        t1.start();
        t2.start();
    }
}
