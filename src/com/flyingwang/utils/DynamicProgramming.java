package com.flyingwang.utils;

import java.util.*;

/**
 * Created by Administrator on 2017/12/17, good luck.
 */
public class DynamicProgramming {
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

    public static List<Integer> zeroOnePack(List<ItemInPack> items, int packSize) {
        List<Integer> valueList = new ArrayList<>(items.size());
        List<Set<ItemInPack>> selectedItems = new ArrayList<>();
        for (int i = 0; i <= packSize; i++) {
            valueList.add(0);
            selectedItems.add(new HashSet<>());
        }
        for (int i = 0; i < items.size(); i++) {
            for (int currentSize = packSize; currentSize >= 0; currentSize--) {
                ItemInPack thisItem = items.get(i);
                ItemInPack lastItem = i > 0 ? items.get(i - 1) : null;
                if (lastItem == null) {
                    if (currentSize >= thisItem.getCost()) {
                        valueList.set(currentSize, thisItem.getValue());
                        selectedItems.get(currentSize).add(thisItem);
                    }
                    continue;
                }
                if (currentSize >= thisItem.getCost()) {
                    int valueWithoutThis = valueList.get(currentSize); // no need items[i]
                    int valueWithThis = valueList.get(currentSize - thisItem.getCost())
                            + thisItem.getValue();
                    if (valueWithoutThis < valueWithThis) {
                        valueList.set(currentSize, valueWithThis);
                        Set<ItemInPack> setWithoutThis =
                                selectedItems.get(currentSize - thisItem.getCost());
                        Set<ItemInPack> newSet = new HashSet<>(setWithoutThis);
                        newSet.add(thisItem);
                        selectedItems.set(currentSize, newSet);
                    }
                }
            }
        }
        for (Set<ItemInPack> selectedItem : selectedItems) {
            System.out.println(selectedItem);
        }
        return valueList;
    }

    public static List<Integer> completePack(List<ItemInPack> items, int packSize) {
        List<Integer> valueList = new ArrayList<>(items.size());
        List<Map<ItemInPack, Integer>> selectedItems = new ArrayList<>();
        for (int i = 0; i <= packSize; i++) {
            valueList.add(0);
            selectedItems.add(new HashMap<>());
        }
        for (int i = 0; i < items.size(); i++) {
            for (int currentSize = 0; currentSize <= packSize; currentSize++) {
                ItemInPack thisItem = items.get(i);
                ItemInPack lastItem = i > 0 ? items.get(i - 1) : null;
                if (lastItem == null) {
                    if (currentSize >= thisItem.getCost()) {
                        valueList.set(currentSize,
                                valueList.get(currentSize - thisItem.getCost()) + thisItem.getValue()
                        );
                        Integer thisCount = selectedItems.get(currentSize).get(thisItem);
                        if (thisCount == null) {
                            thisCount = 0;
                        }
                        selectedItems.get(currentSize).put(thisItem, thisCount + 1);
                    } else {
                        valueList.set(currentSize, 0);
                    }
                    continue;
                }
                if (currentSize >= thisItem.getCost()) {
                    int valueWithoutThis = valueList.get(currentSize); // no need items[i]
                    int valueWithThis = valueList.get(currentSize - thisItem.getCost())
                            + thisItem.getValue();
                    if (valueWithoutThis < valueWithThis) {
                        valueList.set(currentSize, valueWithThis);
                        Map<ItemInPack, Integer> mapWithoutThis =
                                selectedItems.get(currentSize - thisItem.getCost());
                        Integer thisCount = mapWithoutThis.get(thisItem);
                        if (thisCount == null) {
                            thisCount = 0;
                        }
                        Map<ItemInPack, Integer> newMap = new HashMap<>();
                        mapWithoutThis.putAll(newMap);
                        selectedItems.set(currentSize, newMap);
                        selectedItems.get(currentSize).put(thisItem, thisCount + 1);
                    }
                }
            }
        }
        for (Map<ItemInPack, Integer> selectedItem : selectedItems) {
            System.out.println(selectedItem);
        }
        return valueList;
    }
}
