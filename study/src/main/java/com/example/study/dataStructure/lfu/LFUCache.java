package com.example.study.dataStructure.lfu;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 尝试用 TreeMap 实现 【最不常访问缓存策略】Least Frequency Used Cache 【失败】
 * TreeMap 实现了 SortMap，支持按【key】排序，而【不是按 value 排序】
 * 底层数据结构是【红黑树】，key 按【从左到右升序】，即【左子树节点小于父节点，右子树节点大于父节点】
 * 排序逻辑由给定的【Comparator】或【key 的 compareTo】决定
 *
 * 失败原因：
 * 由于 TreeMap 的排序和 get 都依赖同一个比较器
 * 因此只有在排序规则和 get 逻辑一致时才能兼容
 * 而 LFU 场景下，排序规则是按访问次数和访问时间，get 是对比 key，两者不一致
 */
public class LFUCache {
    private TreeMap<Node, Object> map;
    private int capacity;


    public LFUCache(int capacity) {
        this.capacity = capacity;
//        如果初始化指定了 Comparator，则即使 key 实现了 Comparable 接口，也无效
        map = new TreeMap<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.key.equals(o2.key)) {
                    return 0;
                }

                if(o1.times > o2.times) {
                    return 1;
                } else if (o1.times == o2.times) {
                    return o1.timestamp > o2.timestamp ? 1 : -1;
                } else {
                    return -1;
                }
            }
        });
    }

    private class Node /*implements Comparable*/{
        private Integer key;
        private Object value;
        private long timestamp;
        private int times;
        public Node(Integer key, Object value, long timestamp, int times) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
            this.times = times;
        }

        public Node(Integer key) {
            this.key = key;
        }

        /*@Override
        public int compareTo(Object o) {
            Node anotherNode = (Node)o;
            return this.key.compareTo(anotherNode.key);
        }*/

        @Override
        public String toString() {
            return " ["
                    + "key:" + key + ", "
                    + "value:" + (value == null ? "" : value.toString()) + ", "
                    + "times:" + times + ", "
                    + "timestamp:" + timestamp + ""
                    + "]";
        }
    }

    public Object get(Integer key) {
        Object value;
        Node keyNode = new Node(key);

        if(map.containsKey(keyNode)) {
            value = map.get(keyNode);
//            如果 key 存在则在获取值之后更新【访问频次和访问时间】然后重新排序
            resort(getOrgKeyNode(key), value);

//            System.out.println(node.value);
            System.out.println("get: " + key + ", return: " + value + " || " + map.toString());
            System.out.println("FirstKey: " + map.firstKey());
            return value;
        } else {
            System.out.println("get: " + key + ", return: -1 || " + map.toString());
            System.out.println("FirstKey: " + map.firstKey());
            return -1;
        }

    }

    public Object put(Integer key, Object value) {
        Object preVal = null;
        Node keyNode = new Node(key);
//        如果已存在则：1.覆盖节点值；2.更新当前访问节点【访问频次】和【访问时间】并重新排序
        if(map.containsKey(keyNode)) {
            resort(getOrgKeyNode(key), value);
//        如果不存在则新加节点到 map
        } else {
            if(map.size() >= capacity) {
//               如果超过容量则驱逐【最不常使用】节点，即头节点
                evict();
            }
//            添加新元素到 map
            Node newNode = new Node(key, value, System.currentTimeMillis(), 1);
            map.put(newNode ,value);
        }

        System.out.println("put: " + key + "==" + map.toString());
        System.out.println("FirstKey: " + map.firstKey());
        return preVal;
    }

    private Node getOrgKeyNode(Integer key) {
        return map.keySet().stream().filter(e -> e.key == key).findFirst().get();
    }

    /**
     * 容量满时驱逐【最久未使用】，即头节点
     * 先从 map 中移除节点
     * 再调整链表：重置头节点
     */
    private void evict() {
        Object firstKey = map.firstKey();
        map.pollFirstEntry();
        System.out.println("evictKey: " + firstKey);
    }

    /**
     * 调整传入节点为【最近使用】，即移动到链尾
     * 要根据节点的不同位置判断是否要调整头结点
     * @param key
     */
    private void resort(Node key, Object value) {
        key.times ++;
        key.timestamp = System.currentTimeMillis();
        map.remove(key);
        map.put(key, value);
    }

    public static void main(String[] args) throws InterruptedException {

        LFUCache lRUCache = new LFUCache(3);
        lRUCache.put(1, 1); // cache is {1=1}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put(3, 3); // cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get(2);    // return 2, cache is {1=1, 3=3, 2=2}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get(3);    // returns 3, cache is {1=1, 2=2, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.get(1);    // returns 1, cache is {2=2, 1=1, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put(4, 4); // evicts key 2, cache is {4=4, 1=1, 3=3}
        TimeUnit.SECONDS.sleep(1);
        lRUCache.put(5, 5); // evicts key 4, cache is {5=5, 1=1, 3=3}
    }

}
