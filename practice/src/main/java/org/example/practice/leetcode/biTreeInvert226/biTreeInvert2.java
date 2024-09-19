package org.example.practice.leetcode.biTreeInvert226;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 水平反转二叉树，每个子树也都反转
 *
 * 思路：【层级遍历反转】
 * 用一个队列存储每一层级的节点
 * 存储之前先反转左右子树
 * 每个层级的每个节点都反转之后就为最终结果
 */
public class biTreeInvert2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>(){{offer(root);}};

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode leftNode = node.left;
            node.left = node.right;
            node.right = leftNode;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return root;
    }
}
