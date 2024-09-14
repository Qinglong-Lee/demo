package org.example.practice.leetcode.ArrayElementRemove27;

import java.util.Arrays;

/**
 * 【原地】移除数组中指定元素并返回移除后长度
 *
 * 思路：【用其他元素覆盖要被删除的元素】，逐个删除元素后的空位需要通过元素左移填补，直接覆盖可避免元素移动
 * left 代表【下一个元素插入位置】，right 代表【待处理元素】
 * 待处理元素不需要移除则插入 left，且 left 右移，需要移除则忽略
 */
class ArrayElementRemove2 {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 2, 3};
//        int val = 3;

        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

//        int[] nums = new int[]{};
//        int val = 0;

//        int[] nums = new int[]{4, 5};
//        int val = 4;

        ArrayElementRemove2 test = new ArrayElementRemove2();

        System.out.println(test.removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
}
