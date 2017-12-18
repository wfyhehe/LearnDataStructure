package com.flyingwang.utils;

/**
 * Created by Administrator on 2017/12/17, good luck.
 */
public class DynamicProgramming {
    public static void main(String[] args) {

    }

    public static CharSequence longestCommonSubSequence(CharSequence str1, CharSequence str2) {
        int[][] countTable = new int[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (str1.charAt(i) == str2.charAt(j)) {
                    countTable[i][j] = countTable[i - 1][j - 1] + 1;
                } else {
                    countTable[i][j] = Math.max(countTable[i - 1][j], countTable[i][j - 1]);
                }
            }
        }
        for (int[] row : countTable) {
            for (int j = 0; j < countTable[0].length; j++) {
                System.out.print(row[j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        StringBuilder ret = new StringBuilder();
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        while (i >= 0 && j >= 0) {
            if (str1.charAt(i) == str2.charAt(j)) {
                ret.insert(0, str1.charAt(i));
                i--;
                j--;
            } else {
                if (i == 0) {
                    j--;
                    continue;
                }
                if (j == 0) {
                    i--;
                    continue;
                }
                if (countTable[i - 1][j] >= countTable[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return ret;
    }
}
