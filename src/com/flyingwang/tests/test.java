package com.flyingwang.tests;

import com.flyingwang.utils.Collections;
import com.flyingwang.utils.DynamicProgramming;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class test {

    public static void main(String[] args) {
//        testRadixSort();
//        testPermutation();
//        testMajority();
//        testFindMinMax();
//        testFindKthSmallest();
//        testLCS();
        testMatrixChain();
    }

    public static void testRadixSort() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            integers.add(random.nextInt(89999) + 10000); // 10000 ~ 99999
        }
        List<Integer> copyIntegers = new ArrayList<>(integers);
        java.util.Collections.sort(integers);
        Collections.radixSort(copyIntegers, 5);
        System.out.println(integers);
        System.out.println(copyIntegers);
    }

    public static void testPermutation() {
        Integer[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        long startTime = System.currentTimeMillis();
        List<List<Integer>> output = Collections.generatePermutation(Arrays.asList(elements));
        long endTime = System.currentTimeMillis();
        for (List<Integer> list : output) {
            System.out.println(list);
        }
        System.out.println(output.size());
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    public static void testMajority() {
        Integer[] elements = {1, 3, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(elements));
        System.out.println(Collections.findMajority(list));
    }

    public static void testFindMinMax() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            integers.add(random.nextInt(89999) + 10000); // 10000 ~ 99999
        }
        Map<String, Integer> map = Collections.findMinMax(integers);
        System.out.println(map);
    }

    public static void testFindKthSmallest() {
        Integer[] elements = {9, 7, 8, 1, 6, 4, 2, 3, 5, 0};
        List<Integer> list = Arrays.asList(elements);
        Integer target = Collections.findKthSmallest(list, 2);
        System.out.println(target);
    }

    public static void testLCS() {
        String str1 = "xyxxzxyzxy";
        String str2 = "zxzyyzxxyxxz";
        CharSequence commonStr = DynamicProgramming.longestCommonSubSequence(str1, str2);
        System.out.println(commonStr);
    }

    public static void testMatrixChain() {
        List<Map.Entry<Integer, Integer>> matrixList = new ArrayList<>();
        matrixList.add(new AbstractMap.SimpleEntry<>(5, 10));
        matrixList.add(new AbstractMap.SimpleEntry<>(10, 4));
        matrixList.add(new AbstractMap.SimpleEntry<>(4, 6));
        matrixList.add(new AbstractMap.SimpleEntry<>(6, 10));
        matrixList.add(new AbstractMap.SimpleEntry<>(10, 2));
        System.out.println(DynamicProgramming.matrixChainMultiplication(matrixList));
    }
}
