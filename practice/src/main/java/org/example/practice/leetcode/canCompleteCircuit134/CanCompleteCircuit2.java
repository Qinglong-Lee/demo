package org.example.practice.leetcode.canCompleteCircuit134;

/**
 * 环形加油站
 * 给定每个加油站可提供的油量，和从当前加油站到下一个消耗的油量
 * 找出【唯一】一个能【循环一周】回到起始点的加油站
 *
 * 思路：【数形结合，找出剩余量和加油站的规律】
 * 要循环一周，就得到达每个加油站时剩余量大于等于 0
 * gas - cost 不断累积就能计算出某个加油站到每个加油站的剩余量
 * 以加油站编号为横坐标，剩余量为纵坐标画出【不同起始点】的折线图
 * 从图可以发现，不管从哪个起始点开始，剩余量的最小值（min）对应的加油站编号始终为同一个，记为 x
 * 而如果循环一周后最终剩余量大于等于 0，只要以 x 为起始点，之后的剩余量一定都大于 min，而此时 min=0，即都大于 0
 * 之所以最小值唯一，是因为提干前提条件指明：只有【唯一】路径能循环一周。如果最小值不唯一，那么从多个加油点都能循环一周了
 */
public class CanCompleteCircuit2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int minRemain = Integer.MAX_VALUE;
        int min_idx = 0;
        int circuitRemain = 0;

        for (int i = 0; i < length; i++) {
            int remain = gas[i] - cost[i];
            circuitRemain += remain;

            if (circuitRemain < minRemain) {
                minRemain = circuitRemain;
                min_idx = i;
            }
        }

        return circuitRemain < 0 ? -1 : (min_idx + 1) % length;
    }

    public static void main(String[] args) {
//        int[] gas = new int[]{1,2,3,4,5};
//        int[] cost = new int[]{3,4,5,1,2};
//        int[] gas = new int[]{2};
//        int[] cost = new int[]{2};
        int[] gas = new int[]{3, 1, 1};
        int[] cost = new int[]{1, 2, 2};

        CanCompleteCircuit2 test = new CanCompleteCircuit2();
        System.out.println(test.canCompleteCircuit(gas, cost));
    }
}
