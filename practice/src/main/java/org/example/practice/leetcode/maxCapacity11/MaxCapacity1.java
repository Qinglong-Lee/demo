package org.example.practice.leetcode.maxCapacity11;

import java.util.Map;

public class MaxCapacity1 {
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0, end = height.length - 1;
        int last_start_height = 0;

        while (start < end) {
            if (height[start] > last_start_height) {
                int last_end_height = 0;

                for (int i = end; i > start; i--) {
                    if (height[i] > last_end_height) {
                        int capacity = (i - start) * Math.min(height[i], height[start]);

                        if (capacity > max) max = capacity;

                        last_end_height = height[i];

                        if (height[i] >= height[start]) break;
                    }
                }
                last_start_height = height[start];
            }

            start++;
        }

        return max;
    }

    public static void main(String[] args) {
//        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = new int[]{1, 3, 2, 5, 25, 24, 5};
        MaxCapacity1 test = new MaxCapacity1();
        System.out.println(test.maxArea(height));
    }
}
