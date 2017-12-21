package com.flyingwang.collections;

import java.util.*;

/**
 * Created by Administrator on 2017/12/18, good luck.
 */
public class Graph<TV, TE> implements Iterable<Graph.Vertex<TV>> {
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
    }

    public void displayEdges() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                Edge<TE> edge = edges.get(i).get(j);
                if (edge != null) {
                    System.out.print(i + " -> " + j + " | ");
                    System.out.println(edge);
                }
            }
        }
        System.out.println("total " + edgeCount + " edges.");
    }

    public List<Vertex<TV>> getVertexes() {
        return vertexes;
    }

    public List<List<Edge<TE>>> getEdges() {
        return edges;
    }

    public Edge<TE> getEdge(int i, int j) {
        rangeCheck(i, j);
        return edges.get(i).get(j);
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int size() {
        return vertexes.size();
    }

    private void rangeCheck(int i, int j) {
        if (i < 0 || j < 0 || i >= vertexes.size() || j >= vertexes.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void reset() {
        for (Vertex vertex : vertexes) {
            vertex.status = Vertex.VertexStatus.UNDISCOVERED;
            vertex.finishTime = -1;
            vertex.discoverTime = -1;
            vertex.parent = -1;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (edges.get(i).get(j) != null) {
                    edges.get(i).get(j).status = Edge.EdgeStatus.UNDETERMINED;
                }
            }
        }
    }

    public void addOrUpdateEdge(int i, int j, TE edge, int weight) {
        rangeCheck(i, j);
        if (edges.get(i).get(j) == null) {
            edgeCount++;
            vertexes.get(i).outDegree++;
            vertexes.get(j).inDegree++;
        }
        edges.get(i).set(j, new Edge<>(edge, weight));
    }

    public void addOrUpdateEdge(int i, int j, int weight) {
        addOrUpdateEdge(i, j, null, weight);
    }

    public void addOrUpdateEdge(int i, int j, TE edge) {
        addOrUpdateEdge(i, j, edge, 1);
    }

    public void addOrUpdateEdge(int i, int j) {
        addOrUpdateEdge(i, j, null, 1);

    }

    public void addOrUpdateDualEdge(int i, int j, TE edge, int weight) {
        rangeCheck(i, j);
        addOrUpdateEdge(i, j, edge, weight);
        addOrUpdateEdge(j, i, edge, weight);
    }

    public void addOrUpdateDualEdge(int i, int j, int weight) {
        addOrUpdateDualEdge(i, j, null, weight);
    }

    public void addOrUpdateDualEdge(int i, int j, TE edge) {
        addOrUpdateDualEdge(i, j, edge, 1);
    }

    public void addOrUpdateDualEdge(int i, int j) {
        addOrUpdateDualEdge(i, j, null, 1);
    }

    public int firstNeighbour(int i) {
        return nextNeighbour(i, vertexes.size());
    }

    public int nextNeighbour(int i, int j) {
        j--;
        try {
            while (j > -1 && !exists(i, j)) {
                j--;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return j;
    }

    public boolean exists(int i, int j) {
        rangeCheck(i, j);
        Edge<TE> edge = edges.get(i).get(j);
        return edge != null;
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


    @Override
    public Iterator<Vertex<TV>> iterator() {
        return bfsIterator();
    }

    public Iterator<Vertex<TV>> iterator(int start) {
        return bfsIterator(start);
    }

    public Iterator<Vertex<TV>> dfsIterator() {
        return new GraphDFSIterator();
    }

    public Iterator<Vertex<TV>> dfsIterator(int start) {
        return new GraphDFSIterator(start);
    }

    public Iterator<Vertex<TV>> bfsIterator() {
        return new GraphBFSIterator();
    }

    public Iterator<Vertex<TV>> bfsIterator(int start) {
        return new GraphBFSIterator(start);
    }

    public static final class Vertex<TV> {
        TV data;
        int inDegree = 0;
        int outDegree = 0;
        int discoverTime = -1;
        int finishTime = -1;
        int parent = -1;
        int priority;
        VertexStatus status = VertexStatus.UNDISCOVERED;

        public Vertex(TV data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    ", inDegree=" + inDegree +
                    ", outDegree=" + outDegree +
                    ", discoverTime=" + discoverTime +
                    ", finishTime=" + finishTime +
                    ", parent=" + parent +
                    ", priority=" + priority +
                    ", status=" + status +
                    '}';
        }

        enum VertexStatus {
            UNDISCOVERED,
            DISCOVERED,
            VISITED
        }
    }

    public static final class Edge<TE> {
        TE data;
        int weight;
        EdgeStatus status = EdgeStatus.UNDETERMINED;

        Edge(TE data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public TE getData() {
            return data;
        }

        public int getWeight() {
            return weight;
        }

        public EdgeStatus getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "data=" + data +
                    ", weight=" + weight +
                    ", status=" + status +
                    '}';
        }

        enum EdgeStatus {
            UNDETERMINED,
            TREE,
            CROSS,
            FORWARD,
            BACKWARD
        }
    }

    private class GraphBFSIterator implements Iterator<Vertex<TV>> {
        Queue<Integer> queue = new LinkedList<>();
        int clock = 0;

        GraphBFSIterator(int start) {
            rangeCheck(start, 0);
            queue.offer(start);
            vertexes.get(start).discoverTime = this.clock;
            this.clock++;

        }

        GraphBFSIterator() {
            queue.offer(0);
            vertexes.get(0).discoverTime = this.clock;
            this.clock++;
        }

        @Override
        public boolean hasNext() {
            if (!queue.isEmpty()) {
                return true;
            }
            for (int i = 0; i < size(); i++) {
                if (vertexes.get(i).status == Vertex.VertexStatus.UNDISCOVERED) {
                    queue.offer(i);
                    return true;
                }
            }
            return false;
        }

        @Override
        public Vertex<TV> next() {
            int i = queue.poll();
            Vertex<TV> fromVertex = vertexes.get(i);
            for (int j = firstNeighbour(i); j > -1; j = nextNeighbour(i, j)) {
                Edge<TE> edge = edges.get(i).get(j);
                Vertex<TV> toVertex = vertexes.get(j);
                if (toVertex.status == Vertex.VertexStatus.UNDISCOVERED) {
                    queue.offer(j);
                    edge.status = Edge.EdgeStatus.TREE;
                    toVertex.status = Vertex.VertexStatus.DISCOVERED;
                    toVertex.discoverTime = this.clock;
                    this.clock++;
                    toVertex.parent = i;
                } else {
                    edge.status = Edge.EdgeStatus.CROSS;
                }
            }
            fromVertex.status = Vertex.VertexStatus.VISITED;
            fromVertex.finishTime = this.clock;
            this.clock++;
            return fromVertex;
        }
    }

    private class GraphDFSIterator implements Iterator<Vertex<TV>> {
        Stack<Integer> stack = new Stack<>();
        int clock = 0;

        GraphDFSIterator(int start) {
            rangeCheck(start, 0);
            Vertex<TV> toVertex = vertexes.get(start);
            stack.push(start);
            toVertex.discoverTime = this.clock;
            toVertex.status = Vertex.VertexStatus.DISCOVERED;
            this.clock++;
        }

        GraphDFSIterator() {
            stack.push(0);
            Vertex<TV> toVertex = vertexes.get(0);
            toVertex.status = Vertex.VertexStatus.DISCOVERED;
            toVertex.discoverTime = this.clock;
            this.clock++;
        }

        @Override
        public boolean hasNext() {
            if (!stack.isEmpty()) {
                return true;
            }
            for (int i = 0; i < size(); i++) {
                if (vertexes.get(i).status == Vertex.VertexStatus.UNDISCOVERED) {
                    stack.push(i);
                    return true;
                }
            }
            return false;
        }

        @Override
        public Vertex<TV> next() {
            int i = stack.peek();
            Vertex<TV> fromVertex = vertexes.get(i);
            for (int j = firstNeighbour(i); j > -1; j = nextNeighbour(i, j)) {
                Edge<TE> edge = edges.get(i).get(j);
                Vertex<TV> toVertex = vertexes.get(j);
                if (toVertex.status == Vertex.VertexStatus.UNDISCOVERED) {
                    toVertex.discoverTime = this.clock;
                    this.clock++;
                    toVertex.status = Vertex.VertexStatus.DISCOVERED;
                    edge.status = Edge.EdgeStatus.TREE;
                    toVertex.parent = i;
                    stack.push(j);
                    return next();
                } else if (toVertex.status == Vertex.VertexStatus.DISCOVERED) {
                    edge.status = Edge.EdgeStatus.BACKWARD;
                } else { // VISITED
                    if (toVertex.discoverTime < fromVertex.discoverTime) {
                        edge.status = Edge.EdgeStatus.FORWARD;
                    } else if (edge.status == Edge.EdgeStatus.UNDETERMINED) {
                        edge.status = Edge.EdgeStatus.CROSS;
                    }
                }
            }
            // if no undiscovered neighbour
            stack.pop();
            fromVertex.status = Vertex.VertexStatus.VISITED;
            fromVertex.finishTime = this.clock;
            this.clock++;
            return fromVertex;
        }
    }
}
