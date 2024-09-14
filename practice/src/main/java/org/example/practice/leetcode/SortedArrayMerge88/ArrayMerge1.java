package org.example.practice.leetcode.SortedArrayMerge88;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 合并两个指定长度的有序数组到第一个数组使合并后的数组也是有序的
 *
 * 思路：【先拼接数组再利用 PriorityQueue 出栈有序的特性】
 */
public class ArrayMerge1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        List<Integer> list1 = new ArrayList<>(List.of(ArrayUtils.toObject(nums1))).subList(0, m);
//        List<Integer> list2 = new ArrayList<>(List.of(ArrayUtils.toObject(nums2))).subList(0, n);
        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList()).subList(0, m);
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList()).subList(0, n);

        list1.addAll(list2);
        PriorityQueue<Integer> queue = new PriorityQueue<>(list1);

        int i = 0;
        do {
            nums1[i++] = queue.poll();
        } while (queue.size() > 0);
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}, nums2 = new int[]{2, 5, 6};
//        int m = 3, n = 3;

//        int[] nums1 = new int[]{1}, nums2 = new int[]{};
//        int m = 1, n = 0;

        int[] nums1 = new int[]{0}, nums2 = new int[]{1};
        int m = 0, n = 1;

        ArrayMerge1 arrayMerge = new ArrayMerge1();
        arrayMerge.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }
}
