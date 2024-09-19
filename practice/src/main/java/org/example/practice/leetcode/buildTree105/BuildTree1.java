package org.example.practice.leetcode.buildTree105;

import java.util.Arrays;
import java.util.List;

/**
 * 根据二叉树的前序遍历和中序遍历还原二叉树
 *
 * 思路：【递归】
 * 前序遍历特点是 [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
 * 中序遍历特点是 [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 * 那么只要从前序的第一节点取得根节点，然后去中序遍历中定位根节点，即可获得根节点的左右子树的前序中序遍历结果
 * 依次递归，直到前序或中序为空，每次都组合根节点的左右子树
 */
public class BuildTree1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;

        List<Integer> preorderList = Arrays.stream(preorder).boxed().toList();
        List<Integer> inorderList = Arrays.stream(inorder).boxed().toList();

        int length = inorder.length;
        int root = preorder[0];
        int pos = inorderList.indexOf(root);
        int leftSize = pos;

        int[] leftPreorder = preorderList.subList(1, leftSize + 1)
                .stream().mapToInt(Integer::intValue).toArray();
        int[] leftInorder = inorderList.subList(0, pos)
                .stream().mapToInt(Integer::intValue).toArray();
        int[] rightPreorder = preorderList.subList(leftSize + 1, length)
                .stream().mapToInt(Integer::intValue).toArray();
        int[] rightInorder = inorderList.subList(pos + 1, length)
                .stream().mapToInt(Integer::intValue).toArray();

        TreeNode leftTree = buildTree(leftPreorder, leftInorder);
        TreeNode rightTree = buildTree(rightPreorder, rightInorder);

        return new TreeNode(root, leftTree, rightTree);
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        BuildTree1 test = new BuildTree1();
        test.buildTree(preorder, inorder);
    }
}
