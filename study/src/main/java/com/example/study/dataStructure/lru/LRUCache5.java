package com.example.study.dataStructure.lru;

import java.util.HashMap;

/**
 * 【最久未使用】缓存，Least Recently Used Cache，仅以访问时间为维度
 * 实现逻辑：定义一个键值对缓存，容量大小固定，put 的时候如果容量已满则【驱逐最久未使用的 key】。get 和 put 都视为对某个 key 的访问
 * 实现思想：
 * 1.键值对存储首选 HashMap
 * 2.get 和 put 操作时都需要记录下使用情况。首先想到的是将每个访问过的 key 记录在列表，按使用时间排序，但是排序的时间复杂度为 O(n)
 * 3.双向链表可以在定位到某个节点的前提下以 O(1) 的复杂度替换当前节点到链头或者链尾
 * 4.HashMap 的 value 可以作为双向链表的节点，将所有 value 用双向链表连接，get 方法可以直接定位到链表节点，然后就可以直接交换节点到链尾
 * 5.链尾节点总是【最近访问】，链头节点总是【最久未访问】，驱逐的总是链头节点
 * 6.缓存类要记录下链表的【头结点】和【尾节点】，并不断更新
 * 7.由于缓存容量固定，为了避免 HashMap 不必要的自动扩容，负载因子设为 1
 * 8.head 和 tail 不是链表的【真实头尾指针】，而是【伪指针】，即在真实链表头尾各加上一个【假头和假尾】。目的是为了简化链表的增删改操作（无需考虑头尾的特殊情况）
 */
public class LRUCache5 {
    private HashMap<Object, Node> map;
    private Node head, tail;
    private int capacity;

    public LRUCache5(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity, 1f);

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.pre = null; head.next = tail;
        tail.pre = head; tail.next = null;
    }

    private class Node {
        private Object key;
        private Object value;
        private Node pre;
        private Node next;

        public Node(Object key, Object value, Node pre, Node next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public String toString() {
            return " [" +
                    "value:" + value.toString() + ", "
                    + "pre:" + (pre == null ? null : pre.key) + ", "
                    + "next:" + (next == null ? null : next.key) + ""
                    + "]";
        }
    }

    public Object get(Object key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
//            如果 key 存在则在获取值之后更新当前访问节点为【最近使用】
            makeRecently(node);

            System.out.println(node.value);
            System.out.println(map.toString());
            return node.value;
        } else {
            System.out.println(-1);
            System.out.println(map.toString());
            return -1;
        }

    }

    public Object put(Object key, Object value) {
        Object preVal = null;
//        如果已存在则：1.覆盖节点值；2.更新当前访问节点为【最近使用】
        if(map.containsKey(key)) {
            Node node = map.get(key);
            preVal = node.value;
            node.value = value;
            makeRecently(node);
//        如果不存在则新加节点到 map 和链表
        } else {
            if(map.size() >= capacity) {
//                如果超过容量则驱逐【最久未使用】节点，即头节点
                evict();
            }
//            新节点添加到尾部，将当前尾节点作为新节点的前置节点
            Node newNode = new Node(key, value, tail.pre, tail);

            map.put(key ,newNode);

            //将之前的尾节点链接到新节点
            tail.pre.next = newNode;
            tail.pre = newNode;
        }

        System.out.println(map.toString());
        return preVal;
    }

    /**
     * 容量满时驱逐【最久未使用】，即头节点
     * 先从 map 中移除节点
     * 再调整链表：重置头节点
     */
    private void evict() {
        Object headKey = head.next.key;
        map.remove(headKey);
        head.next.next.pre = head;
        head.next = head.next.next;
    }

    /**
     * 调整传入节点为【最近使用】，即移动到链尾
     * 要根据节点的不同位置判断是否要调整头结点
     * @param node
     */
    private void makeRecently(Node node) {
//        只有非尾节点才需要做调整
        if(node != tail.pre) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
        }
    }

    public static void main(String[] args) {
//        LRUCache4 lRUCache = new LRUCache4(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        lRUCache.put(1, 11); // cache is {2=2, 1=11}
//        lRUCache.get(1);    // return 11
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=11, 3=3}
//        lRUCache.get(2);    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {3=3, 4=4}
//        lRUCache.get(1);    // return -1 (not found)
//        lRUCache.get(3);    // return 3
//        lRUCache.get(4);    // return 4


        LRUCache5 lRUCache = new LRUCache5(3);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.put(1, 11); // cache is {2=2, 1=11}
        lRUCache.get(2);    // return 2, cache is {1=11, 2=2}
        lRUCache.put(3, 3); // cache is {1=11, 2=2, 3=3}
        lRUCache.get(2);    // returns 2, cache is {1=11, 3=3, 2=2}
        lRUCache.put(4, 4); // evicts key 1, cache is {3=3, 2=2, 4=4}
        lRUCache.get(1);    // return -1, cache is {3=3, 2=2, 4=4}
        lRUCache.get(3);    // return 3, cache is {2=2, 4=4, 3=3}
        lRUCache.get(4);    // return 4, cache is {2=2, 3=3, 4=4}
    }

}
