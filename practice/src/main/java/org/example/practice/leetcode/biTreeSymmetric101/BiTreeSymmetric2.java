package org.example.practice.leetcode.biTreeSymmetric101;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否对称【镜像结构，值相互对应】
 *
 * 【官方遍历题解，有疑问，为什么要重复存储节点】
 */
public class BiTreeSymmetric2 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root);
            offer(root);
        }};

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;

            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        return true;
    }
}
