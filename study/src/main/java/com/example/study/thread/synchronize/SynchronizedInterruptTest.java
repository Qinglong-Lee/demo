package com.example.study.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 测试 synchronized 的【线程等待锁的状态】能否被中断
 * lock12() 和 lock21() 同时执行将构成死锁
 * 尝试利用 Thread.interrupt() 中断某个线程使其释放锁，从而解除死锁
 * 结果：无法中断
 */
public class SynchronizedInterruptTest {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void lock12() {
        System.out.println(Thread.currentThread().getName() + " waiting for lock1...");
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " got lock1!");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + " waiting for lock2...");

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " got lock2!");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void lock21() {
        System.out.println(Thread.currentThread().getName() + " waiting for lock2...");

        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " got lock2!");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + " waiting for lock1...");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " got lock1!");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            SynchronizedInterruptTest.lock12();
        });

        Thread t2 = new Thread(() -> {
            SynchronizedInterruptTest.lock21();
        });

        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        synchronized 等待锁的状态无法被中断，此处无法解决死锁局面
//        要中断等待锁的状态，可以用 Reentrantlock.lockInterruptibly()
        if(t1.isAlive()) {
            t1.interrupt();
        }
    }
}
