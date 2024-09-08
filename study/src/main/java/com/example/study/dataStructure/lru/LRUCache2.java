package com.example.study.dataStructure.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2 extends LinkedHashMap {
    private int capacity;

    public LRUCache2(int capacity) {
        super(capacity, 1f, true);
        this.capacity = capacity;
    }

    @Override
    public Object get(Object key) {
        if(containsKey(key)) {
            System.out.println(super.get(key));
            return super.get(key);
        } else {
            System.out.println(-1);
            return -1;
        }
    }

    @Override
    public Object put(Object key, Object value) {
        Object rst = super.put(key, value);
        System.out.println(toString());
        return rst;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry eldest) {
        if(size() > capacity) {
            return true;
        }
         return false;
    }

    public static void main(String[] args) {
        LRUCache2 lRUCache = new LRUCache2(2);
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
