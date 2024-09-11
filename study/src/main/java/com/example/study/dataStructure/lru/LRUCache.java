package com.example.study.dataStructure.lru;

import lombok.val;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 【最久未使用】缓存，Least Recently Used Cache，仅以访问时间为维度
 * 实现逻辑：定义一个键值对缓存，容量大小固定，put 的时候如果容量已满则【驱逐最久未使用的 key】。get 和 put 都视为对某个 key 的访问
 * 实现思想：LinkedHashMap insertOrder + 手动节点移动
 * 1.键值对存储首选 HashMap，但是普通的 HashMap 无法记录使用情况
 * 2.LinkedHashMap 继承自 HashMap，改写了 HashMap 的 Entry，将所有 Entry 使用双向链表连接，实现了【插入顺序】
 * 3.每次 put 都会将新的 Entry 加到链尾
 * 4.可以利用此特性手动实现【最近访问节点到链尾的移动】：先删除节点，再从新添加
 * 5.驱逐的是头节点，即 keySet 中第一个 key，因为 LinkedHashMap 是默认记录【插入顺序】的，所以 keySet 的迭代器也是有序的
 * 6.由于缓存容量固定，为了避免 HashMap 不必要的自动扩容，负载因子设为 1
 */
public class LRUCache {
    private HashMap<Integer, Integer> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
//        map = new LinkedHashMap<>(capacity, 0.75F, true);
        map = new LinkedHashMap<>(capacity, 1f);
    }

    public Integer get(Integer key) {
        Integer rst = -1;
        if(map.containsKey(key)) {
            rst = map.get(key);
            makeRecently(key);
        }
        System.out.println(rst);
        return rst;
    }

    public void put(Integer key, Integer value) {
        if(map.containsKey(key)) {
            map.put(key, value);
           makeRecently(key);
        }
        else {
            if (map.size() >= capacity) {
                evict();
            }
            map.put(key, value);
        }
        System.out.println(map.toString() + " capacity: " + getCapacity());
    }

    private int getCapacity() {
        Class<?> mapClass = HashMap.class;
        try {
            Method capacityMethod = mapClass.getDeclaredMethod("capacity");
            capacityMethod.setAccessible(true);
            return (int) capacityMethod.invoke(map);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void iterate() {
        String rst = "{";
        val iterator = map.keySet().iterator();
        if(iterator.hasNext()) {
            Integer key = iterator.next();
            Integer val = map.get(key);
            rst += key + "=" + val + ",";
        }
        System.out.println(rst.substring(0, rst.length() - 1) + "}");
    }

    private void makeRecently(Integer key) {
        Integer value = map.get(key);
        map.remove(key);
        map.put(key, value);
    }

    private void evict() {
        Integer firstKey = map.keySet().iterator().next();
        map.remove(firstKey);
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
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
