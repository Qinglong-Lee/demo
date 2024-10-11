package org.example.practice.leetcode.findKthLargest215;

/**
 * 找出数组中第 k 大的数
 * 思路：【快速排序改进算法】
 *
 * 【快速排序】的思路：
 *      选定【左一】为【基准】
 *      不断将【大/小于的值】和基准交换，直到【基准左侧都更小，右侧都更大】
 *      递归左侧数组和右侧数组
 *  可见【快拍】每次排序都能确定一个【基准位】
 *  只需判定【基准位为倒数第 k】，就找到了第 k 大的数
 *  而且【快排】是会递归左右两侧的，而此题只需要找到【某个位置的数】，无需真正排序
 *  因此每次通过【基准位】和【第 k 大的数索引】的大小关系，来判断继续递归左侧还是右侧，无需都递归
 *  当得到结果后也立即返回，不再继续递归排序
 */
public class FindKthLargest3 {
    int k, length, result = 0;

    public void quickSortImprovedVersion(int[] nums) {
        handle(nums, 0, nums.length - 1);
//        System.out.println(Arrays.toString(nums));
    }

    public void handle(int[] nums, int s, int e) {
        if (s >= e) {
            result = nums[s];
            return;
        }

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

        if (k == length - l) {
            result = basic;
            return;
        }
        if (k > length - l) {
            handle(nums, s, l - 1);
        }
        if (k < length - l) {
            handle(nums, l + 1, e);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        this.k = k;
        this.length = nums.length;
        quickSortImprovedVersion(nums);
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
//        int k = 2;
//        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int k = 4;

        int[] nums = new int[]{1};
        int k = 1;
        FindKthLargest3 test = new FindKthLargest3();
        System.out.println(test.findKthLargest(nums, k));
    }
}
