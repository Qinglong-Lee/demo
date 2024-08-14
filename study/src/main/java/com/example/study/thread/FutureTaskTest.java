package com.example.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

public class FutureTaskTest {
    //    3 threads
    //    start 3 and print thread name
    //    wait for finish in main thread
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started!");
        List<FutureTask<Integer>> futureTaskList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " started!");
                        sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " ended!");
                        return 1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            futureTaskList.add(futureTask);

            Thread t = new Thread(futureTask);
            t.start();
        }

        futureTaskList.stream().forEach(t -> {
            try {
                t.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(Thread.currentThread().getName() + " ended!");
    }

}
