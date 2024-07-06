package com.example.study.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class TestList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList<>();
        for (int i = 0; i < 100000000; i++) {
            linkedList.add(1);
            arrayList.add(1);
        }
//        LinkedList get 时间复杂度 O(n)，因为要从头开始查找
        long start = System.currentTimeMillis();
        linkedList.get(9999999);
        System.out.println("LinkedList get 耗时：" + (System.currentTimeMillis()-start));
//        ArrayList get 时间复杂度 O(1)，因为数组是顺序存储，查找速度极快
        start = System.currentTimeMillis();
        arrayList.get(9999999);
        System.out.println("ArrayList get 耗时：" + (System.currentTimeMillis()-start));

//        LinkedList add 时间复杂度 O(1)，即和数据总数无关
//        但是和插入的位置有关，因为插入前要先查找到插入位置，插入位置越靠后，越耗时
        start = System.currentTimeMillis();
//        linkedList.add(1, 2);
        linkedList.add(9999999, 2);
        System.out.println("LinkedList add 耗时：" + (System.currentTimeMillis()-start));
//        ArrayList add 时间复杂度 O(n)，即耗时和数据量成正比，因为要移动数组元素，和插入位置无关
        start = System.currentTimeMillis();
//        arrayList.add(1, 2);
        arrayList.add(9999999, 2);
        System.out.println("ArrayList add 耗时：" + (System.currentTimeMillis()-start));

    }
}
