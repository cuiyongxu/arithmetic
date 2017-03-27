package org.openote.KMP;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class KMP {

    //abcabcdef
    public static int[] preProcess(char[] childStr) {
        int size = childStr.length;
        int[] ints = new int[size];
        int j = 0;
        for (int i = 1; i < size; i++) {
            char currentValue = childStr[j];
            char nextValue = childStr[i];
            log.info(" currentValue :{} , nextValue :{} ", currentValue, nextValue);
            while (j > 0 && currentValue != nextValue) {
                j = ints[j];
            }
            if (currentValue == nextValue) {
                log.info("find equal value, abolish {}", currentValue);
                j++;
            }
            ints[i] = j;
        }
        return ints;
    }


    public static int kmp(String parentStr, String childStr) {
        int parSize = parentStr.length();
        int subSize = childStr.length();


        char[] parentChar = parentStr.toCharArray();
        char[] childChar = childStr.toCharArray();

        int[] ints = preProcess(childChar);

        log.info("P 的列表如下： {}", ints);
        int j = 0;
        int k = 0;
        int coordinate = 0;
        for (int i = 0; i < parSize; i++) {
            while (j > 0 && childChar[j] != parentChar[i]) {
                j = ints[j - 1];
            }
            if (childChar[j] == parentChar[i]) {
                j++;
            }
            if (j == subSize) {
                j = ints[j - 1];
                k++;
                coordinate = i - subSize + 1;
                log.info("Find subString {} at {}", childStr, coordinate);
            }
        }
        return coordinate;

    }
}
