package com.example.study.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 测试 ArrayList 和 LinkedList get 和 add 的性能
 *
 * ArrayList 的优势是：
 * 1.【数组的快速定位】
 * 2.【数组结构带来的高内存使用率】：数组是连续存储，减少内存碎片
 * 瓶颈是：
 * 【数组扩容和数据移动】
 *
 * LinkedList 的优势是：
 * 1.【双向链表支持双向遍历】：
 *  双向遍历的优势是【当定位到某个元素时，可以快速移动元素到链头或链尾，或者删除当前元素，并保证不断链，因为每个节点都保存了前置和后置两个节点】
 *  单向链表只有后置节点，无法移动或移除元素并不断链
 * 2.【迭代器遍历到某个元素后的元素操作】：在使用迭代器的场景下利用迭代器操作当前元素，避免元素遍历
 * 3.【元素批量连续操作】：
 *  虽然元素的定位会有元素遍历导致的性能瓶颈，但是如果定位到某个元素后连续批量插入或删除元素，并不需要每次元素操作都重新遍历一次
 *  一次遍历，多次操作】冲淡了遍历的性能损耗
 *  而相比之下 ArrayList 就没有这个又是，因为每次元素操作都还是会导致元素的移动
 * 瓶颈是：
 * 【元素的遍历（随机元素操作都会涉及到元素遍历）】
 */
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
//        linkedList.get(99999999); //查找最后一个
//        System.out.println("LinkedList get 耗时：" + (System.currentTimeMillis()-start));
////        ArrayList get 时间复杂度 O(1)，因为数组是顺序存储，查找速度极快
//        start = System.currentTimeMillis();
//        arrayList.get(99999999); //查找最后一个
//        System.out.println("ArrayList get 耗时：" + (System.currentTimeMillis()-start));
//
////        LinkedList add 【平均】时间复杂度 O(n)，
////        和插入的位置有关，因为插入前要先查找到插入位置
////        由于 LinkedList 是双向链表，所以插入到前面和后面都很快，插入到中间位置是最慢的
//        start = System.currentTimeMillis();
////        linkedList.add(0, 2); //插入到第一个
//        linkedList.add(99999999, 2); //插入到最后一个
//        linkedList.add(49999999, 2); //插入到中间位置
//        System.out.println("LinkedList add 耗时：" + (System.currentTimeMillis()-start));
////        ArrayList add 【平均】时间复杂度 O(n)
////        因为要移动数组元素，平均情况下，需要移动 n/2 个元素
//        start = System.currentTimeMillis();
//        arrayList.add(0, 2); //插入到第一个
////        arrayList.add(99999999, 2); //插入到最后一个
//        System.out.println("ArrayList add 耗时：" + (System.currentTimeMillis()-start));

//        LinkedList 使用【列表迭代器】来插入元素时间复杂度为 O(1)
//        因为【列表迭代器】已经定位到某个元素，插入元素时只需要插入到当前元素之前或者之后就行，无需先遍历到指定元素
//        但是此种方式也并不是随机插入，只不过当应用场景刚好涉及到迭代器，我们就可以复用迭代器的插入功能，提高插入效率
        start = System.currentTimeMillis();
        linkedList.listIterator().add(2);
        System.out.println("LinkedList listIterator().add 耗时：" + (System.currentTimeMillis()-start));
//        ArrayList 使用【列表迭代器】来插入元素时间复杂度为 O(n)
//        虽然【列表迭代器】已经定位到某个元素，但是 ArrayList 的插入性能瓶颈不是元素定位，而是元素移动
//        迭代器无法优化元素移动问题
        start = System.currentTimeMillis();
        arrayList.listIterator().add(2);
        System.out.println("ArrayList listIterator().add 耗时：" + (System.currentTimeMillis()-start));


    }
}
