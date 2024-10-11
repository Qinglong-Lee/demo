package org.example.practice.leetcode.canCompleteCircuit134;

/**
 * 环形加油站
 * 给定每个加油站可提供的油量，和从当前加油站到下一个消耗的油量
 * 找出【唯一】一个能循环一周回到起始点的加油站
 *
 * 思路：【剩余量循环过滤】
 * 循环计算从任意起始点到达任意点后的剩余量，每次循环都增加起始点到终点距离
 * 每次循环过滤掉剩余量小于 0 的数据，只对大于 0 的进行下一步计算。因为只有大于 0 的路径才能继续前进
 * 直到回到起始点循环结束，找到唯一一个大于 0 的路径起始点
 *
 * 此算法在大数据量下性能不佳
 */
public class CanCompleteCircuit1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int rst = -1;
        int length = gas.length;
        int[] nearByRemain = new int[length];
        int[] anyRemain = new int[length];

        for (int i = 0; i < length; i++) {
            int remain = gas[i] - cost[i];
            nearByRemain[i] = remain;
            anyRemain[i] = remain;

            if (remain >= 0) rst = i;
        }

        for (int end_path_idx = 1; end_path_idx <= length; end_path_idx++) {
            rst = -1;
            for (int i = 0; i < length; i++) {
                int idx = i + end_path_idx > length - 1 ?
                        i + end_path_idx - length : i + end_path_idx;

                if (anyRemain[i] >= 0) {
                    anyRemain[i] = anyRemain[i] + nearByRemain[idx];
                    if (anyRemain[i] >= 0) rst = i;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
//        int[] gas = new int[]{1,2,3,4,5};
//        int[] cost = new int[]{3,4,5,1,2};
        int[] gas = new int[]{2};
        int[] cost = new int[]{2};



        CanCompleteCircuit1 test = new CanCompleteCircuit1();
        System.out.println(test.canCompleteCircuit(gas,cost));
    }
}
