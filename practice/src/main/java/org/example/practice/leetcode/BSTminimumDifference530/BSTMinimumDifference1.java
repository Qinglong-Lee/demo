package org.example.practice.leetcode.BSTminimumDifference530;

/**
 * 计算【二叉搜索树】中任意两节点【差的绝对值】的最小值
 *
 * 思路：【从树中查找左右子树中唯一可能更小的节点】
 *
 * 由于【二叉搜索树】的有序性质
 * 一个节点和其左子树中任意节点【差绝对值】的最小值，一定是左子树中的【最右节点】
 * 一个节点和其右子树中任意节点【差绝对值】的最小值，一定是右子树中的【最左节点】
 * 分别对左子树和右子树递归计算，最终的到最小值
 */
public class BSTMinimumDifference1 {
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root.left != null) {
            min = Math.min(min, Math.abs(root.val - root.left.val));

            TreeNode right = root.left.right;
            TreeNode rightest = null;
            while (right != null) {
                rightest = right;
                right = right.right;
            }

            if (rightest !=null) {
                min = Math.min(min, Math.abs(root.val - rightest.val));
            }
            getMinimumDifference(root.left);
        }

        if (root.right != null) {
            min = Math.min(min, Math.abs(root.val - root.right.val));

            TreeNode left = root.right.left;
            TreeNode leftest = null;
            while (left != null) {
                leftest = left;
                left = left.left;
            }

            if (leftest != null) {
                min = Math.min(min, Math.abs(root.val - leftest.val));
            }
            getMinimumDifference(root.right);
        }

        return min;
    }

    public void buildBST(int[] arr, TreeNode root, int idx) {
        int l = idx * 2 + 1, r = idx * 2 + 2;
        if (l < arr.length) {
            root.left = new TreeNode(arr[l]);
            buildBST(arr, root.left, l);
        }

        if (r < arr.length) {
            root.right = new TreeNode(arr[r]);
            buildBST(arr, root.right, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,2,6,1,3};
        BSTMinimumDifference1 test = new BSTMinimumDifference1();
        TreeNode root = new TreeNode(arr[0]);
        test.buildBST(arr, root, 0);
        System.out.println(test.getMinimumDifference(root));

    }

}
