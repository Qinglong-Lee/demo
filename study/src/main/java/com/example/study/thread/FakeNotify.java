package com.example.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试虚假唤醒
 * 三个线程交替打印线程名
 * 如果【while (!TURN.equals("1"))】换成【if】
 * 将导致顺序错乱，最终某个线程被永久阻塞，因为顺序错乱会导致另外两个线程先执行完毕，剩下一个线程无法被唤醒
 * 因为某个线程打印完成 signalAll 唤醒另外两个线程
 * 两个线程共同竞争锁，无法确定哪个竞争到
 * 如果竞争到锁的线程却不是它的轮次，依然会打印，并且唤醒下一个线程，导致顺序错乱
 * 因为用的【if】，await被唤醒后会继续下一行
 * 而【while】就会再次判断是否为自己的轮次，防止【虚假唤醒】
 */

public class FakeNotify {
    public static String TURN = "1";
    public static final ReentrantLock LOCK = new ReentrantLock();
    public static final Condition CONDITION = LOCK.newCondition();

    public static void print1() {
        LOCK.lock();

        try {
            if (!TURN.equals("1")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println(Thread.currentThread().getName());
            TURN = "2";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }

    public static void print2() {
        LOCK.lock();

        try {
            if (!TURN.equals("2")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println(Thread.currentThread().getName());
            TURN = "3";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }

    public static void print3() {
        LOCK.lock();

        try {
            if (!TURN.equals("3")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println(Thread.currentThread().getName());
            TURN = "1";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                FakeNotify.print1();
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                FakeNotify.print2();
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                FakeNotify.print3();
            }
        }).start();
    }
}
