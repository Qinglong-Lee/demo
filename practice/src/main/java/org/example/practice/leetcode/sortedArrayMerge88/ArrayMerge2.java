package org.example.practice.leetcode.sortedArrayMerge88;

import java.util.Arrays;

/**
 * 合并两个指定长度的有序数组到第一个数组使合并后的数组也是有序的
 *
 * 思路：【同向遍历两个数组，将更小的元素放入另一个数组】
 * 复制第一个数组所有元素用于第一个数组遍历
 * 分别从做到右遍历两个数组，比较每个元素
 * 较小的的元素放入最终合并数组中
 * 最后剩余元素合并到最后
 */
public class ArrayMerge2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = Arrays.copyOf(nums1, m);
        int i = 0, j = 0;

        for (int k = 0; k < m + n; k++) {
            if(i < m && j < n) {
                if (tmp[i] < nums2[j]) {
                    nums1[k] = tmp[i++];
                } else {
                    nums1[k] = nums2[j++];
                }
            } else if(i == m && j < n) {
                nums1[k] = nums2[j++];
            } else if(i < m && j == n) {
                nums1[k] = tmp[i++];
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}, nums2 = new int[]{2, 5, 6};
//        int m = 3, n = 3;

//        int[] nums1 = new int[]{1}, nums2 = new int[]{};
//        int m = 1, n = 0;

        int[] nums1 = new int[]{0}, nums2 = new int[]{1};
        int m = 0, n = 1;

        ArrayMerge2 arrayMerge = new ArrayMerge2();
        arrayMerge.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }
}
