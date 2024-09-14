package org.example.practice.leetcode.removeDuplicates80;

import java.util.Arrays;

/**
 * 【原地】删除数组重复元素，每个元素最【最多保留 2 个重复值】，返回处理后的长度
 *
 * 思路：【就地覆盖】，将数组看做【已处理】和【待处理】两个数组。【已处理】是初始长度为 2 的左侧部分，【代处理】初始长度为 size - 2 的右侧部分
 * 用两个指针分别只想【最后一个已处理元素】和【第一个待处理元素】
 * 判断待处理元素是否应该被保留，应该则将其直接加到已处理数组最后，否则忽略
 * 【已处理数组】越来越长。【待处理数组】越来越短，直到为 0
 * 通过对比【已处理数组倒数第二个元素值】和【待处理数组第一个元素值】判断元素是否保留
 **/
public class RemoveDuplicates2 {
    public int removeDuplicates(int[] nums) {
        int processed_tail = 1, processing_idx = 2;
        int size = nums.length;

        if (size <= 2) return size;

        while (processing_idx < size) {
            int comparing_idx = processed_tail - 1;

            if (nums[comparing_idx] != nums[processing_idx]) {
                nums[++processed_tail] = nums[processing_idx];
            }

            processing_idx++;
        }

        return processed_tail + 1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
//        int[] nums = new int[]{1};
//        int[] nums = new int[]{1, 1, 1};
        int[] nums = new int[]{1, 1, 1, 2};
//        int[] nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3};
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 3, 3, 3, 4, 4};
//        int[] nums = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};

        RemoveDuplicates2 test = new RemoveDuplicates2();

        System.out.println(test.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
