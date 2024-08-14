package org.example.practice;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueSort {
    public static void main(String[] args) throws InterruptedException {
        Integer[] data = new Integer[] {4, 7, 2, 6, 8};

        List<Integer> list = Arrays.asList(data);
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(List.of(data));
//        Integer i = queue.take();
//        queue.forEach(System.out::println);

        Collections.sort(list);
        list.forEach(System.out::println);
    }
}
