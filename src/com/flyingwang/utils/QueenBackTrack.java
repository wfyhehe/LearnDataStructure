package com.flyingwang.utils;


/**
 * Created by wfy on 17-12-25, good luck.
 */
public class QueenBackTrack {
    private int size;
    private int caseCount = 0;
    private int[] positions;
    private boolean print;

    public QueenBackTrack(int size) {
        this.size = size;
        print = true;
        positions = new int[size];
    }

    public QueenBackTrack(int size, boolean print) {
        this(size);
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
        if (index == size - 1) {
            caseCount++;
            if (this.print) {
                System.out.printf("Case %d:\n", caseCount);
                printBoard();
            }
            return;
        }
        for (int col = 0; col < size; col++) {
            positions[index + 1] = col;
            backTrack(index + 1);
            positions[index + 1] = 0;
        }
    }

    private boolean isValid(int index) {
        for (int col = 0; col < index; col++) {
            if (positions[col] == positions[index] || // 南北
                    positions[col] + (index - col) == positions[index] || //西北-东南
                    positions[col] - (index - col) == positions[index] //西南-东北
                    ) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int col : positions) {
            for (int i = 0; i < size; i++) {
                sb.append(i == col ? "1 " : "0 ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

}
