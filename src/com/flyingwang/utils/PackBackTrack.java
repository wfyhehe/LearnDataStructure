package com.flyingwang.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wfy on 18-1-12, good luck.
 */
public class PackBackTrack {
    private List<ItemInPack> items;
    private List<ItemInPack> selectedItems = new ArrayList<>();
    private int packSize;

    public PackBackTrack(List<ItemInPack> items, int packSize) {
        this.items = items;
        this.packSize = packSize;
    }

    public void run() {
        items.sort((i1, i2) -> { // 按价值密度从大到小排列
            double dense1 = ((double) (i1.getValue())) / i1.getCost();
            double dense2 = ((double) (i2.getValue())) / i2.getCost();
            if (dense1 - dense2 < 1e-9) {
                return 0;
            }
            if (dense1 < dense2) {
                return -1;
            } else {
                return 1;
            }
        });
        backTrack(0);
    }

    private void backTrack(int index) {
        if (getTotalCost() >= this.packSize) {
            return;
        }
        if (index == items.size()) {
            print();
            return;
        }

        // with items[index]
        selectedItems.add(items.get(index));
        backTrack(index + 1);
        selectedItems.remove(selectedItems.size() - 1);

        // without items[index]
        backTrack(index + 1);
    }

    private void print() {
        System.out.println(selectedItems);
        System.out.printf("cost:%d, value:%d\n", getTotalCost(), getTotalValue());
    }

    private int getTotalCost() {
        int totalCost = 0;
        for (ItemInPack item : selectedItems) {
            totalCost += item.getCost();
        }
        return totalCost;
    }

    private int getTotalValue() {
        int totalValue = 0;
        for (ItemInPack item : selectedItems) {
            totalValue += item.getValue();
        }
        return totalValue;
    }
}
