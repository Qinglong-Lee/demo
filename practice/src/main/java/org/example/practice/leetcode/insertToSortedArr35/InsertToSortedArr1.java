package org.example.practice.leetcode.insertToSortedArr35;

/**
 * 插入指定值到一个有序数组，已存在则返回索引，否则返回插入位置索引
 *
 * 思路：【递归二分查找】
 * 不断和有序数组的【中位数】作比较
 * 相等则直接返回索引位置
 * 大于或小于则递归右侧子数组或左侧子数组，直到子数组长度为 1
 */
public class InsertToSortedArr1 {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        return helper(nums, 0, length - 1, target);
    }

    public int helper(int[] nums, int l, int r, int target) {
        if (l == r) return target <= nums[l] ? l : l + 1;

        int length = r - l + 1;
        int m = l + length / 2;
        int m_val = nums[m];

        if (m_val > target) {
            if (m == l) return l;
            return helper(nums, l, m - 1, target);
        }
        if (m_val < target) {
            if (m == r) return m + 1;
            return helper(nums, m + 1, r, target);
        }

        return m;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 5;
//        int target = 2;
//        int target = 7;

//        int[] nums = new int[]{1};
//        int target = 1;

//        int[] nums = new int[]{1, 3};
//        int target = 4;

        InsertToSortedArr1 test = new InsertToSortedArr1();
        System.out.println(test.searchInsert(nums, target));
    }
}
