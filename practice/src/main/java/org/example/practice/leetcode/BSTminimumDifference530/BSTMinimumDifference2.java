package org.example.practice.leetcode.BSTminimumDifference530;

/**
 * 计算【二叉搜索树】中任意两节点【差的绝对值】的最小值
 *
 * 思路：【Mirrors 算法中序遍历】
 * https://blog.csdn.net/x0919/article/details/120602597
 *
 * 由于【二叉搜索树】的有序性质，中序遍历可以得到有序序列
 * 中序遍历有多种方式，递归，栈，还有【Mirrors】，其中递归和栈都需要大量内存空间存储中间数据，而【Mirrors】则不用
 * 【Mirrors】的原理是在遍历过程中将【下次遍历要返回的节点】存储为当前节点右子树，以便实现【回溯】的效果
 * 而【回溯】之后，有会将更新过的节点【还原】，保证树结构不变
 * 【Mirrors】首选左子树遍历，找到【左子树最右节点】，将其右子树指向当前节点，作为【回溯节点】
 * 当发现节点没有左子树或者遍历到【回溯节点】时，就转向右子树，并且将【回溯节点】还原
 *
 * 这样最终就能同时实现前序，中序和后续遍历：
 *      前序遍历只需要在【设置回溯节点】和【遍历右节点】的时候获取节点值，因为【设置回溯节点】意味着当前节点还有左子树，就会继续向左深入
 *      中序遍历只需在【还原回溯节点】和【遍历右节点】的时候获取节点值，因为【还原回溯节点】意味着左子树已遍历完成，已经回溯到根节点
 *      后序比较复杂，需要【对树逆序】
 */
public class BSTMinimumDifference2 {
    int min = Integer.MAX_VALUE, pre = -1;
    public int getMinimumDifference(TreeNode root) {
        TreeNode cur = root;

        while (cur != null) {
            TreeNode mostRight = cur.left;
//            如果有左子树
            if (mostRight != null) {
//                找到【左节点的最右节点】，排除【辅助链】，即遍历过程中加的链
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
//                第一次遍历到【左节点的最右节点】，添加【辅助链】，继续向左遍历
                if (mostRight.right != cur) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else { // 第二次遍历到【左节点的最右节点】，代表着此次遍历是【回溯之后的遍历】，清除【辅助链】，向右继续遍历
                    updateMinAndPre(cur);
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else { // 如果没有左子树，则向右遍历，
                updateMinAndPre(cur);
                cur = cur.right;
            }
        }

        return min;
    }

    private void updateMinAndPre(TreeNode cur) {
//        System.out.println(cur.val + " ");
        min = Math.min(min, Math.abs(pre < 0 ? min : (cur.val - pre)));
        pre = cur.val;
    }

    public void buildBST(Integer[] arr, TreeNode root, int idx) {
        int l = idx * 2 + 1, r = idx * 2 + 2;
        if (l < arr.length && arr[l] != null) {
            root.left = new TreeNode(arr[l]);
            buildBST(arr, root.left, l);
        }

        if (r < arr.length  && arr[r] != null) {
            root.right = new TreeNode(arr[r]);
            buildBST(arr, root.right, r);
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {4,2,6,1,3};
        Integer[] arr = new Integer[] {543,384,652,null,445,null,699};

        BSTMinimumDifference2 test = new BSTMinimumDifference2();
        TreeNode root = new TreeNode(arr[0]);
        test.buildBST(arr, root, 0);
        System.out.println(test.getMinimumDifference(root));

    }

}
