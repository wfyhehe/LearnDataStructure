package com.flyingwang.utils;

import java.util.*;

/**
 * Created by wfy on 17-12-25, good luck.
 */
public class HorseTraversal {
    private static List<Map.Entry<Integer, Integer>> diffEntries = new ArrayList<>();

    static {
        diffEntries.add(new AbstractMap.SimpleEntry<>(1, 2));
        diffEntries.add(new AbstractMap.SimpleEntry<>(2, 1));
        diffEntries.add(new AbstractMap.SimpleEntry<>(2, -1));
        diffEntries.add(new AbstractMap.SimpleEntry<>(1, -2));
        diffEntries.add(new AbstractMap.SimpleEntry<>(-1, -2));
        diffEntries.add(new AbstractMap.SimpleEntry<>(-2, -1));
        diffEntries.add(new AbstractMap.SimpleEntry<>(-2, 1));
        diffEntries.add(new AbstractMap.SimpleEntry<>(-1, 2));
    }

    private int size;
    private boolean flag = false;
    private boolean[][] board;
    private int traversedCount = 0;
    private Stack<Map.Entry<Integer, Integer>> path;

    public HorseTraversal(int size) {
        this.size = size;
        board = new boolean[size][size];
        path = new Stack<>();
    }

    public boolean canTraverse() {
        jump(0, 0);
        return flag;
    }

    private void jump(int i, int j) {
        if(flag) {
            return;
        }
        if (i == 0 & j == 0 && traversedCount == size * size) {
            flag = true;
            return;
        }
        if (!inBoard(i, j) || board[i][j]) { // outOfBoard or visited
            return;
        }
        for (Map.Entry<Integer, Integer> entry : diffEntries) {
            board[i][j] = true;
            traversedCount++;
            path.push(new AbstractMap.SimpleEntry<>(i, j));
            jump(i + entry.getKey(), j + entry.getValue());
            board[i][j] = false;
            traversedCount--;
            path.pop();
        }
    }

    private boolean inBoard(int i, int j) {
        return i >= 0 && j >= 0 && i < size && j < size;
    }
}
