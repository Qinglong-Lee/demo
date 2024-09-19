package org.example.practice.leetcode.circleLink141;

/**
 * 判断一个单向链表是否有环
 *
 * 思路：
 * 利用快慢两个指针遍历连边，
 * 如果有环，则快慢指针进入环后，快指针一定会在某一刻追上慢指针
 * 为了方便判断【是否追上】，将快指针每次移动两个节点，慢指针一个。这样追上的时候 fast = slow
 * 如果快指针移动三个节点，那【是否追上】就不止需要判断 fast = slow，还要判断 fast.last = slow，因为可能直接超过
 **/
public class CircleLink2 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow.next == fast.next) return true;

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode zero = new ListNode(0);
        ListNode _four = new ListNode(-4);

        three.next = two;
//        two.next = zero;
//        zero.next = _four;
//        _four.next = two;

        CircleLink2 test = new CircleLink2();
        System.out.println(test.hasCycle(three));

    }
}

