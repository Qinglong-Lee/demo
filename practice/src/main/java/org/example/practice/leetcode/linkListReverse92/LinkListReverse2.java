package org.example.practice.leetcode.linkListReverse92;

/**
 * 反转链表中指定一段长度的节点
 *
 * 思路：【利用链表反转】
 * 直接把【指定一段长度】拿走，看似是要反转链表中的值，其实就是要反转链表节点
 * 对链表进行一次遍历
 * 遍历过程中记录下【反转段】左节点和前置节点，左节点最后用于和【右侧非反转段】拼接，前置节点用于和【反转之后的链】拼接
 *
 * 反转逻辑：
 * 记录【当前节点】【前置节点】【原始后置节点】
 * 头结点不用反转，从第二节点开始
 * 反转前先备份【原始后置节点】，用于向后遍历
 * 将当前节点的后置节点置为前置节点
 * 下次遍历前：重置前置节点为当前节点，重置当前节点为【原始后置节点】
 */
public class LinkListReverse2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null) {
            return head;
        }

        ListNode front = null, p = head, leftNode = null;
        int i = 1;

        while (i <= left) {
            ListNode nextNode = p.next;

            if (i == left - 1) {
                front = p;
            }
            if (i == left) {
                leftNode = p;
            }

            p = nextNode;
            i++;
        }

        ListNode last = leftNode, originNext;

        while (i <= right) {
            originNext = p.next;
            p.next = last;

            last = p; p = originNext;
            i++;
        }

        ListNode rightNode = last, end = p;
        if (front != null) {
            front.next = rightNode;
        }
        leftNode.next = end;

        return left == 1 ? rightNode : head;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        LinkListReverse2 test = new LinkListReverse2();
        test.reverseBetween(one, 2, 4);

    }
}
