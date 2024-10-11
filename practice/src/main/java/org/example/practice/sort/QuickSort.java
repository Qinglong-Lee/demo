package org.example.practice.sort;

import java.util.Arrays;

/**
 * 从数列中挑出一个基准值(通常是最左边的值)
 * 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)
 *      用两个指针分别指向【待比较的值的左右边界】
 *      先【从右向左找到第一个小于等于基准的值】和基准值交换，然后左指针右移
 *      再【从左向右找到第一个大于等于基准的值】和基准值交换，然后右指针左移
 *      这样就能不断将基准和【小于基准或者大于基准的值】交换
 * 一次处理后，该基准就处于数列的中间位置，左变都小于等于基准，右边都大于等于基准
 * 递归地把【基准值前面的子数列】和【基准值后面的子数列】进行排序
 * 当待排序数组的起始下标【大于等于】结束下标时递归结束
 *
 */
public class QuickSort {
    public void quickSort(int[] nums) {
        handle(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
    public void handle(int[] nums, int s, int e) {
        if (s >= e) return;

        int l = s, r = e;
        int basic = nums[l];

        while (l < r) {
            while (nums[r] > basic) r--;
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
            }

            while (nums[l] < basic) l++;
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                r--;
            }
        }

        handle(nums, s, l - 1);
        handle(nums, l + 1, e);
    }

    public static void main(String[] args) {
//        int[] nums = new int[] {30,40,60,10,20,50};
        int[] nums = new int[]{3, 2, 7, 1, 2, 4, 5, 5, 6};
        QuickSort test = new QuickSort();
        test.quickSort(nums);
    }
}
