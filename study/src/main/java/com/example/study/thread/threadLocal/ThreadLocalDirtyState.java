package com.example.study.thread.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试 ThreadLocal 在线程池场景下的【脏数据】污染
 * 线程池只给一个线程，分配两个任务，意味着一个线程需要分两次运行两个任务
 * 在第一个任务重给 ThreadLocal 设置了一个状态，退出时如果没有 remove
 * 则此线程在执行第二个任务时会携带此状态，第二个任务会获取到第一个任务的【脏数据】
 */
public class ThreadLocalDirtyState {
    public static void main(String[] args) throws InterruptedException {
//        ThreadLocal.withInitial 用于定义一个自动初始化的 SuppliedThreadLocal
//        这样即使不先 set，调用 get 也会利用传入的 supplier 去初始化一个 ThreadLocalMap
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "HAHAHA");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            threadLocal.set("HAI, BOY");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
//            如果不 remove，则此任务在当前线程的状态会被带到下一个任务中
//            threadLocal.remove();
        });

        TimeUnit.SECONDS.sleep(1);

        executorService.execute(() -> {
//            如果此任务为重新赋值，上一任务又没有remove，则会获取到上一任务的【脏数据】
//            threadLocal.set("HAI, GIRL");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        });
    }
}
