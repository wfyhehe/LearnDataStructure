package com.flyingwang.utils;

import com.flyingwang.collections.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wfy on 17-12-25, good luck.
 */
public class ColorBackTrack {
    private Graph<Integer, Integer> graph;
    private int colorCount;
    private int caseCount = 0;
    private int[] colors;
    private boolean print;

    public ColorBackTrack(Graph<Integer, Integer> graph, int colorCount) {
        this.graph = graph;
        this.colorCount = colorCount;
        colors = new int[graph.size()];
        print = true;
    }

    public ColorBackTrack(Graph<Integer, Integer> graph, int colorCount, boolean print) {
        this(graph, colorCount);
        this.print = print;
    }

    public void run() {
        backTrack(-1);
        System.out.printf("Total %d cases.\n", caseCount);
    }

    private void backTrack(int index) {
        if (index != -1 && !isValid(index)) {
            return;
        }
        if (index == graph.size() - 1) {
            caseCount++;
            if (this.print) {
                System.out.printf("Case %d:\n", caseCount);
                printColors();
            }
            return;
        }

        for (int color = 1; color <= colorCount; color++) {
            colors[index + 1] = color;
            backTrack(index + 1);
            colors[index + 1] = 0;
        }
    }

    private boolean isValid(int index) {
        List<Graph.Edge<Integer>> edgesOfThis = graph.getEdges().get(index);
        for (Graph.Edge<Integer> edge : edgesOfThis) { // find if there's same color
            if (edge == null) {
                continue;
            }
            int fromColor = colors[edge.getFrom()];
            int toColor = colors[edge.getTo()];
            if (fromColor != 0 && toColor != 0 && fromColor == toColor) { // invalid
                return false;
            }
        }
        return true;
    }

    private void printColors() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int color : colors) {
            sb.append(color);
            sb.append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }

}
