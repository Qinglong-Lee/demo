package com.example.study.dataStructure.lfu;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 【最不常使用】缓存，Least Frequency Used Cache，以【访问频次】和【访问时间】两个维度判断，频次优先级更高
 * 实现逻辑：定义一个键值对缓存，容量大小固定，put 的时候如果容量已满则【驱逐最不常使用的 key】。get 和 put 都视为对某个 key 的访问
 * 实现思想：
 * 1.键值对存储首选 HashMap
 * 2.LinkedHashMap 的访问顺序只支持【访问时间】维度，并不支持【访问频次】
 * 3.而且有了【访问频次】的维度，就不得不和每个曾经频次大于当前 key 的数据做比较
 * 4.在 HashMap 的 value 中记录下每个节点的访问频次和访问时间，每次访问更新数据在
 * 5.需要驱逐的时候【按频次和时间排序以取得最小节点】
 * 6.由于缓存容量固定，为了避免 HashMap 不必要的自动扩容，负载因子设为 1
 *
 * 也可以继承 LinkedHashMap 重写 afterNodeAccess，每次节点访问都去做一次排序，或直接将【最不常访问】节点移到链头
 * 但这样每次访问都需要堆链做移动操作，没有必要，事实上只需要在驱逐的时候找到【最不常使用】就好了
 */
public class LFUCache2<K, V> extends HashMap<K, V> {
    private HashMap<K, Node> record;
    private int capacity;

    public LFUCache2(int capacity) {
        super(capacity, 1f);
        this.record = new HashMap<K, Node>();
        this.capacity = capacity;
    }

    private class Node implements Comparable{
        private K key;
        private int accessCount;
        private long timestamp;

        public Node(K key, int accessCount, long timestamp) {
            this.key = key;
            this.accessCount = accessCount;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Object o) {
            Node anotherNode = (Node)o;
            if(accessCount > anotherNode.accessCount) {
                return 1;
            } else if (accessCount == anotherNode.accessCount) {
                if (timestamp > anotherNode.timestamp) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", accessCount=" + accessCount +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    @Override
    public V get(Object key) {
        V rst = null;

        if(containsKey(key)) {
            rst = super.get(key);
            makeFrequently(key);
        }
        System.out.println("GET: " + key + ", RETURN:" + rst);
        System.out.println(toString());
        System.out.println(record.toString());

        return rst;
    }

    @Override
    public V put(K key, V value) {
        V rst;

        if(containsKey(key)) {
            makeFrequently(key);
        } else {
            if(size() >= capacity) {
                evict();
            }
        }
        rst = super.put(key, value);
        record.put(key, new Node(key, 1, System.currentTimeMillis()));
        System.out.println("PUT: {" + key + ", " + value + "}");
        System.out.println(toString());
        System.out.println(record.toString());
        return rst;
    }

    private void evict() {
        Node evictNode = Collections.min(record.values());
        Object evictKey = evictNode.key;
        remove(evictKey);
        record.remove(evictKey);
    }

    private void makeFrequently(Object key) {
        Node node = record.get(key);
        node.accessCount ++;
        node.timestamp = System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {

//        LFUCache2 lRUCache = new LFUCache2(3);
//        lRUCache.put(1, 1); // cache is {1=1}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.put(3, 3); // cache is {1=1, 2=2, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.get(2);    // return 2, cache is {1=1, 3=3, 2=2}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.get(1);    // returns 1, cache is {2=2, 1=1, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.put(4, 4); // evicts key 2, cache is {4=4, 1=1, 3=3}
//        TimeUnit.SECONDS.sleep(1);
//        lRUCache.put(5, 5); // evicts key 4, cache is {5=5, 1=1, 3=3}


        LFUCache2<String, String> lRUCache = new LFUCache2(3);
        lRUCache.put("1", "1"); // cache is {1=1}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put("2", "2"); // cache is {1=1, 2=2}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put("3", "3"); // cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get("2");    // return 2, cache is {1=1, 3=3, 2=2}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get("3");    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get("3");    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get("3");    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get("1");    // returns 1, cache is {2=2, 1=1, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put("4", "4"); // evicts key 2, cache is {4=4, 1=1, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put("5", "5"); // evicts key 4, cache is {5=5, 1=1, 3=3}
    }

}
