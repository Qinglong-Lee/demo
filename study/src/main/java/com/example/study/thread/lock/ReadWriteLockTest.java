package com.example.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试可重入锁的读写锁
 * 写锁和其他写锁以及所有读锁互斥
 * 读锁和所有写锁互斥，和其他读锁共享
 * 前提是读操作不能改变数据，否则会有线程安全问题
 * 比如本例读操作只是返回数据，适合用读写锁提高读效率
 * 如果是读取一个链表的某个值，然后从这个链表中删除这个值，则不能使用读写锁，因为读操作有数据更新
 * 由于 Condition 是基于锁机制来等待和通知的，而读锁之间是共享的，并不互斥，因此读锁没有 Condition
 * 即读线程之间没有线程同步关系
 * TODO: 如何让读锁线程等待写锁线程并让写锁线程通知读锁线程？
 */
public class ReadWriteLockTest {
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
//    利用 Condition 等待和通知时需要使用同一个 Condition
//    因此不能在方法中使用局部变量定义 Condition，即使是从同一个 writeLock获取也不行
    public static Condition condition = writeLock.newCondition();
    public static Integer count = 0;
    public static boolean goon = false;

    public static void contine() {
        try {
            TimeUnit.SECONDS.sleep(2);
            writeLock.lock();

            goon = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
    public static void produce() {
        writeLock.lock();
        try {
            while (!goon) {
                System.out.println(Thread.currentThread().getName() + " waiting...! " );
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " got writeLock! " );

            TimeUnit.SECONDS.sleep(1);
            count += 1;
            System.out.println(Thread.currentThread().getName() + " produced " + count);
//            writeLock.notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " released writeLock! " );
            writeLock.unlock();
        }
    }

    public static Integer consume() {
        readLock.lock();
//        读锁不支持 Condition, 调用一下方法会抛出 UnsupportedOperationException
//        readLock.newCondition();
        System.out.println(Thread.currentThread().getName() + " got readLock! " );
        try {
//            while (count == 0) {
//                readLock.wait();
//            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " consumed " + count);
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " released readLock! " );
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> ReadWriteLockTest.contine()).start();

        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(() -> ReadWriteLockTest.produce(), "PRODUCER-" + i);

            Thread consumer = new Thread(() -> ReadWriteLockTest.consume(), "CONSUMER-" + i);

            producer.start();
            TimeUnit.SECONDS.sleep(2);
            consumer.start();
        }
    }
}
