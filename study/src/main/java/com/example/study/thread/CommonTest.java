package com.example.study.thread;

public class CommonTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("sub thread end");
        });
        t.start();
        System.out.println("main end");
    }
}
