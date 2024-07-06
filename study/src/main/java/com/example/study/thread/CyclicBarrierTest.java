package com.example.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
//        可提供一个回调函数用于每次释放线程之后的操作
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("All sub thread finished!");
        });

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

                    try {
                        System.out.println(Thread.currentThread().getName() + " ended");

                        barrier.await();
                        System.out.println("All subs released, can do something else!");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            t.start();
        }

//        要在 main 线程等待所有子线程，main 线程也得被拦住
//        当所有子线程结束，被阻塞的 main 线程 + 子线程数刚好等于 barrier 设置的数量
//        此时所有线程被释放，即 main 线程得以继续运行
//        如果不需要在 main 线程中等待，而是每个子线程等待其他子线程结束，然后 do something else
//        则无需在 main 线程阻塞，直接在子线程 barrier.await() 后 do something else
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " ended");
    }
}
