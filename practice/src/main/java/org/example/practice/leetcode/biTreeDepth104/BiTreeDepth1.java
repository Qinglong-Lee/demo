package org.example.practice.leetcode.biTreeDepth104;

/**
 * 计算二叉树高度
 *
 * 思路：【递归】
 * 如果分别知道了左右子树的高度，则当前节点所在树的高度为两者最大值 + 1
 * 最终会递归到叶子节点，其左右子树都为 null，高度设为 0 即可
 */
public class BiTreeDepth1 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
