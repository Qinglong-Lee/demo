package org.example.practice.leetcode.buildTree106;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据二叉树的后序遍历和中序遍历还原二叉树
 *
 * 思路：【递归 + 指针 + HashMap 定位 + 全局变量】
 * 后序遍历特点是 [ [左子树的前序遍历结果], [右子树的前序遍历结果], 根节点 ]
 * 中序遍历特点是 [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 * 那么只要从后序的最后一个节点取得根节点，然后去中序遍历中定位根节点，即可获得根节点的左右子树的后序中序遍历结果
 * 依次递归，直到后序或中序为空，每次都组合根节点的左右子树
 *
 * 递归过程需要不断地截断数组来获取下一个前序中序，截断过程比较复杂，时间复杂度和空间复杂度都不低
 * 而且要在中序中定位根节点也不方便
 * 因此用指针标识下一次的前序中序头尾，用 HashMap 映射中序的值和位置，方便定位
 * 因为在递归过程中要不断地使用到后序和中序两个数组，而且后序的根节点位置也只是一直递减
 * 所以直接用全局变量存储这些数据，避免在形参中传递，导致方法逻辑复杂
 *
 * 注意：
 * 由于是从后序的右到左移动查找根节点，因此下一个根节点其实是右子树的根节点，这和前序是相反的
 * 因此递归过程中也要先构造右子树再构造左子树
 */
public class BuildTreeByInAndPost1 {
    private int post_root_idx;
    private Map<Integer, Integer> inorderMap;
    private int[] inorder, postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        post_root_idx = postorder.length - 1;
        this.inorder = inorder;
        this.postorder = postorder;

        inorderMap = new HashMap<>(inorder.length, 1f);
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeRecursively(0, inorder.length - 1);
    }

    private TreeNode buildTreeRecursively(int inorder_head, int inorder_tail) {
        if (inorder_head > inorder_tail) return null;

        int rootVal = postorder[post_root_idx --];
        int inorder_root_idx = inorderMap.get(rootVal);
        TreeNode rightNode = buildTreeRecursively(inorder_root_idx + 1, inorder_tail);
        TreeNode leftNode = buildTreeRecursively(inorder_head, inorder_root_idx - 1);
        return new TreeNode(rootVal, leftNode, rightNode);
    }
}
