package com.example.study.thread.synchronize;

public class Counter {
    public static int count = 0;
    public static final Object lock = new Object();
}
