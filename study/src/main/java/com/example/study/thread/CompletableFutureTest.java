package com.example.study.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": started");

        CompletableFuture<Integer>[] cfs = new CompletableFuture[3];
        for (int i = 0; i < 3; i++) {
            CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + ": started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": ended");
                return 1;
            }, Executors.newFixedThreadPool(4));
            cfs[i] = cf;
        }

        CompletableFuture<Void> res =  CompletableFuture.allOf(cfs);
        res.thenAccept((reslut) -> System.out.println(Thread.currentThread().getName() + ": all ended"));

        System.out.println(Thread.currentThread().getName() + ": ended");
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        // 但是如果是自定义线程池就不会关闭
        // 主线程不会等待子线程
//        Thread.sleep(5000);
    }
}

