package com.example.study.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " started");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " ended");
                }
            });

            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " ended");
    }
}
