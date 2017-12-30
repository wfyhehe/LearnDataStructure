package com.flyingwang.utils;

import com.flyingwang.collections.CompleteHeap;
import com.flyingwang.collections.Graph;
import com.flyingwang.collections.UnionFindSet;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class Collections {
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

        for (int i = startIndex; i < elements.size(); i++) { // elements[i] makes the first
            // position
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

    public static <E> List<Integer> dijkstra(Graph<E, E> graph, int source) {
        Integer current = source;
        Set<Integer> group = new HashSet<>();
        Set<Integer> visitedGroup = new HashSet<>();
        List<Integer> ret = new ArrayList<>(graph.size());
        for (int i = 0; i < graph.size(); i++) {
            if (i == source) {
                group.add(i);
                ret.add(0);  // self to self is 0
                continue;
            }
            Graph.Edge<E> edge = graph.getEdge(source, i);
            if (edge != null) {
                ret.add(edge.getWeight());
            } else {
                ret.add(null);
            }
        }
        while (current != null) {
            group.remove(current);
            visitedGroup.add(current);
            for (int i = 0; i < graph.size(); i++) {
                if (graph.getEdge(current, i) != null && !visitedGroup.contains(i)) {
                    group.add(i);
                }
            }
            for (int i = 0; i < graph.size(); i++) {
                if (graph.getEdge(current, i) != null) {
                    if (ret.get(i) == null ||
                            ret.get(i) > ret.get(current) + graph.getEdge(current, i).getWeight()) {
                        ret.set(i, ret.get(current) + graph.getEdge(current, i).getWeight());
                    }
                }
            }
            Integer minDistance = null;
            current = null;
            for (int i = 0; i < ret.size(); i++) {
                Integer distance = ret.get(i);
                if (distance != null && distance != 0 && group.contains(i)) {
                    if (minDistance == null || distance < minDistance) {
                        minDistance = distance;
                        current = i;
                    }
                }
            }
        }
        return ret;
    }

    public static <E> List<Graph.Edge<E>> kruskal(Graph<E, E> graph) {
        List<Graph.Edge<E>> allEdges = graph.getFlattenedEdges();
        List<Graph.Edge<E>> selectedEdges = new ArrayList<>();
        List<Integer> vertexNumbers = new ArrayList<>(graph.size());
        for (int i = 0; i < graph.size(); i++) {
            vertexNumbers.add(i);
        }
        allEdges.sort(Comparator.comparingInt(Graph.Edge::getWeight));
        UnionFindSet<Integer> ufs = new UnionFindSet<>(vertexNumbers);
        for (Graph.Edge<E> edge : allEdges) {
            int from = edge.getFrom();
            int to = edge.getTo();
            if (!ufs.find(from).equals(ufs.find(to))) {
                ufs.union(from, to);
                selectedEdges.add(edge);
            }
        }
        return selectedEdges;
    }

    public static <E> List<Graph.Edge<E>> prim(Graph<E, E> graph) {
        Set<Integer> group = new HashSet<>();
        group.add(0);
        List<Graph.Edge<E>> selectedEdges = new ArrayList<>(graph.size());
        List<Graph.Edge<E>> allEdges = graph.getFlattenedEdges();
        while (group.size() < graph.size()) {
            Integer minDistance = null;
            Graph.Edge<E> temp = null;
            Integer indexOfEdgeToBeRemoved = null;
            for (int i = 0; i < allEdges.size(); i++) {
                Graph.Edge<E> edge = allEdges.get(i);
                Integer from = edge.getFrom();
                Integer to = edge.getTo();
                if (group.contains(from) && !group.contains(to)) {
                    if (minDistance == null || edge.getWeight() < minDistance) {
                        minDistance = edge.getWeight();
                        temp = edge;
                        indexOfEdgeToBeRemoved = i;
                    }
                }
            }
            if (indexOfEdgeToBeRemoved != null) {
                allEdges.remove(indexOfEdgeToBeRemoved.intValue());
            }
            if (temp != null) {
                selectedEdges.add(temp);
                group.add(temp.getFrom());
                group.add(temp.getTo());
            }
        }
        return selectedEdges;
    }

    private static int[] getKmpNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        int t = -1;
        next[0] = -1;
        while (j < pattern.length() - 1) {
            if (t < 0 || pattern.charAt(j) == pattern.charAt(t)) {
                j++;
                t++;
                next[j] = pattern.charAt(j) != pattern.charAt(t) ? t : next[t];
            } else {
                t = next[t];
            }
        }
        return next;
    }

    public static boolean isSubstring(String pattern, String text) {
        int[] next = getKmpNext(pattern);
        int i = 0;
        int j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (j < 0 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        return j == pattern.length();
    }

    public static <E> List<Integer> topologicalSort(Graph<E, E> g) throws CloneNotSupportedException {
        Graph<E, E> graph = g.clone();
        List<Integer> ret = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>(); // 入度=0的顶点
        List<List<Graph.Edge<E>>> edges = new ArrayList<>(graph.getEdges());
        for (int i = 0; i < graph.size(); i++) {
            if (graph.getVertexes().get(i).getInDegree() == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ret.add(current);
            for (Graph.Edge<E> edge : edges.get(current)) {
                if (edge == null) continue;
                graph.remove(edge);
                if (graph.getVertexes().get(edge.getTo()).getInDegree() == 0) {
                    queue.offer(edge.getTo());
                }
            }
        }
        if (graph.getEdgeCount() > 0) {
            throw new RuntimeException("Graph is closed");
        }
        return ret;
    }

    public static <E extends Comparable<? super E>> void insertSort(List<E> list) {
        for (int i = 1; i < list.size(); i++) {
            E e = list.remove(i);
            int index = 0;
            for (; index < i; index++) {
                if (e.compareTo(list.get(index)) < 0) {
                    break;
                }
            }
            list.add(index, e);
        }
    }

    public static <E extends Comparable<? super E>> void insertSort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            E e = array[i];
            int index = 0;
            for (; index < i; index++) {
                if (e.compareTo(array[index]) < 0) {
                    break;
                }
            }
            for (int j = i; j > index; j--) {
                array[j] = array[j - 1];
            }
            array[index] = e;
        }
    }


    public static <E extends Comparable<? super E>> void shellSort(E[] array) {
        shellSort(array, array.length / 2);
    }

    public static <E extends Comparable<? super E>> void shellSort(E[] array, int dist) {
        while (dist > 0) {
            for (int i = dist; i < array.length; i += dist) {
                E e = array[i];
                int index = 0;
                for (; index < i; index += dist) {
                    if (e.compareTo(array[index]) < 0) {
                        break;
                    }
                }
                for (int j = i; j > index; j -= dist) {
                    array[j] = array[j - dist];
                }
                array[index] = e;
            }
            dist /= 2;
        }
    }

    public static <E extends Comparable<? super E>> void heapSort(List<E> list) {
        CompleteHeap.heapSort(list);
    }
}
