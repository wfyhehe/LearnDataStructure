package com.flyingwang.utils;

import java.util.ArrayList;
import java.util.List;

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


}

