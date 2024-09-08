package com.example.study.dataStructure.lru;


import lombok.val;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
