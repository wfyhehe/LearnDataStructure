package com.flyingwang.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18, good luck.
 */
public class Graph<TV, TE> {
    private List<Vertex<TV>> vertexes;
    private List<List<Edge<TE>>> edges;
    private int edgeCount = 0;

    public Graph(int size) {
        vertexes = new ArrayList<>(size);
        edges = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            vertexes.add(null);
            edges.add(new ArrayList<>(size));
            for (int j = 0; j < size; j++) {
                edges.get(i).add(null);
            }
        }
    }

    public static void main(String[] args) {
        Graph<Integer, Integer> g = new Graph<>(5);
        g.edges.get(2).set(2, new Edge<>(15, 2));
        System.out.println(g.edges.get(2).get(3));
    }

    private void rangeCheck(int i, int j) {
        if (i < 0 || j < 0 || i >= vertexes.size() || j >= vertexes.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int firstNeighbour(int i) {
        return nextNeighbour(i, vertexes.size());
    }

    public int nextNeighbour(int i, int j) {
        rangeCheck(i, j);
        while (j > -1 && !exists(i, j)) {
            j--;
        }
        return j;
    }

    public boolean exists(int i, int j) {
        rangeCheck(i, j);
        Edge<TE> edge = edges.get(i).get(j);
        return edge != null;
    }

    public boolean insert(TE data, int weight, int i, int j) {
        rangeCheck(i, j);
        if (exists(i, j)) return false;
        edges.get(i).set(j, new Edge<>(data, weight));
        edgeCount++;
        vertexes.get(i).outDegree++;
        vertexes.get(j).inDegree++;
        return true;
    }

    public boolean insert(TV data) {
        vertexes.add(new Vertex<>(data));
        int newSize = vertexes.size();
        for (int i = 0; i < newSize - 1; i++) {
            edges.get(i).add(null);
        }
        edges.add(new ArrayList<>(newSize));
        for (int i = 0; i < newSize; i++) {
            edges.get(newSize - 1).add(null);
        }
        return true;
    }

    public Edge remove(int i, int j) {
        rangeCheck(i, j);
        if (!exists(i, j)) return null;
        Edge ret = edges.get(i).get(j);
        edges.get(i).set(j, null);
        edgeCount--;
        vertexes.get(i).outDegree--;
        vertexes.get(j).inDegree--;
        return ret;
    }

    public Vertex remove(int i) {
        rangeCheck(i, 0);
        Vertex ret = vertexes.get(i);
        for (int j = 0; j < vertexes.size(); j++) {
            remove(i, j);
            remove(j, i);
        }
        for (int j = 0; j < vertexes.size(); j++) {
            edges.get(j).remove(i);
        }
        edges.remove(i);
        vertexes.remove(i);
        return ret;
    }

    static final class Vertex<TV> {
        TV data;
        int inDegree = 0;
        int outDegree = 0;
        int discoverTime;
        int finishTime;
        int parent;
        int priority;
        VertexStatus status = VertexStatus.UNDISCOVERED;

        Vertex(TV data) {
            this.data = data;
        }

        enum VertexStatus {
            UNDISCOVERED,
            DISCOVERED,
            VISITED
        }
    }

    static final class Edge<TE> {
        TE data;
        int weight;
        EdgeStatus status = EdgeStatus.UNDETERMINED;

        Edge(TE data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        enum EdgeStatus {
            UNDETERMINED,
            TREE,
            CROSS,
            FORWARD,
            BACKWARD
        }
    }

}
