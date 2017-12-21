package com.flyingwang.utils;

import com.flyingwang.collections.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, E> retMap = new HashMap<>();
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

    private static <E extends Comparable<? super E>> int getMidIndexOfThree(
            List<E> elements, int i1, int i2, int i3) {
        E e1 = elements.get(i1);
        E e2 = elements.get(i2);
        E e3 = elements.get(i3);
        if (e1.compareTo(e2) <= 0 && e2.compareTo(e3) <= 0
                || e3.compareTo(e2) <= 0 && e2.compareTo(e1) <= 0) {
            return i2;
        } else if (e2.compareTo(e1) <= 0 && e1.compareTo(e3) <= 0
                || e3.compareTo(e1) <= 0 && e1.compareTo(e2) <= 0) {
            return i1;
        } else if (e2.compareTo(e3) <= 0 && e3.compareTo(e1) <= 0
                || e1.compareTo(e3) <= 0 && e3.compareTo(e2) <= 0) {
            return i3;
        } else { // should never happens
            throw new IllegalArgumentException();
        }
    }

    private static <E extends Comparable<? super E>> int partition(
            List<E> elements, int lo, int hi) {
        int mi = (lo + hi) / 2;
        int midIndex = getMidIndexOfThree(elements, lo, mi, hi - 1);
        int indexToInsert = lo + 1;
        java.util.Collections.swap(elements, lo, midIndex);
        for (int i = lo; i < hi; i++) {
            if (elements.get(i).compareTo(elements.get(lo)) < 0) {
                java.util.Collections.swap(elements, i, indexToInsert);
                indexToInsert++;
            }
        }
        indexToInsert--;
        java.util.Collections.swap(elements, lo, indexToInsert);
        return indexToInsert;
    }

    public static <E extends Comparable<? super E>> E findKthSmallest(List<E> elements, int k) {
        return findKthSmallest(elements, 0, elements.size(), k);
    }

    private static <E extends Comparable<? super E>> E findKthSmallest(
            List<E> elements, int lo, int hi, int k) {
        int pivotIndex = partition(elements, lo, hi);
        // elements[pivotIndex] is pivotIndex+1_th smallest element
        if (pivotIndex + 1 == k) {
            return elements.get(pivotIndex);
        } else if (pivotIndex + 1 < k) { // kth smallest element is after pivot
            return findKthSmallest(elements, pivotIndex + 1, hi, k);
        } else { // kth smallest element is before pivot
            return findKthSmallest(elements, lo, pivotIndex, k);
        }
    }

    public static <E> List<List<Integer>> floyd(Graph<E, E> graph) {
        List<List<Integer>> ret = new ArrayList<>(graph.size());
        for (int i = 0; i < graph.size(); i++) {
            ret.add(new ArrayList<>(graph.size()));
            for (int j = 0; j < graph.size(); j++) {
                Graph.Edge<E> edge = graph.getEdge(i, j);
                if (edge != null) {
                    ret.get(i).add(graph.getEdge(i, j).getWeight());
                } else {
                    ret.get(i).add(null);
                }
            }
        }
        for (int k = 0; k < graph.size(); k++) {
            for (int i = 0; i < graph.size(); i++) {
                for (int j = 0; j < graph.size(); j++) {
                    Integer originEdge = ret.get(i).get(j);
                    Integer newEdgePart1 = ret.get(i).get(k);
                    Integer newEdgePart2 = ret.get(k).get(j);
                    if (newEdgePart1 == null || newEdgePart2 == null) {
                        continue;
                    }
                    if (originEdge == null) {
                        ret.get(i).set(j, newEdgePart1 + newEdgePart2);
                    } else {
                        ret.get(i).set(j, Math.min(originEdge, newEdgePart1 + newEdgePart2));
                    }
                }
            }
        }
        return ret;
    }
}
