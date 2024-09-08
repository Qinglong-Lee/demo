package com.example.study.thread.starvation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程饥饿
 * 由于线程所需要的资源一直无法获得导致线程一直处于等待状态
 */
public class ThreadStarvationTest {
    public static void main(String[] args) {
        //创建只有一个线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //向线程池中添加取数据任务与添加数据的任务
        executorService.submit(new TakeDataTask());
        executorService.submit(new AddDataTask());
        executorService.shutdown();
    }

    //创建一个阻塞队列
    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(10);
    //定义一个向阻塞队列中添加数据的任务
    private static class AddDataTask implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() +  " 编号的线程执行添加数据的任务");
            try {
                QUEUE.put(123);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //定义从队列中取数据的任务
    private static class  TakeDataTask implements Runnable{
        @Override
        public void run() {
            System.out.println( Thread.currentThread().getId()  + " 编号的线程执行取数据的任务");
            try {
                Integer data = QUEUE.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

