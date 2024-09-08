package com.example.study.dataStructure.lru;

import java.util.HashMap;
import java.util.LinkedList;

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
