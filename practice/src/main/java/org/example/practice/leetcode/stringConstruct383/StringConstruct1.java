package org.example.practice.leetcode.stringConstruct383;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断一个字符串 str1 中的字符是否可以用于组成另一个字符串 str2
 * 这里【组成】的意思是从 str1 中抽取出来给到 str2，抽取一个 str1 就少一个
 *
 * 思路：【HashMap 快速定位并记录字符个数】
 * String 类型本质上就是【字节数组】，可以直接 toCharArray 转换为【字符数组】
 * 将每个字符和字符出现次数映射为 HashMap，方便快速定位字符以及判断字符个数是否足够
 */
public class StringConstruct1 {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ransomBodeArr = ransomNote.toCharArray();
        char[] magazineArr = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>(magazineArr.length, 1f);

        for (char c : magazineArr
        ) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomBodeArr
        ) {
            if (map.getOrDefault(c, 0) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }

        return  true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa", magazine = "aab";
        StringConstruct1 test = new StringConstruct1();
        System.out.println(test.canConstruct(ransomNote, magazine));
    }
}
