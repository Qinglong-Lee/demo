package org.example.practice;

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
            }, null);

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
