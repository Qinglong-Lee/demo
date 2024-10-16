package org.example.practice.leetcode.insertToSortedArr35;

/**
 * 插入指定值到一个有序数组，已存在则返回索引，否则返回插入位置索引
 *
 * 思路：【循环双指针二分查找】
 * 两个指针指向【待查找数组】的头和尾
 * 取【中位数】做比较
 * 等于则直接返回中位索引，否则根据比较结果移动头尾指针
 * 循环直到左指针大于右指针
 * 跳出循环说明不存在
 * 在跳出之前 l = r，即 mid = l，l 已经根据 targer 和 nums[l] 的大小做过调整
 * l 就是最终位置
 **/
public class InsertToSortedArr2 {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
//            利用位移操作获取中位数
            int mid = (l + r) >> 1;

            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                l = mid + 1;
            }
            if (target < nums[mid]) {
                r = mid - 1;
            }
        }

        return l;
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

        InsertToSortedArr2 test = new InsertToSortedArr2();
        System.out.println(test.searchInsert(nums, target));
    }
}
