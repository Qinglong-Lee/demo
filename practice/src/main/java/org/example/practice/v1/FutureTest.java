package org.example.practice.v1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        List<FutureTask<Boolean>> futureTasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FutureTask futureTask = new FutureTask<>(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " done!");
//                return "Success!";
            }, "Success!");
//            result 参数用于为 runnable 的 task 返回一个结果，因为 runnable 没有返回值
//            作用同 callable 接口中的返回值

            new Thread(futureTask).start();

            futureTasks.add(futureTask);
        }

        futureTasks.stream().forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(Thread.currentThread().getName() + " done!");
    }
}
