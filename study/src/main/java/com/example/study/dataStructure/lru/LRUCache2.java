package com.example.study.dataStructure.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 【最久未使用】缓存，Least Recently Used Cache，仅以访问时间为维度
 * 实现逻辑：定义一个键值对缓存，容量大小固定，put 的时候如果容量已满则【驱逐最久未使用的 key】。get 和 put 都视为对某个 key 的访问
 * 实现思想：LinkedHashMap accessOrder
 * 1.键值对存储首选 HashMap，但是普通的 HashMap 无法记录使用情况
 * 2.LinkedHashMap 继承自 HashMap，改写了 HashMap 的 Entry，将所有 Entry 使用双向链表连接，默认实现了【插入顺序】，但也支持【访问顺序 accessOrder】
 * 3.每次 get 和 put 都会将新的 Entry 加到链尾，即将【最近访问】移动到链尾
 * 4.LinkedHashMap 还提供 removeEldestEntry 方法让用户自动以驱逐策略，默认不驱逐，本场景下为【容量满则驱逐】，驱逐的是头结点
 * 5.由于缓存容量固定，为了避免 HashMap 不必要的自动扩容，负载因子设为 1
 */
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
