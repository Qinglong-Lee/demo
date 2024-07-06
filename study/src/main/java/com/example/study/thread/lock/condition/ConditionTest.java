package com.example.study.thread.lock.condition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public LinkedList<Integer> queue = new LinkedList<>();
    public static AtomicInteger productId = new AtomicInteger(0);
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            queue.add(productId.getAndAdd(1));
            System.out.println(Thread.currentThread().getName() + " Produced: " + queue.getLast());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " Waiting...");
                condition.await();
            }
            System.out.println("Current size: " + queue.size());
            queue.stream().forEach(p -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " Interrupted!");
                }
                System.out.println(Thread.currentThread().getName() + " Consumed: " + p);
            });
            queue.clear();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Interrupted!");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest queue = new ConditionTest();
        List<Thread> consumerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(() -> queue.produce(), "PRODUCER" + i);
            Thread cosumer = new Thread(() -> queue.consume(), "CONSUMER" + i);
            consumerList.add(cosumer);
            producer.start();
            cosumer.start();
        }

        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        consumerList.stream().forEach(thread -> {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        });
    }
}
