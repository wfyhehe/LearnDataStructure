package com.flyingwang.utils;

import java.util.*;

/**
 * Created by Administrator on 2017/12/17, good luck.
 */
public class DynamicProgramming {
    public static CharSequence longestCommonSubSequence(CharSequence str1, CharSequence str2) {
        int[][] countTable = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
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
        int i = str1.length();
        int j = str2.length();
        while (i > 0 && j > 0) {
            if (countTable[i - 1][j] == countTable[i][j]) {
                i--;
            } else if (countTable[i][j - 1] == countTable[i][j]) {
                j--;
            } else {
                ret.insert(0, str1.charAt(i - 1));
                i--;
                j--;
            }
        }
        return ret;
    }

    public static int editDistance(CharSequence str1, CharSequence str2) {
        int[][] countTable = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    countTable[i][j] = j;
                } else if (j == 0) {
                    countTable[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    countTable[i][j] = countTable[i - 1][j - 1];
                } else {
                    countTable[i][j] = Math.min(Math.min(
                            countTable[i - 1][j] + 1,
                            countTable[i][j - 1] + 1),
                            countTable[i - 1][j - 1] + 1
                    );
                }
            }
        }
        for (int[] row : countTable) {
            for (int j = 0; j < countTable[0].length; j++) {
                System.out.printf("%d ", row[j]);
            }
            System.out.println();
        }
        return countTable[str1.length()][str2.length()];
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
        List<Integer> valueList = new ArrayList<>(items.size()); // 存放总价值的一位数组
        List<Map<ItemInPack, Integer>> selectedItems = new ArrayList<>(); // 存放选取的物品及数量
        for (int i = 0; i <= packSize; i++) {
            valueList.add(0);
            selectedItems.add(new HashMap<>());
        }
        for (int i = 0; i < items.size(); i++) {
            for (int currentSize = 0; currentSize <= packSize; currentSize++) {
                ItemInPack thisItem = items.get(i);
                ItemInPack lastItem = i > 0 ? items.get(i - 1) : null;
                if (lastItem == null) { // 第一行，仅有一种物品（item[0]）
                    if (currentSize >= thisItem.getCost()) { // 当前容量可以装下至少一个item[0]
                        valueList.set(currentSize, // valueList(当前容量 - 1个item[0]的容量) + item[0]价值
                                valueList.get(currentSize - thisItem.getCost()) + thisItem.getValue()
                        );
                        Integer thisCount = selectedItems.get(currentSize).get(thisItem);
                        if (thisCount == null) {
                            thisCount = 0;
                        }
                        selectedItems.get(currentSize).put(thisItem, thisCount + 1);
                    } else {
                        valueList.set(currentSize, 0); // 不足以装下一个物品1, value设为0
                    }
                    continue;
                }
                if (currentSize >= thisItem.getCost()) { // 第n行（n>1)
                    int valueWithoutThis = valueList.get(currentSize); // 上一次到该行时的价值（不加item[i]）
                    int valueWithThis = valueList.get(currentSize - thisItem.getCost())
                            + thisItem.getValue(); // valueList(当前容量 - 1个item[i]的容量) + item[i]价值
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

    public static int maxIntervalSum(List<Integer> numbers) {
        int currentSum = 0;
        int historyMaxSum = 0;
        boolean hasPositive = false;
        int maxNumber = numbers.get(0);
        for (Integer number : numbers) {
            hasPositive = number > 0 || hasPositive;
            maxNumber = Math.max(maxNumber, number);
            currentSum = Math.max(currentSum + number, 0);
            historyMaxSum = Math.max(currentSum, historyMaxSum);
        }
        if (!hasPositive) {
            return maxNumber;
        }
        return historyMaxSum;
    }

    public static List<Integer> subListOfMaxIntervalSum(List<Integer> numbers) {
        int currentSum = 0;
        int historyMaxSum = 0;
        int lo = 0;
        int hi = 0;
        boolean hasPositive = false;
        int maxNumberIndex = 0; // 若所有数字均小于0，最大子段和即为最大的数
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            hasPositive = number > 0 || hasPositive;
            if (number > numbers.get(maxNumberIndex)) {
                maxNumberIndex = i;
            }
            // 若当前数字加入求和后结果为负，说明最大子段和必然舍弃这部分，currentSum归零
            if (currentSum + number > 0) {
                currentSum = currentSum + number;
            } else {
                currentSum = 0;
                lo = i + 1;
            }
            // 保存历史最大子段和
            if (currentSum > historyMaxSum) {
                historyMaxSum = currentSum;
                hi = i + 1;
            }
        }
        if (!hasPositive) {
            return numbers.subList(maxNumberIndex, maxNumberIndex + 1);
        }
        return numbers.subList(lo, hi);
    }
}
