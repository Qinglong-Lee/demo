package org.example.practice.leetcode.linkListMerge21;

/**
 * 非递减有序链表合并
 *
 * 思路：【递归合并】
 * 从头比较，较小的节点单独提出来放一边，剩下的所有节点合并完后，链接在此节点之后
 * 直到任意链表达到链尾，则将另一个链表作为合并结果返回，递归结束
 */
public class LinkListMerge2 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;

        } else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }
}
