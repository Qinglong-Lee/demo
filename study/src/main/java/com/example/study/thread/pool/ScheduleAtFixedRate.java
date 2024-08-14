package com.example.study.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试定时线程池
 * 一个线程只要空闲就可以执行不同的任务，并非一个线程始终绑定一个任务
 * 当任务被分配的线程被占用是，此任务可以会被分配到其他空闲的线程执行
 * 但是一个任务不会同时被多个线程并行执行
 * 也就是说并发量并不直接取决于线程数量，还取决于任务数量和任务执行时间
 * 如果就一个任务，再多线程也不会一起来执行这个任务
 * 定时是作用在任务上的，而不是线程上
 * 即时间到了，而且上次的执行已经结束，任务就需要被执行一次，于是就去找一个线程池中当前空闲的线程
 * 如果时间到了，上次任务未结束，则会等待任务结束，然后立即执行下次任务
 */
public class ScheduleAtFixedRate {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
                    System.out.println(Thread.currentThread().getName() + " task1");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                , 0L, 2L, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> {
                    System.out.println(Thread.currentThread().getName() + " task2");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                , 0L, 2L, TimeUnit.SECONDS);
    }
}
