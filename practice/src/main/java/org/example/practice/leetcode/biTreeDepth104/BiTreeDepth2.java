package org.example.practice.leetcode.biTreeDepth104;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 计算二叉树高度
 *
 * 思路：【依次遍历每一层所有节点，计算层级数】
 * 利用一个队列存储每一层的节点，一个变量存储遍历了多少层
 * 下一层节点就是上一层所有节点的左右节点
 * 遍历所有层级直到队列大小为 0，表示当前层级已无节点
 *
 * 注意：
 * 之所以用队列，是为了方便节点的移除和增加
 * 队列是先进先出，上层节点从队头出队【得到出队节点】，然后将【出队节点的左右节点】从队尾入队
 * 无需使用【迭代器】，因为队列的出队就能得到当前节点，使用迭代器反而会【因为迭代过程中改变了队列】而抛出异常
 * 迭代器迭代过程【不能改变队列】
 */
public class BiTreeDepth2 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> layerNodes = new LinkedList<TreeNode>() {{
            add(root);
        }};

        do {
            int size = layerNodes.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = layerNodes.poll();

                if (node.left != null)
                    layerNodes.add(node.left);
                if (node.right != null)
                    layerNodes.add(node.right);
            }

            depth++;
        } while (layerNodes.size() > 0);

        return depth;
    }
}
