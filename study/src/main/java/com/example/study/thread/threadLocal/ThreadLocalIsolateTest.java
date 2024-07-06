package com.example.study.thread.threadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocalIsolateTest {
    public static class ValueObj {
        ValueObj(String name) {
            this.name = name;
        }
        public String name;
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadLocal 所谓的线程隔离，指的是下面的 new ValueObj("Ada")，即每次初始化都为当前线程创建换一个新的对象
//        那么这种场景下的线程隔离就和通常意义上的线程同步场景不同了
//        线程同步场景下，共享变量是需要被不同线程读写，而且各线程间需要访问变量的实时状态
//        ThreadLocal 线程隔离场景下，所谓的共享变量其实不用共享，即当前线程无需知道其他线程对当前变量的更新
//        那么通常做法是利用局部变量在每个线程中保存一个变量副本，专供当前线程使用。但是这样做有个问题：局部变量需要再线程执行过程中在不同方法中传递
//        因此 java 提供了 ThreadLocal 来解决此问题：为每个线程创建一个变量副本实现线程间隔离，利用线程专属内存空间实现同一个线程中不同方法间的变量共享
        ThreadLocal<ValueObj> threadLocal = ThreadLocal.withInitial(() -> new ValueObj("Ada"));

        new Thread(() -> {
            ValueObj obj = threadLocal.get();
            obj.name = "Balana";
            System.out.println(Thread.currentThread().getName() + ": " + obj.name);
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            ValueObj obj = threadLocal.get();
//            obj.name = "Candy";
            System.out.println(Thread.currentThread().getName() + ": " + obj.name);
        }).start();

    }
}
