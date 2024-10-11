package org.example.practice.leetcode.stringConstruct383;

/**
 * 判断一个字符串 str1 中的字符是否可以用于组成另一个字符串 str2
 * 这里【组成】的意思是从 str1 中抽取出来给到 str2，抽取一个 str1 就少一个
 *
 * 思路：【利用 char 和 int 可以互转的特性，直接将 char 作为数组下标，计数值作为数组元素】
 * String 类型本质上就是【字节数组】，可以直接 toCharArray 转换为【字符数组】
 * 将每个字符和字符出现次数映射为 HashMap，方便快速定位字符以及判断字符个数是否足够
 * 但是不一定需要 HashMap 来做映射，HashMap 底层也就是基于数组，只不过把 hash(key) 作为了数组下标
 * 现在的 key 是 char 类型，而 char 类型本身就可以转为 int，int 就能做数组下标
 * 为了使数组下标从 0 开始，用 char - ‘a’ 作为下标
 * HashMap可以自动扩容，数组不行，所以还是需要事先定好长度，最多就 26 个字母
 */
public class StringConstruct2 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote .length() > magazine.length()) return false;

        char[] ransomBodeArr = ransomNote.toCharArray();
        char[] magazineArr = magazine.toCharArray();
        int[] charCnt = new int[26];

        for (char c : magazineArr) {
            charCnt[c - 'a']++;
        }

        for (char c : ransomBodeArr) {
            if (charCnt[c - 'a']-- == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa", magazine = "aab";
        StringConstruct2 test = new StringConstruct2();
        System.out.println(test.canConstruct(ransomNote, magazine));
    }
}
