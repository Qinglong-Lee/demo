package org.example.practice.leetcode.removeDuplicates80;

import java.util.Arrays;

/**
 * 【原地】删除数组重复元素，每个元素最【最多保留 2 个重复值】，返回处理后的长度
 *
 * 思路：【循环计数冒泡】，从前往后遍历过程中对元素计数，如果大于 2 则【冒泡后移所有多余元素到尾部】，循环处理每个元素
 * head 和 tail 分别是【待处理数组】的头和尾，遍历过的元素和冒泡后移的元素都是【已处理元素】，应该在下次遍历被排除
 * p1 和 p2 用于【冒泡后移所有多余元素】
 **/
public class RemoveDuplicates1 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int val, count, size = nums.length;
        int head = 0, tail = size - 1;
        int p1 = 1, p2;
        
        while (head < tail) {
            p1 = head; val = nums[head]; count = 1;

            while (count < 3) {
                if (++p1 > tail) {
                    return tail + 1;
                }

                if (nums[p1] == val) {
                    count++;
                } else {
                    val = nums[p1];
                    count = 1;
                }
            }

            head = p1;

            if ((p2 = p1 + 1) > tail) {
                return p1;
            }

            while (val == nums[p2]) {
                if (++p2 > tail) {
                    return p1;
                }
            }
            
            boolean switched = false;
            
            while (p2 <= tail) {
                if (nums[p1] != nums[p2]) {
                    int temp = nums[p1];
                    nums[p1] = nums[p2];
                    nums[p2] = temp;

                    p1++; p2++;
                    switched = true;
                } else {
                    p2 ++;
                }
            }

            tail = p1 - (switched ? 1 : 0);
            
        }

        return p1;
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

        RemoveDuplicates1 test = new RemoveDuplicates1();

        System.out.println(test.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
