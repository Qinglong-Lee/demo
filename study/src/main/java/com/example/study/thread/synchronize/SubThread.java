package com.example.study.thread.synchronize;

public class SubThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Counter.count --;
//            System.out.println(Thread.currentThread().getName() + ": " + Counter.count);
        }
    }
}
