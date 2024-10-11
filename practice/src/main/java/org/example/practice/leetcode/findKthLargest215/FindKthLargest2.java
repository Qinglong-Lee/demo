package org.example.practice.leetcode.findKthLargest215;

/**
 * 找出数组中第 k 大的数，前提：-10^4 <= nums[i] <= 10^4
 * 思路：【桶排序】
 *
 * 定义一个数组作为【桶】
 * 待排序数组中的【值作为桶的下标】，【相同值的数量作为桶的值】
 * 由于数组本身的有序性，值越大数组下标越大，遍历数组就能得到有序的结果
 * 桶排序有个局限：待排序数组中的值大小必须有限，因为桶的大小需要提前确定
 */
public class FindKthLargest2 {
    public int findKthLargest(int[] nums, int k) {
        int[] bucket = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i] + 10000]++;
        }

        for (int i = 20000; i >= 0; i--) {
            if((k -= bucket[i]) <= 0) {
                return i - 10000;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;

        FindKthLargest2 test = new FindKthLargest2();
        System.out.println(test.findKthLargest(nums, k));
    }
}
