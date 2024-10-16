package org.example.practice.leetcode.searchMatrix74;

/**
 * 在【非严格递增】的【二维数组】中查找
 * 【非严格递增】指可能相等
 * 每行最后一位小于下一行的第一位
 *
 * 思路：【二维数组转一维数组 + 二分查找】
 * 第由于第三个性质，可将二维数组每行首位相连，从而得到一个【递增的一维数组】
 * 对一位数组做二分查找
 * 查找过程需要将一维数组的下标 x 映射到二维数组下标 (i, j)
 *  i = x/n, j = x%n
 *  其中 n 是二维数组列数
 */
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = m * n - 1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            int line = mid / n, column = mid % n;
            if (target == matrix[line][column]) {
                return true;
            }
            if (target > matrix[line][column]) {
                l = mid + 1;
            }
            if (target < matrix[line][column]) {
                r = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
//        int target = 3;
//        int[][] matrix = new int[][]{{1, 1}};
//        int target = 2;
        int[][] matrix = new int[][]{{1}};
        int target = 0;

        SearchMatrix2 test = new SearchMatrix2();
        System.out.println(test.searchMatrix(matrix, target));
    }
}
