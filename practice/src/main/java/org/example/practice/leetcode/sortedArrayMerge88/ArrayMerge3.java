package org.example.practice.leetcode.sortedArrayMerge88;

import java.util.Arrays;

/**
 * 合并两个指定长度的有序数组到第一个数组使合并后的数组也是有序的
 *
 * 思路：【从后往前比较两个数组元素，大的放到最后】
 * 要想不使用额外空间的前提下直接合并两个数组，就得充分利用数组的多余空间
 * 从后往前遍历比较，然后将大的元素放到最后
 * 如果放的是当前数组的元素，则相当于移动了元素，如果放的是另一个数组元素，则待处理元素就少了一个
 * 不管怎样，剩余空间都充足
 */
public class ArrayMerge3 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;

        for (int k = m + n - 1; k >= 0; k--) {
            if (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[k] = nums1[i--];
                } else {
                    nums1[k] = nums2[j--];
                }
            } else if (i < 0 && j >= 0) {
                nums1[k] = nums2[j--];
            } else if (i <= 0 && j < 0) {
                nums1[k] = nums1[i--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}, nums2 = new int[]{2, 5, 6};
        int m = 3, n = 3;

//        int[] nums1 = new int[]{1}, nums2 = new int[]{};
//        int m = 1, n = 0;

//        int[] nums1 = new int[]{0}, nums2 = new int[]{1};
//        int m = 0, n = 1;

        ArrayMerge3 arrayMerge = new ArrayMerge3();
        arrayMerge.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }
}
