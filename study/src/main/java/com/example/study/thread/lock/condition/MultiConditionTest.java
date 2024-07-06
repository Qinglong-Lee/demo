package com.example.study.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 Reentrantlock 的 condition 在多线程同步场景下下相比单一锁的优势
 *
 * 多线程按顺序调用 , A->B->C ...
 * 每个线程共打印 1 -> 5 五个数
 * 轮流打印：A 1, B 1 ... A 2, B 2 ... A 5, B 5 ...
 *
 * 一个 Reentrantlock 可以有多个 condition，condition 意味着一个条件，即满足什么条件则等待 await 或被唤醒 signal/signalAll
 * 多线程同步场景下不同线程之间的同步条件是不同的，比如此例中 A 等待之后唤醒 B, B 等待后唤醒 C ...
 * 如果所有线程都用一个条件做同步，即每个线程处理完自己的逻辑之后都唤醒所有等待线程，然后抢占到锁的线程依靠业务逻辑来判断是否已经轮到当前线程，会造成不必要的锁竞争和资源浪费
 * 比如 D 线程执行完成于是唤醒所有等待线程（A, C, E, G），这四个线程（等待池）就会一起和其他非等待状态的线程（锁池）竞争锁，如果被 A, C, G 获取，则根据轮次要求他们还是必须等待，直到 E 获取锁，才能继续
 * 即 get lock after wait 之后有可能还是被阻塞
 * 而如果 D 只唤醒 E，就不会有这个问题，因为只有 E 以及非等待状态的线程会一起竞争锁，就可以减少锁的无效竞争
 * 即 get lock after wait 之后一定是当前轮次，可以继续执行
 *
 * condition 就有这个优势，conditionA.signalAll 只会唤醒所有调用了 conditionA.await 的线程，而不会唤醒 conditionB/C/D.await 的线程
 */
public class MultiConditionTest {
    private static char startChar = 'A';
    private static int count = 10;
    private char current = startChar;
    private Lock lock = new ReentrantLock();
    private Condition singleCondition = lock.newCondition();

    public void prints(int i, Condition currentCondition, Condition nextCondition) {
        System.out.println(Thread.currentThread().getName() + " try lock...");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " get lock");
        try {
            while (!Thread.currentThread().getName().equals(String.valueOf(current))) {
                System.out.println(Thread.currentThread().getName() + " wait...");
//                singleCondition.await();
                currentCondition.await();
                System.out.println(Thread.currentThread().getName() + " get lock after wait");
            }

            System.out.println(Thread.currentThread().getName() + "\t" + i);

            if(current == startChar + count - 1) {
                current = startChar;
            }
            else {
                current += 1;
            }
//            singleCondition.signalAll();
            nextCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String args[]){
        final MultiConditionTest test = new MultiConditionTest();
        Condition fistCondition = test.lock.newCondition();
        Condition tmp = fistCondition;

        for (int i = 0; i < count; i++) {
            char name = (char) (startChar + i);
            Condition currentCondition = tmp;
            if (i == count - 1) {
                tmp = fistCondition;
            }
            else {
                tmp = test.lock.newCondition();
            }
            Condition nextCondition = tmp;
            new Thread(() -> {
                for (int j = 1; j <= 5; j++) {
                    test.prints(j, currentCondition, nextCondition);
                }
            }, String.valueOf(name)).start();
        }
    }
}
