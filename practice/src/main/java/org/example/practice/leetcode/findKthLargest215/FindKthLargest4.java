package org.example.practice.leetcode.findKthLargest215;

/**
 * 找出数组中第 k 大的数
 * 思路：【大根堆】
 *
 * 将数组调整为大根堆
 * 然后弹出根节点 k 次
 */
public class FindKthLargest4 {
    public void adjustToMaxHeap(int[] arr, int size, int root) {
        int l = root * 2 + 1, r = root * 2 + 2;
        if (l >= size) return;
//        找出【根节点】，【左节点】，【右节点】中最大的，然后和根节点比较，目的是将三者最大值换为根节点
        int max = root;
        if (l < size && arr[max] < arr[l]) max = l;
        if (r < size && arr[max] < arr[r]) max = r;

        if (max != root) {
            swap(arr, max, root);
//            交换后的元素需要递归调整【以此元素为根节点的子树】，防止因当前调整影响了子树
            adjustToMaxHeap(arr, size, max);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void buildMaxHeap(int[] arr) {
        int length = arr.length;
//        从【最后一个元素的父节点】开始【从后往前】将所有子树调整为大顶堆
//        目的是为了将【较大元素】上移
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustToMaxHeap(arr, length, i);
        }
    }

    public int popHeap(int[] nums, int size) {
        if (size < 0) return -1;

        int val = nums[0];
//        弹出根节点后将最后一个节点放到根节点处
        swap(nums, 0, size - 1);
//        然后从根节点开始【从上往下】调整堆为大顶堆，因为此时除了根节点，所有子树都是大顶堆
        adjustToMaxHeap(nums, size - 1, 0);
        return val;
    }

    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        buildMaxHeap(nums);

        for (int i = 1; i < k; i++) {
            popHeap(nums, size--);
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
//        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int k = 4;
//        int[] nums = new int[]{1};
//        int k = 1;
        FindKthLargest4 test = new FindKthLargest4();
        System.out.println(test.findKthLargest(nums, k));
    }
}
