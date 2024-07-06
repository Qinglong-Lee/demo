package com.example.study.thread.threadLocal;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> finalThreadLocal = threadLocal;
        Arrays.stream(new String[]{"Ally", "Belaty", "Cancy"})
                .forEach(p -> new Thread(() -> {
//                    无法通过一下方式直接获取到当前线程的 ThreadLocalMap
//                    因为 ThreadLocalMap 是受保护的，只有通过 ThreadLocal 这个工具类才能对其进行操作
//                    因此，拿不到 ThreadLocalMap 也就无法调用其 get，set和 remove 方法
//                    而只有这三个方法可以清空无用的缓存数据
//                    当 ThreadLocal 变量被置空后，线程就失去了访问 ThreadLocalMap 的唯一途径，也就无法清空无用的数据
//                    造成内存泄漏
//                    另外，get，set方法清空无用的缓存数据时附加的操作，并不是其主要功能，而且在两次方法调用期间也有短暂的内存溢出风险
//                    因此一个 ThreadLocal 使用完毕就立即调用 remove 清空才是正确的做法
//                    ThreadLocal.ThreadLocalMap map = Thread.currentThread().getMap(Thread.currentThread());
                    finalThreadLocal.set(p);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + finalThreadLocal.get());
                }).start());
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.gc();
//            System.out.println(threadLocal);
//        }).start();
//        threadLocal = null;
//        System.gc();
        System.out.println("end");
    }
}
