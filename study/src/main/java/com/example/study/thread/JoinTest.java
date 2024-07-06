package com.example.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started");

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " started");

                    try {
                        TimeUnit.SECONDS.sleep(2);
//                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + " ended");
                }
            });
            threads.add(t);
            t.start();

        }

//        TimeUnit.SECONDS.sleep(5);

        threads.stream().forEach(t -> {
            try {
                t.join();
//                join()方法本质和下面这段代码是一个意思
//                即用子线程对象作为锁，调用wait()方法等待子线程
//                之所以用子线程对象作为锁，是因为子线程结束后会自动唤醒所有等待子线程锁的其他线程
//                这样就无需手动唤醒了，这也是为什么join()方法能自动唤醒的原因
//                synchronized (t) {
//                    先判断子线程是否还存活，如果子线程已经结束，主线程还去等待，则会无限阻塞
//                    while (t.isAlive()) {
//                        t.wait(0);
//                    }
//                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(Thread.currentThread().getName() + " ended");

    }
}
