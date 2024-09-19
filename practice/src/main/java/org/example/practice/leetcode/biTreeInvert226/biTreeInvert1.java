package org.example.practice.leetcode.biTreeInvert226;

/**
 * 水平反转二叉树，每个子树也都反转
 *
 * 思路：【递归反转】
 * 如果左右子树都已反转，则直接将当前节点的左右子树调换即可
 */
public class biTreeInvert1 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);

        root.left = invertedRight;
        root.right = invertedLeft;

        return root;
    }
}
