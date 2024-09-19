package org.example.practice.leetcode.linkListReverse92;

/**
 * 反转链表中指定一段长度的节点
 *
 * 思路：【利用数组实现节点值替换】
 * 反转节点最容易的是直接替换节点的值，而不用改变链表结构
 * 可是要替换两个位置的节点值，就需要同时定位到两个位置才行，数组就是最好方便的定位方式
 * 先遍历一遍链表，遍历过程中将要反转的所有节点存入一个数组中
 * 然后将数组中前后节点的值进行替换即可
 */
public class LinkListReverse1 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int length = right - left + 1;
        ListNode p = head;
        ListNode[] array = new ListNode[length];

        int i = 1;
        while (p != null) {
            if (i >= left && i <= right) {
                array[i - left] = p;
            }

            i++;
            p = p.next;
        }

        int m = 0, n = right - left;
        while (m < n) {
            int temp = array[m].val;
            array[m].val = array[n].val;
            array[n].val = temp;

            m++;
            n--;
        }

        return head;
    }
}
