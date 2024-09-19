package org.example.practice.leetcode.biTreeSymmetric101;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否对称【镜像结构，值相互对应】
 *
 * 思路：【层级遍历，将镜像节点相邻入队比较】
 * 二叉树的层级遍历是用一个队列依次存储每个层级的所有节点，顺序通常是左到右或右到左
 * 但是这样的存储顺序不便于镜像比较，因此可以在存储的时候改变存储顺序
 * 【左的左和右的右】【左的右和右的左】分别相邻
 * 每次出队出两个相邻节点比较即可
 */
public class BiTreeSymmetric3 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root.left);
            offer(root.right);
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
