package com.flyingwang.utils;

import com.flyingwang.collections.ItemInPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static int matrixChainMultiplication(
            List<Map.Entry<Integer, Integer>> matrixPairs) {
        int size = matrixPairs.size();
        int[][] countTable = new int[size][size];
        for (int i = 0; i < size; i++) {
            countTable[i][i] = 0;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j + i < size; j++) {
                if (i == 0) {
                    countTable[j][j] = 0;
                    continue;
                }
                List<Integer> tempList = new ArrayList<>();
                for (int k = j; k < j + i; k++) {
                    tempList.add(countTable[j][k] +
                            countTable[k + 1][j + i] +
                            matrixPairs.get(j).getKey() *
                                    matrixPairs.get(k).getValue() *
                                    matrixPairs.get(j + i).getValue());
                }
                countTable[j][j + i] = java.util.Collections.min(tempList);
            }
        }
        return countTable[0][size - 1];
    }

    public static List<List<Integer>> zeroOnePack(List<ItemInPack> items, int packSize) {
        int itemsCount = items.size();
        List<List<Integer>> valueTable = new ArrayList<>(itemsCount);
        for (int i = 0; i < itemsCount; i++) {
            valueTable.add(new ArrayList<>(packSize));
            for (int currentSize = 0; currentSize <= packSize; currentSize++) {
                ItemInPack thisItem = items.get(i);
                ItemInPack lastItem = i > 0 ? items.get(i - 1) : null;
                if (lastItem == null) {
                    if (currentSize >= thisItem.getCost()) {
                        valueTable.get(i).add(thisItem.getValue());
                    } else {
                        valueTable.get(i).add(0);
                    }
                    continue;
                }
                int max;
                if (currentSize < thisItem.getCost()) {
                    max = valueTable.get(i - 1).get(currentSize);
                } else {
                    int valueWithoutThis = valueTable.get(i - 1).get(currentSize); // no need items[i]
                    int valueWithThis = valueTable.get(i - 1).get(currentSize - thisItem.getCost())
                            + thisItem.getValue();
                    if (valueWithoutThis > valueWithThis) {
                        max = valueWithoutThis;
                    } else {
                        max = valueWithThis;
                    }
                }
                valueTable.get(i).add(max);
            }
        }
        return valueTable;
    }

    public static List<List<Integer>> completePack(List<ItemInPack> items, int packSize) {
        int itemsCount = items.size();
        List<List<Integer>> valueTable = new ArrayList<>(itemsCount);
        for (int i = 0; i < itemsCount; i++) {
            valueTable.add(new ArrayList<>(packSize));
            for (int currentSize = 0; currentSize <= packSize; currentSize++) {
                ItemInPack thisItem = items.get(i);
                ItemInPack lastItem = i > 0 ? items.get(i - 1) : null;
                if (lastItem == null) {
                    if (currentSize >= thisItem.getCost()) {
                        valueTable.get(i).add(
                                valueTable.get(i).get(currentSize - thisItem.getCost()) + thisItem.getValue()
                        );
                    } else {
                        valueTable.get(i).add(0);
                    }
                    continue;
                }
                int max;
                if (currentSize < thisItem.getCost()) {
                    max = valueTable.get(i - 1).get(currentSize);
                } else {
                    int valueWithoutThis = valueTable.get(i - 1).get(currentSize); // no need items[i]
                    int valueWithThis = valueTable.get(i).get(currentSize - thisItem.getCost())
                            + thisItem.getValue();
                    if (valueWithoutThis > valueWithThis) {
                        max = valueWithoutThis;
                    } else {
                        max = valueWithThis;
                    }
                }
                valueTable.get(i).add(max);
            }
        }
        return valueTable;
    }
}
