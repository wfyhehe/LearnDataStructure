package com.flyingwang.collections;

/**
 * Created by Administrator on 2017/12/21, good luck.
 */
public class ItemInPack {
    private String name;
    private int cost;
    private int value;

    public ItemInPack(String name, int cost, int value) {
        this.name = name;
        this.cost = cost;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInPack that = (ItemInPack) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ItemInPack{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", value=" + value +
                '}';
    }
}
