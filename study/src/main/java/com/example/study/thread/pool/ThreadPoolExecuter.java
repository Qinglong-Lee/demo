package com.example.study.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试手动构造 ThreadPoolExecuter
 * BlockingQueue 用于当线程数量大于【核心线程数】时，将任务线放入自队列等待，只有队列满时，才会继续创建新线程知道【最大线程数】
 * SynchronousQueue 是一个容量为 0 的队列，因此可以用于【无需阻塞队列】的场景
 * 实际上它不是一个真正的队列，因为它不会为队列中元素维护存储空间。与其他队列不同的是，它维护一组线程，这些线程在等待着把元素加入或移出队列
 *
 * ThreadFactory 用于为线程池中每个线程实现自定义扩展
 * 重写的方法以当前任务为入参，需要自己利用此任务构造一个线程，这时就可以对线程进行扩展，常用于自定义线程名
 *
 * RejectedExecutionHandler 用于自定义任务因超出最大线程数被拒绝后的【拒绝策略】
 * ThreadPoolExecutor 本身实现了一组策略
 * AbortPolicy：直接抛出异常，默认策略
 * DiscardPolicy：什么也不干
 * DiscardOldestPolicy：从阻塞队列中移除最先加入的任务，将当前任务加入阻塞队列
 * CallerRunsPolicy：用线程池所在的父线程执行被拒绝的任务，在本例中就是主线程
 *
 * 两种添加任务的方式：
 * execute：没有任务返回值
 * submit：有任务返回值，以 Future 的形式返回
 */
public class ThreadPoolExecuter {
//    public static AtomicInteger seq = new AtomicInteger(1);
    public static class Inner {
        public String name = "A";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger seq = new AtomicInteger(1);
        int i = 0;
        Inner obj = new Inner();
        obj.name = "B";
        ExecutorService service =
                new ThreadPoolExecutor(1,
                        2,
                        10,
                        TimeUnit.SECONDS,
                        /*new LinkedBlockingQueue(1),*/
                        new SynchronousQueue(),
                        new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                obj.name = "C";
                                return new Thread(r, "MY-THREAD-" + seq.getAndAdd(1) + i + obj.name);
                            }
                        },
//                        默认拒绝策略, 和下面的自定义策略实现一类似，直接抛出异常
//                        ThreadPoolExecutor.AbortPolicy

                        new RejectedExecutionHandler() {
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                System.out.println("Task " + r.toString() + "rejected by " + executor.toString());
//                                throw new RejectedExecutionException("Task " + r.toString() +
//                                        " rejected from " +
//                                        executor.toString());
                            }
                        });


        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " started 1!");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " ended 1!");
        });
        System.out.println("============");
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " started 2!");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " ended 2!");
        });
        System.out.println("=============");
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " started 3!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " ended 3!");
        });

//        Future future = service.submit(() -> {
//            System.out.println(Thread.currentThread().getName() + " started 2!");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return "DONE";
//        });
//
//        service.submit(() -> {
//            System.out.println(Thread.currentThread().getName() + " started 3!");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        System.out.println(future.get());
    }
}
