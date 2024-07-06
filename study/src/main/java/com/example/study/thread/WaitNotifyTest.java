package com.example.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Object lock = new Object();
        AtomicInteger runningSubs = new AtomicInteger(3);
        var obj = new Object() {
            public void setA(int a) {
                this.a = a;
            }

            int a = 1;
        };

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                obj.setA(2);

                System.out.println(Thread.currentThread().getName() + " done!");
                if(runningSubs.addAndGet(-1) == 0) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            });

            t.start();
        }

//        TimeUnit.SECONDS.sleep(5);

        synchronized (lock) {
            while (runningSubs.get() > 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("main done!");
    }
}
