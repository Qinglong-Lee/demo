package org.example.practice.leetcode.biTreeSymmetric101;

/**
 * 判断二叉树是否对称【镜像结构，值相互对应】
 *
 * 思路：【递归镜像判断】
 * 想到递归就容器有个误区：以为递归就必须要所有子树都是对称
 * 以上递归是【垂直方向的递归】，即是从上往下的角度来想问题的
 * 其实还可以【水平递归】，即每次递归的判断逻辑是从同一层级的节点来做比较的
 * 每次递归一个层级，然后比较左右的节点值
 * 下次递归比较的是对称节点，因此将【左的左和右的右】【左的右和右的左】分别再次比较
 */
public class BiTreeSymmetric1 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.val == right.val
                && check(left.left, right.right)
                && check(left.right, right.left)) {
            return true;
        }

        return false;
    }
}
