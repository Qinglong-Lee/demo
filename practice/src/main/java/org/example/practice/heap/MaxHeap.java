package org.example.practice.heap;

import java.util.Arrays;

/**
 * 给定一个数组，将其调整为【大顶堆】，并实现【获取最大值】
 */
public class MaxHeap {
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

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int size = nums.length;
        MaxHeap test = new MaxHeap();
        test.buildMaxHeap(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println("Pop: " + test.popHeap(nums, size--));
        System.out.println(Arrays.toString(nums));
        System.out.println("Pop: " + test.popHeap(nums, size--));
        System.out.println(Arrays.toString(nums));
        System.out.println("Pop: " + test.popHeap(nums, size--));
        System.out.println(Arrays.toString(nums));
    }
}
