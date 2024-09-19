package org.example.practice.leetcode.circleLink141;

/**
 * 判断一个单向链表是否有环
 *
 * 思路：
 * 所有节点遍历一次，遍历过程中将整个链表的方向改为相反方向。即所有节点的 next 指针都从后置节点改为前置节点
 * 这样在遍历过程中如果有环，则最终一定会返回头结点，如果没环，则不会再次遍历到头结点，以此判断是否有环
 *
 * 用三个变量分别记录遍历的当前节点，前置节点，后置节点
 * 先把头结点的 next 置为 null（好像对于遍历也没有意义，不过这样遍历完成整个链表就完全反向了）
 * 从第二个节点开始遍历，每次遍历先判断是否是头结点，是则返回 true
 * 如果不是头结点，则利用三个变量来使当前节点的 next 反向，并移动到下一个节点
 * 遍历节点为 null，代表已遍历到尾节点，无环
 */
public class CircleLink1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode lastNode = head;
        ListNode currentNode = head.next;
        ListNode nextNode;
        head.next = null;

        while (currentNode != null) {
            if (currentNode == head) return true;

            nextNode = currentNode.next;
            currentNode.next = lastNode;
            lastNode = currentNode;
            currentNode = nextNode;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode zero = new ListNode(0);
        ListNode _four = new ListNode(-4);

        three.next = two; two.next = zero; zero.next = _four; _four.next = two;

        CircleLink1 test = new CircleLink1();
        System.out.println(test.hasCycle(three));

    }
}

