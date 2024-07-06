package com.example.study.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 测试
 * Semaphore 用于限制某个当前代码能够被多少个线程同时访问
 * 下例定义了许可数为 3，即最多 3 个线程同时访问 semaphore.acquire() 后面的代码
 * 启动了 6 个线程，则意味着刚开始有 3 个线程同时访问，之后当某个线程释放了 semaphore，则其他线程才能接手
 * 任意时刻最多 3 个线程
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting for semaphore...");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " get semaphore!");
                    TimeUnit.MICROSECONDS.sleep(new Random().nextLong(500, 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(Thread.currentThread().getName() + " release semaphore!");
//                    release(int) 可以用于动态增加许可数，默认是 1，即归还原有许可，许可数不变
//                    即初始许可数为 3，如果这里 release(2)，则可用许可就变成了 4，就允许最多 4 个线程了
                    semaphore.release();
                }
            }).start();
        }

    }
}
