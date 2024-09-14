package org.example.practice.leetcode.removeDuplicates80;

import java.util.Arrays;

/**
 * 【原地】删除数组重复元素，每个元素最【最多保留 2 个重复值】，返回处理后的长度
 *
 * 思路：【滑动窗口】，用一个长度为 3 的窗口从前往后逐步移动，确保每次移动前窗口中头尾元素不等，即可保证【最多两个重复值】
 * 发现头尾相等则将尾部元素【冒泡移动到数组尾部】
 * s 和 e 分别代表窗口的头和尾
 * c 代表待处理的数组元素个数：因为每次【冒泡】后，处理过的元素就可以忽略
 */
public class RemoveDuplicates3 {
    public int removeDuplicates(int[] nums) {
        int size = nums.length;
        int s = 0, e = s + 2, c = size;

        if (size < 3) return size;

        while (e < c) {
            boolean more = false;

            if (nums[s] == nums[e]) {
                int p = e;

                while (p < c - 1) {
                    if (nums[e] != nums[p + 1]) more = true;

                    int temp = nums[p];
                    nums[p] = nums[p + 1];
                    nums[p + 1] = temp;

                    p++;
                }

                if (!more) {
                    return e;
                }

                c--;
            }

            if (nums[s] != nums[e]) {
                s++; e++;
            }
        }

        return c;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
//        int[] nums = new int[]{1};
//        int[] nums = new int[]{1, 1, 1};
//        int[] nums = new int[]{1, 1, 1, 2};
//        int[] nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3};
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 3, 3, 3, 4, 4};
        int[] nums = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};

        RemoveDuplicates3 test = new RemoveDuplicates3();

        System.out.println(test.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
