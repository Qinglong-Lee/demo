package org.example.practice.leetcode.linkListReverse92;

/**
 * 反转链表中指定一段长度的节点
 *
 * 思路：【链表递归反转，截断重新拼接】
 * 直接把【指定一段长度】拿走，看似是要反转链表中的值，其实就是要反转链表节点
 * 先把要反转的链表段和不需要反转的链表段分离，反转之后再拼接即可
 *
 * 递归反转：
 * 从头结点开始，先将后面的节点都反转，然后将【反转链表尾节点】的后置节点置为当前节点
 * 每次递归都返回【反转链表尾节点】，即当前节点
 * 如果【当前节点为 null】或者【当前节点的后置节点为 null】，则说明当前节点为【尾节点或其后置节点】，直接返回当前节点，因为后面没有链表需要反转
 * 注意：
 * 每次将【反转链表尾节点】的后置节点置为当前节点后还需要【将当前节点的后置节点置为 null】
 * 虽然递归返回过程中每个节点的后置节点都会被重置，但是当返回到【原始链表的头结点时】，就结束了，头结点的后置节点不会被重置
 * 因此每次递归都【事先断掉原始后置节点】
 *
 */
class LinkListReverse4 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private ListNode reverseLinkedList(ListNode head) {
        // 使用递归反转一个链表
        if (head == null || head.next == null) return head;

        ListNode reversedTail = reverseLinkedList(head.next);

        reversedTail.next = head;
        head.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        ListNode three = new ListNode(3, five);

        LinkListReverse4 test = new LinkListReverse4();
        test.reverseBetween(three, 1, 2);
    }
}

