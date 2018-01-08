package com.flyingwang.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by wfy on 18-1-8, good luck.
 */
public class HamiltonianPath {
    private int size;
    private int caseCount = 0;
    private int[][] graph;
    private boolean visited[];
    private int source;
    private Stack<Integer> path = new Stack<>();

    public HamiltonianPath(int[][] graph) {
        this.graph = graph;
        this.size = graph.length;
        visited = new boolean[size];
    }

    public void run() {
        for (int i = 0; i < size; i++) {
            clear();
            this.source = i;
            visited[i] = true;
            path.push(i);
            dfs(i);
        }
    }

    private void dfs(int v) {
        List<Integer> neighbours = getNeighbours(v);
        if (neighbours.isEmpty()) {
            return;
        }
        for (int neighbour : neighbours) {
            if (neighbour == this.source && path.size() == size) {
                caseCount++;
                System.out.printf("Case %d\n", caseCount);
//                printPath();
                printPathWithOffset(1);
                return;
            }
            if (!visited[neighbour]) {
                path.push(neighbour);
                visited[neighbour] = true;
                dfs(neighbour);
                path.pop();
                visited[neighbour] = false;
            }
        }
    }

    private void clear() {
        visited = new boolean[size];
        path = new Stack<>();
    }

    List<Integer> getNeighbours(int v) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (graph[v][i] > 0) {
                ret.add(i);
            }
        }
        return ret;
    }

    private void printPath() {
        Integer[] array = new Integer[size];
        path.copyInto(array);
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println(list);
    }

    private void printPathWithOffset(int offset) {
        Integer[] array = new Integer[size];
        path.copyInto(array);
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + offset);
        }
        System.out.println(list);
    }
}
