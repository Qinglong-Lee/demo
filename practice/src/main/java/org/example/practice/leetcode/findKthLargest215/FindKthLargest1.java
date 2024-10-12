package org.example.practice.leetcode.findKthLargest215;

/**
 * 找出数组中第 k 大的数
 * 思路：【二叉排序树】
 *
 * 定义一个二叉排序树
 * 将数组转换为数形结构
 * 然后【中序遍历】树，按【右，根，左】顺序
 */
public class FindKthLargest1 {
    private int k, count, result;

    private class Node {
        private Node left, right;
        private int val;

        public Node(int val) {
            this.val = val;
        }

        ;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private void insert(Node n, int val) {
        if (n.val > val) {
            if (n.left == null) {
                n.left = new Node(val);
            } else {
                insert(n.left, val);
            }
        } else {
            if (n.right == null) {
                n.right = new Node(val);
            } else {
                insert(n.right, val);
            }
        }
    }

    private void search(Node n) {
        if (n.right != null) {
            search(n.right);
        }
        count++;
        if (this.k == count) {
            result = n.val;
            return;
        }

        if (n.left != null) {
            search(n.left);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        this.k = k;
        Node heap = new Node(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            insert(heap, nums[i]);
        }

        search(heap);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;

        FindKthLargest1 test = new FindKthLargest1();
        System.out.println(test.findKthLargest(nums, k));
    }
}
