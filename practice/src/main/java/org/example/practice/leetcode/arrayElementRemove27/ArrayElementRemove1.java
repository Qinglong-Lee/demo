package org.example.practice.leetcode.arrayElementRemove27;

import java.util.Arrays;

/**
 * 【原地】移除数组中指定元素并返回移除后长度
 *
 * 思路：【遍历交换】，分别从头尾两端遍历，将【头部要移除的元素】和【尾部不移除的元素】交换
 *
 * p1 和 p2 分别尾头指针和尾指针
 * 每次都要检查 p2 指向元素是否是需要移除元素，如果是则需要 p2 前移
 */
public class ArrayElementRemove1 {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int p1 = 0, p2 = length - 1;

        if (length == 0) {
            return 0;
        }
        while (p1 < p2) {
            if (nums[p1] == val) {
                if (nums[p2] != val) {
                    int tmp = nums[p1];
                    nums[p1] = nums[p2];
                    nums[p2] = tmp;
                    p1 ++;
                    p2 --;
                } else {
                    p2--;
                }
            } else {
                p1++;

                if (nums[p2] == val) {
                    p2--;
                }
            }
        }

        return nums[p1] == val ? p1 : p1 + 1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 2, 3};
//        int val = 3;

        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;

//        int[] nums = new int[]{};
//        int val = 0;

//        int[] nums = new int[]{4, 5};
//        int val = 4;

        ArrayElementRemove1 test = new ArrayElementRemove1();

        System.out.println(test.removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
}
