package org.example.practice.leetcode.maxCapacity11;

public class MaxCapacity2 {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            int left_height = height[left];
            int right_height = height[right];
            int capacity = (right - left) * Math.min(left_height, right_height);

            if (capacity > max) max = capacity;

            if (left_height < right_height) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
//        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = new int[]{1, 3, 2, 5, 25, 24, 5};
        MaxCapacity2 test = new MaxCapacity2();
        System.out.println(test.maxArea(height));
    }
}
