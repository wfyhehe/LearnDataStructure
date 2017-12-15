package com.flyingwang.utils;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class Collections {
    public static void main(String[] args) {

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


    private static <E> void permutation(
            List<List<E>> elementsContainer, List<E> elements, int startIndex) {
        if (startIndex == elements.size()) {
            elementsContainer.add(new ArrayList<>(elements));
        }

        for (int i = startIndex; i < elements.size(); i++) { // elements[i] makes the first position
            // if startIndex=0, this traverse all conditions
            // case: elements[i] is first, swap first
            java.util.Collections.swap(elements, startIndex, i);
            permutation(elementsContainer, elements, startIndex + 1);
            java.util.Collections.swap(elements, startIndex, i);
        }
    }

    public static <E> List<List<E>> generatePermutation(List<E> elements) {
        List<List<E>> elementsContainer = new ArrayList<>();
        permutation(elementsContainer, elements, 0);
        return elementsContainer;
    }

    private static <E> E candidate(List<E> elements, int start) {
        int count = 1;
        E first = elements.get(start);
        int i = start + 1;
        for (; i < elements.size(); i++) {
            if (elements.get(i).equals(first)) {
                count++;
            } else {
                count--;
                if (count <= 0) break;
            }
        }
        if (i >= elements.size() - 1) {
            return first;
        }
        return candidate(elements, i + 1);
    }

    public static <E> E findMajority(List<E> elements) {  // frequency > floor(n/2)
        int count = 0;
        E candidate = Collections.candidate(elements, 0);
        for (E element : elements) {
            if (element.equals(candidate)) count++;
        }
        if (count > elements.size() / 2) {
            return candidate;
        }
        return null;
    }

    public static <E extends Comparable<? super E>> Map<String, E> findMinMax(List<E> elements) {
        return Collections.findMinMax(elements, 0, elements.size());
    }

    private static <E extends Comparable<? super E>> Map<String, E> findMinMax(
            List<E> elements, int lo, int hi) {
        if (hi - lo == 1) {
            Map<String, E> innerMap = new HashMap<>();
            innerMap.put("min", elements.get(lo));
            innerMap.put("max", elements.get(lo));
            return innerMap;
        } else if (hi - lo == 2) {
            E first = elements.get(lo);
            E second = elements.get(lo + 1);
            Map<String, E> innerMap = new HashMap<>();
            innerMap.put("min", first.compareTo(second) > 0 ? second : first);
            innerMap.put("max", first.compareTo(second) > 0 ? first : second);
            return innerMap;
        }
        int mi = (hi + lo) / 2;
        Map<String, E> retMap = new HashMap<String, E>();
        Map<String, E> innerMap1 = findMinMax(elements, lo, mi);
        E min1 = innerMap1.get("min");
        E max1 = innerMap1.get("max");
        Map<String, E> innerMap2 = findMinMax(elements, mi, hi);
        E min2 = innerMap2.get("min");
        E max2 = innerMap2.get("max");
        retMap.put("min", min1.compareTo(min2) < 0 ? min1 : min2);
        retMap.put("max", max1.compareTo(max2) > 0 ? max1 : max2);
        return retMap;
    }
}
