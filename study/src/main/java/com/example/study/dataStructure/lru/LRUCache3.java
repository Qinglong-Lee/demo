package com.example.study.dataStructure.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 【最久未使用】缓存，Least Recently Used Cache，仅以访问时间为维度
 * 实现逻辑：定义一个键值对缓存，容量大小固定，put 的时候如果容量已满则【驱逐最久未使用的 key】。get 和 put 都视为对某个 key 的访问
 * 实现思想：HashMap + LinkedList
 * 1.键值对存储首选 HashMap，可以直接继承 HashMap，覆写 get，put 方法，而无需在类中维护一个 HashMap
 * 2.LinkedList 是一个双向链表结构，可用于记录【访问顺序】
 * 3.当访问某个 key 时，需要先根据 key 定位到 LinkedList 的对应节点，然后通过【删除重新添加】实现节点移动。节点的定位时间复杂度为 O(n)
 * 4.驱逐 LinkedList 的头结点
 * 5.由于缓存容量固定，为了避免 HashMap 不必要的自动扩容，负载因子设为 1
 */
public class LRUCache3 extends HashMap {
    private LinkedList list = new LinkedList<>();
    private int capacity;
    public LRUCache3(int initialCapacity) {
        super(initialCapacity, 1f);
        this.capacity = initialCapacity;
    }

    @Override
    public Object get(Object key) {
        if(containsKey(key)) {
            makeRecently(key);
            System.out.println(super.get(key)+ "  " + list.toString());
            return super.get(key);
        }
        System.out.println(-1);
        return -1;
    }

    @Override
    public Object put(Object key, Object value) {
        Object rst;
        if(containsKey(key)) {
            makeRecently(key);
            rst =  super.put(key, value);
        } else {
            if (size() >= capacity) {
                evict();
            }
            rst = super.put(key, value);
            list.add(key);
        }

        System.out.println(toString() + "  " + list.toString());
        return rst;
    }

    private void evict() {
        Object evictedKey = list.getFirst();
        remove(evictedKey);
        list.removeFirst();
    }

    private void makeRecently(Object key) {
        list.remove(key);
        list.add(key);
    }

    public static void main(String[] args) {
        LRUCache3 lRUCache = new LRUCache3(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.put(1, 11); // cache is {2=2, 1=11}
        lRUCache.get(1);    // return 11
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=11, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {3=3, 4=4}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
