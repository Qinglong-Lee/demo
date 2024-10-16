package org.example.practice.leetcode.searchMatrix74;

/**
 * 在【非严格递增】的【二维数组】中查找
 * 【非严格递增】指可能相等
 * 每行最后一位小于下一行的第一位
 *
 * 思路：【两次二分查找】
 * 第一次对【最后一列】做二分查找，找到所在的行
 * 第二次对【所在行】做二分查找
 */
public class SearchMatrix1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int t = 0, b = m - 1, line = -1;
        while (t <= b) {
            int mid = (t + b) >> 1;
            if (target == matrix[mid][n - 1]) {
                line = mid;
                break;
            }
            if (target > matrix[mid][n - 1]) {
                t = mid + 1;
            }
            if (target < matrix[mid][n - 1]) {
                b = mid - 1;
            }
        }

        if ((line = line < 0 ? t : line) == m) return false;

        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (target == matrix[line][mid]) {
                return true;
            }
            if (target > matrix[line][mid]) {
                l = mid + 1;
            }
            if (target < matrix[line][mid]) {
                r = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        SearchMatrix1 test = new SearchMatrix1();
        System.out.println(test.searchMatrix(matrix, target));
    }
}
