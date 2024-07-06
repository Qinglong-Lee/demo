package com.example.study.thread.synchronize;

public class SynchronizedTest extends Thread {
    public static int a = 0;
    public static Object lock = new Object();

//    同步方法只能锁住当前对象，没法指定锁
//    因此如果要指定锁，用同步代码块
    public /*synchronized*/ void calculate() {
        synchronized(lock) {
            a += 5;
            a -= 2;
        }

    }

    @Override
    public void run() {
//        循环次数不能太小，否则看不出效果
        for (int i = 0; i < 1000; i++) {
            calculate();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest thread1 = new SynchronizedTest();
        SynchronizedTest thread2 = new SynchronizedTest();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(SynchronizedTest.a);
    }
}
