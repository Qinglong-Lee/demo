package org.example.practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread {
    /**
     * 启动两个线程
     * 一个打印 A-Z
     * 一个打印 1-52
     * 每个字母后面跟两个数字，交替打印
     */

    public static String TURN = "C";
    public static final ReentrantLock LOCK = new ReentrantLock();
    public static final Condition CONDITION = LOCK.newCondition();

    public static void printCharacter(char c) {
        LOCK.lock();

        try {
            while (!TURN.equals("C")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println(Thread.currentThread().getName() + ": " + c);
            TURN = "N";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }

    public static void printNumber(int n) {
        LOCK.lock();

        try {
            while (!TURN.equals("N")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + ": " + n);
            System.out.println(Thread.currentThread().getName() + ": " + (++n));
            TURN = "S";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }


    public static void printSpliter() {
        LOCK.lock();

        try {
            while (!TURN.equals("S")) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            System.out.println("===============");
            TURN = "C";
            CONDITION.signalAll();

        } finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (char c = 'A'; c <= 'Z'; c++) {
                MultiThread.printCharacter(c);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 52; i += 2) {
                MultiThread.printNumber(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 25; i++) {
                MultiThread.printSpliter();
            }
        }).start();
    }
}
