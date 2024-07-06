package com.example.study.thread.synchronize;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        SubThread subThread = new SubThread();
        addThread.start();
        subThread.start();
        addThread.join();
        subThread.join();
        System.out.println(Counter.count);
    }
}
