package org.example.practice.leetcode.buildTree105;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据二叉树的前序遍历和中序遍历还原二叉树
 *
 * 思路：【递归 + 指针 + HashMap 定位 + 全局变量】
 * 递归过程需要不断地截断数组来获取下一个前序中序，截断过程比较复杂，时间复杂度和空间复杂度都不低
 * 而且要在中序中定位根节点也不方便
 * 因此用指针标识下一次的前序中序头尾，用 HashMap 映射中序的值和位置，方便定位
 * 因为在递归过程中要不断地使用到前序和中序两个数组，而且前序的根节点位置也只是一直递增
 * 所以直接用全局变量存储这些数据，避免在形参中传递，导致方法逻辑复杂
 */
public class BuildTree3 {
    private Map<Integer, Integer> inorderMap;
    private int pre_root_idx = 0;
    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;

        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        this.preorder = preorder;
        this.inorder = inorder;
        
        return buildTreeByPreInOrder(0,inorder.length - 1);
    }

    public TreeNode buildTreeByPreInOrder(int in_head, int in_tail) {
        if (in_head > in_tail) return null;

        int root = preorder[pre_root_idx ++];
        int in_root_idx = inorderMap.get(root);

        int left_in_head = in_head;
        int left_in_tail = in_root_idx - 1;

        int right_in_head = in_root_idx + 1;
        int right_in_tail = in_tail;


        TreeNode leftTree = buildTreeByPreInOrder(left_in_head, left_in_tail);
        TreeNode rightTree = buildTreeByPreInOrder(right_in_head, right_in_tail);

        return new TreeNode(root, leftTree, rightTree);
    }

    public static void main(String[] args) {
//        int[] preorder = new int[]{3, 9, 20, 15, 7};
//        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] preorder = new int[]{-1};
        int[] inorder = new int[]{-1};

        BuildTree3 test = new BuildTree3();
        test.buildTree(preorder, inorder);
    }
}
