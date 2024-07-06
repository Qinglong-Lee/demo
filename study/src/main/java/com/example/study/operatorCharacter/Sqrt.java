package com.example.study.operatorCharacter;

import java.math.BigInteger;

/**
 * 测试带符号位移 >>和不带符号位移 >>>
 * 带符号位移是把被位移的对象当做一个有符号的整数，符号位占了一位，对于 int，边界就是 2^31
 * 不带符号位移是把被位移的对象当做一个无符号整数，即没有符号位了，但是对于 int，位数还是 32，边界就变成了 2^32
 * 因此不带符号位移的溢出边界要大很多
 */
public class Sqrt {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(2147483647));
        System.out.println(Integer.toBinaryString(2147483647 + 1));
//        System.out.println(Integer.parseInt(Integer.toBinaryString(2147483647 + 1), 2));
        System.out.println(new BigInteger(Integer.toBinaryString(2147483647 + 1), 2));
        int a = 2147483647 + 1;
        System.out.println(a);
        System.out.println(a >> 1);
        System.out.println(a >>> 1);

//        mySqrt(2147483647 );
    }

    /**
     * 此函数用于求一个整数的平方根，向下取整
     * left + right + 1 >>> 1 这个计算中
     * left + right + 1 是 int 类型，如果把它当做一个带符号整数来做位移运算：left + right + 1 >> 1
     * left + right + 1 的边界就是 2^31，一旦操作编辑则溢出导致计算错误
     * 但是如果把它当做一个不带带符号整数来做位移运算，边界就变成了 2^32，而 left + right + 1 不可能超过这个边界，所以一定不会溢出
     */
    public static int mySqrt(int x) {
        int left = 0,
        right = x;

        while (left < right) {
//            left + right + 1 >> 1 当 x = 2147483647 会溢出
            int mid = left + right + 1 >>> 1;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }

}
