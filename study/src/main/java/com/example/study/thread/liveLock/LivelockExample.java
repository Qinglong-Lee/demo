package com.example.study.thread.liveLock;

import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static cn.hutool.core.thread.ThreadUtil.sleep;

public class LivelockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LivelockExample livelock = new LivelockExample();
        new Thread(livelock::operation1, "T1").start();
        new Thread(livelock::operation2, "T2").start();
    }

    @SneakyThrows
    public void operation1() {
        while (true) {
            lock1.tryLock(50, TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName() + ": lock1 acquired, trying to acquire lock2.");
            sleep(50);

            if (lock2.tryLock()) {
                System.out.println(Thread.currentThread().getName() + ": lock2 acquired.");
            } else {
                System.out.println(Thread.currentThread().getName() + ": cannot acquire lock2, releasing lock1.");
                lock1.unlock();
//                sleep(RandomUtil.randomInt(10,100));
                continue;
            }

            System.out.println(Thread.currentThread().getName() + ": executing first operation.");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

    @SneakyThrows
    public void operation2() {
        while (true) {
            lock2.tryLock(50, TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName() + ": lock2 acquired, trying to acquire lock1.");
            sleep(50);

            if (lock1.tryLock()) {
                System.out.println(Thread.currentThread().getName() + ": lock1 acquired.");
            } else {
                System.out.println(Thread.currentThread().getName() + ": cannot acquire lock1, releasing lock2.");
                lock2.unlock();
//                sleep(RandomUtil.randomInt(10,100));
                continue;
            }

            System.out.println(Thread.currentThread().getName() + ": executing second operation.");
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    // helper methods

}