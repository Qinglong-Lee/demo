package org.example.practice;

import java.util.Arrays;

public class SortBubble {
    public static void main(String[] args) {
        int[] data = new int[]{4, 7, 2, 6, 8};

        int size = data.length;
        int last = size - 1;
        int lastSwitch = last;
        int count = 0;

        for (int i = 0; i < size - 1; i++/*, last--*/) {
            boolean switched = false;
            int flag = 0;
            for (int j = 0; j < /*last*/lastSwitch; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                    switched = true;
                    flag = j;
                }
                count++;
            }
            if (!switched) {
                break;
            } else {
                lastSwitch = flag;
            }
        }
        System.out.println(count);
        System.out.println(Arrays.toString(data));
    }
}
