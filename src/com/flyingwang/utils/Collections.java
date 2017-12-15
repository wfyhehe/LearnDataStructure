package com.flyingwang.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class Collections {
    public static void main(String[] args) {
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

    public static void radixSort(List<Integer> list, int length) {
        ArrayList<Integer>[] buckets = new ArrayList[10];


        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 10; j++) {
                buckets[j] = new ArrayList<>();
            }
            for (Integer integer : list) {
                int currentNumber = Integer.parseInt(
                        integer.toString().substring(length - i - 1, length - i));
                buckets[currentNumber].add(integer);
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer integer : buckets[j]) {
                    list.set(index, integer);
                    index++;
                }
            }
        }
    }
}

