package org.example.practice.v2;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    public static ReentrantLock lock = new ReentrantLock(true);
    public static Condition nCondition = lock.newCondition();
    public static Condition cCondition = lock.newCondition();
    public static String flag = "N";

    @SneakyThrows
    public static void print(Condition c, Object o) {
        lock.lock();

        String threadName = Thread.currentThread().getName();

        if(!flag.equals(threadName)) {
            c.await();
        }

        if(flag.equals(threadName) && threadName.equals("N")) {
            Integer number = (Integer)o;
            System.out.println(threadName + ": " + number);
            System.out.println(threadName + ": " + (number + 1));
            flag = "C";
            cCondition.signalAll();
        }
        if(flag.equals(threadName) && threadName.equals("C")) {
            Character character = (Character)o;
            System.out.println(threadName + ": " + character);
            flag = "N";
            nCondition.signalAll();
        }


        lock.unlock();
    }

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i <= 52; i+=2) {
                Integer number = Integer.valueOf(i);
                print(nCondition, number);
            }
        }, "N").start();

        new Thread(() -> {
            for (char c = 'A'; c <= 'Z'; c++) {
                Character character = Character.valueOf(c);
                print(cCondition, character);
            }
        }, "C").start();
    }
}
