package com.example.study.thread.synchronize;

public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        //线程 1，2 同时到达，均通过（instance == null）判断
        // 线程 1 进入下面的同步块，线程 2 被阻塞
        if (instance == null) {
            synchronized (Singleton.class) {

                //线程 1 执行发现 instance 为 null, 初始化实例后，释放锁
                //线程 2 进入同步块，此次 instance 已经被初始化。无法通过 if 条件，避免多次重复初始化
                if (instance == null) {

                    //对象初始操作并非原子操作（1.分配内存空间 2.初始化 3.地址赋值给变量）
                    //没有 volatile 三个指令会被重排，如 1->3->2
                    //导致线程 2 在外层 if 拿到的 instance != null 却没有初始化（无状态），如此线程 2 将无法获得真正的实例，即使线程 1 最终会生成完整实例
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}

