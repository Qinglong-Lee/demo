package org.example.practice.leetcode.linkListMerge21;

/**
 * 非递减有序链表合并
 *
 * 思路：【双指针遍历】
 * 从头比较，将小的提出来放到新链表中
 * 接着比较剩余节点
 *
 * 注意：
 * 和【数组合并】相比，链表本身就是指针，因此无需额外定义指针变量
 * 节点的比较在某个链表到达链尾后终止，之后还需判断某个链表是否有剩余节点需要链接到链尾
 */
public class LinkListMerge1 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        ListNode p1 = list1, p2 = list2;
        ListNode newNode = new ListNode();
        ListNode p = newNode;

        while (list1 !=null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }

        p.next = list1 == null ? list2 : list1;

        return newNode.next;
    }
}
