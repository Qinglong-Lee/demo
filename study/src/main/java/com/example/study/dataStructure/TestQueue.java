package com.example.study.dataStructure;

import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TestQueue {
    public static void main(String[] args) {

//        LinkedBlockingQueue 是先进先出队列，意味着只能在队尾插入，从队头获取并删除数据
//        利用【Reentrantlock】实现线程安全和阻塞模式，即向饱和队列添加数据和从空队列获取数据都会等待
//        可设置初始容量，若不设置，则容量为 Integer.MAX_VALUE，相当于无限大，即 put 永不阻塞
//        分别用两个不同的锁保证读写的线程安全，即读和读互斥，写和写互斥，读写共享
//        之所以这样，是应为 LinkedBlockingQueue 是链表结构，而读只读链头，写只写链尾，两者互不影响
//        因此没必要用一个锁将读写也互斥，这样可以提升读写频繁的场景下的并发性能
//        但也因为 LinkedBlockingQueue 是链表结构，每个链节点所需存储空间更大，因此要防止数据量大导致的内存溢出问题
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<>();
        try {
//            put 无返回值，若队列满则阻塞
            linkedBlockingQueue.put(1);
//            add 返回值为 boolean，添加成功为 true，添加失败直接抛异常 IllegalStateException，本质使用 offer，不阻塞
            linkedBlockingQueue.add(1);
//            offer 返回值为 boolean，添加成功为 true，添加失败为 false，不阻塞
            linkedBlockingQueue.offer(1);
            linkedBlockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        ArrayBlockingQueue 也是先进先出队列，只能在队尾插入，从队头获取并删除数据
//        也是利用【Reentrantlock】实现线程安全和阻塞模式，即向饱和队列添加数据和从空队列获取数据都会等待
//        但是由于 ArrayBlockingQueue 是基于数组，数组是非线程安全的，而且必须初始化容量
//        因此 ArrayBlockingQueue 的读写之间也是互斥的，在读写频繁场景下并发性能较低
//        然而数组的单次的读写操作效率高且存储空间小，因此在读写频次不平衡的场景下会性能更好
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);
        try {
            arrayBlockingQueue.put(1);
            arrayBlockingQueue.add(1);
            arrayBlockingQueue.offer(1);
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        基于 PriorityQueue（非线程安全）的线程安全优先阻塞队列
//        PriorityBlockingQueue并不会阻塞生产者，而只是在没有可消费的数据时阻塞消费者
//        不初始化容量则默认是 11，队列满则自动扩容
//        不指定比较器则默认按字符顺序排序，指定比较器则按【最小优先】排序：即比较结果小的优先出队
//        内部是一个堆，即树形结构
        PriorityBlockingQueue priorityBlockingQueue =
                new PriorityBlockingQueue<Character>(10,
//                        (e1, e2) -> e1 > e2 ? 1 : -1
                        new Comparator<Character>() {
                            @Override
                            public int compare(Character o1, Character o2) {
                                return o1 > o2 ? 1 : -1;
                            }
                        }
                );

        for (char c = 'Z'; c >= 'A'; c--) {
            priorityBlockingQueue.put(c);
        }

        System.out.println(priorityBlockingQueue.size());

        try {
            System.out.println(priorityBlockingQueue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
