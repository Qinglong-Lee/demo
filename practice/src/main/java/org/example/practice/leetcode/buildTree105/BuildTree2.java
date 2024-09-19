package org.example.practice.leetcode.buildTree105;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据二叉树的前序遍历和中序遍历还原二叉树
 *
 * 思路：【递归 + 指针 + HashMap 定位】
 * 递归过程需要不断地截断数组来获取下一个前序中序，截断过程比较复杂，时间复杂度和空间复杂度都不低
 * 而且要在中序中定位根节点也不方便
 * 因此用指针标识下一次的前序中序头尾，用 HashMap 映射中序的值和位置，方便定位
 */
public class BuildTree2 {
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;

        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeByPreInOrder(preorder, inorder,
                0, preorder.length - 1,
                0, inorder.length - 1);
    }

    public TreeNode buildTreeByPreInOrder(int[] preorder, int[] inorder, int pre_head, int pre_tail, int in_head, int in_tail) {
        if (pre_head > pre_tail) return null;

        int root = preorder[pre_head];
        int in_root_idx = inorderMap.get(root);
        int leftSize = in_root_idx - in_head;
        int rightSize = in_tail - in_root_idx;

        int left_pre_head = pre_head + 1;
        int left_pre_tail = pre_head + leftSize;
        int left_in_head = in_head;
        int left_in_tail = in_root_idx - 1;

        int right_pre_head = pre_head + leftSize + 1;
        int right_pre_tail = pre_head + leftSize + rightSize;
        int right_in_head = in_root_idx + 1;
        int right_in_tail = in_root_idx + rightSize;


        TreeNode leftTree = buildTreeByPreInOrder(preorder, inorder, left_pre_head, left_pre_tail, left_in_head, left_in_tail);
        TreeNode rightTree = buildTreeByPreInOrder(preorder, inorder, right_pre_head, right_pre_tail, right_in_head, right_in_tail);

        return new TreeNode(root, leftTree, rightTree);
    }

    public static void main(String[] args) {
//        int[] preorder = new int[]{3, 9, 20, 15, 7};
//        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] preorder = new int[]{-1};
        int[] inorder = new int[]{-1};

        BuildTree2 test = new BuildTree2();
        test.buildTree(preorder, inorder);
    }
}
