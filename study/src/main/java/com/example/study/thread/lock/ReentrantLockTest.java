package com.example.study.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends Thread {
    public static int a = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public void calculate() {
        lock.lock();
        try {
            a += 5;
            sub();
        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        lock.lock();
        try {
            a -= 2;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
//        循环次数不能太小，否则看不出效果
        for (int i = 0; i < 1000; i++) {
            calculate();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest thread1 = new ReentrantLockTest();
        ReentrantLockTest thread2 = new ReentrantLockTest();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(ReentrantLockTest.a);
    }
}
