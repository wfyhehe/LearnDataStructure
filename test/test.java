import com.flyingwang.collections.CompleteHeap;
import com.flyingwang.collections.Graph;
import com.flyingwang.collections.ItemInPack;
import com.flyingwang.collections.UnionFindSet;
import com.flyingwang.utils.Collections;
import com.flyingwang.utils.DynamicProgramming;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class test {
    @Test
    public void testCompleteHeap() {
        //        List<Integer> randomArray = new ArrayList<>();
        //        Random random = new Random();
        //        for (int i = 0; i < 12; i++) {
        //            randomArray.add(random.nextInt(50));
        //        }
        Integer[] randomArray = new Integer[]{
                14, 34, 6, 30, 4, 28, 45, 26, 41, 32, 40, 43
        };
        CompleteHeap<Integer> ch = new CompleteHeap<>(Arrays.asList(randomArray));
        System.out.println(Arrays.asList(randomArray));
        System.out.println(ch);
        ch.add(44);
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch);
        ch.deleteMax();
        System.out.println(ch.getMax());
    }

    @Test
    public void testUnionFindSet() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            integers.add(random.nextInt(1000));
        }
        UnionFindSet<Integer> ufs = new UnionFindSet<>(integers);
        ufs.union(integers.get(1), integers.get(2));
        ufs.union(integers.get(2), integers.get(3));
        ufs.union(integers.get(3), integers.get(4));
        ufs.union(integers.get(4), integers.get(5));
        ufs.union(integers.get(4), integers.get(6));
        ufs.union(integers.get(11), integers.get(15));
        ufs.union(integers.get(11), integers.get(17));
        ufs.union(integers.get(17), integers.get(19));
        ufs.union(integers.get(15), integers.get(8));
        ufs.union(integers.get(14), integers.get(18));
        System.out.println(ufs);
    }

    @Test
    public void testRadixSort() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            integers.add(random.nextInt(89999) + 10000); // 10000 ~ 99999
        }
        List<Integer> copyIntegers = new ArrayList<>(integers);
        java.util.Collections.sort(integers);
        Collections.radixSort(copyIntegers, 5);
        System.out.println(integers);
        System.out.println(copyIntegers);
        assert integers.equals(copyIntegers);
    }

    @Test
    public void testPermutation() {
        Integer[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        long startTime = System.currentTimeMillis();
        List<List<Integer>> output = Collections.generatePermutation(Arrays.asList(elements));
        long endTime = System.currentTimeMillis();
        for (List<Integer> list : output) {
            System.out.println(list);
        }
        System.out.println(output.size());
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void testMajority() {
        Integer[] elements = {1, 3, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(elements));
        System.out.println(Collections.findMajority(list));
    }

    @Test
    public void testFindMinMax() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            integers.add(random.nextInt(89999) + 10000); // 10000 ~ 99999
        }
        Map<String, Integer> map = Collections.findMinMax(integers);
        System.out.println(map);
    }

    @Test
    public void testFindKthSmallest() {
        Integer[] elements = {9, 7, 8, 1, 6, 4, 2, 3, 5, 0};
        List<Integer> list = Arrays.asList(elements);
        Integer target = Collections.findKthSmallest(list, 2);
        System.out.println(target);
    }

    @Test
    public void testLCS() {
        String str1 = "xyxxzxyzxy";
        String str2 = "zxzyyzxxyxxz";
        CharSequence commonStr = DynamicProgramming.longestCommonSubSequence(str1, str2);
        System.out.println(commonStr);
    }

    @Test
    public void testMatrixChain() {
        List<Map.Entry<Integer, Integer>> matrixList = new ArrayList<>();
        matrixList.add(new AbstractMap.SimpleEntry<>(5, 10));
        matrixList.add(new AbstractMap.SimpleEntry<>(10, 4));
        matrixList.add(new AbstractMap.SimpleEntry<>(4, 6));
        matrixList.add(new AbstractMap.SimpleEntry<>(6, 10));
        matrixList.add(new AbstractMap.SimpleEntry<>(10, 2));
        System.out.println(DynamicProgramming.matrixChainMultiplication(matrixList));
    }

    @Test
    public void testUndirectedGraph() {
        Graph<Integer, Integer> g = new Graph<>(10);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1);
        g.addOrUpdateDualEdge(0, 2);
        g.addOrUpdateDualEdge(0, 4);
        g.addOrUpdateDualEdge(1, 7);
        g.addOrUpdateDualEdge(2, 5);
        g.addOrUpdateDualEdge(2, 6);
        g.addOrUpdateDualEdge(3, 5);
        g.addOrUpdateDualEdge(4, 9);
        g.addOrUpdateDualEdge(4, 9);
        g.addOrUpdateDualEdge(5, 9);
        g.addOrUpdateDualEdge(7, 8);
        g.addOrUpdateDualEdge(8, 9);
        System.out.println("BFS:");
        for (Iterator it = g.bfsIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        g.reset();
        System.out.println("DFS:");
        for (Iterator it = g.dfsIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testDirectedGraph() {
        Graph<Integer, Integer> g = new Graph<>(7);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateEdge(0, 1);
        g.addOrUpdateEdge(0, 2);
        g.addOrUpdateEdge(0, 5);
        g.addOrUpdateEdge(1, 2);
        g.addOrUpdateEdge(3, 0);
        g.addOrUpdateEdge(3, 4);
        g.addOrUpdateEdge(4, 5);
        g.addOrUpdateEdge(5, 6);
        g.addOrUpdateEdge(6, 0);
        g.addOrUpdateEdge(6, 2);
        System.out.println("BFS:");
        for (Iterator it = g.bfsIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        g.displayEdges();
        g.reset();
        System.out.println("DFS:");
        for (Iterator it = g.dfsIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        g.displayEdges();
    }


    @Test
    public void testFloyd() {
        Graph<Integer, Integer> g = new Graph<>(4);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateEdge(0, 0, 0);
        g.addOrUpdateEdge(1, 1, 0);
        g.addOrUpdateEdge(2, 2, 0);
        g.addOrUpdateEdge(3, 3, 0);
        g.addOrUpdateEdge(0, 1, 2);
        g.addOrUpdateEdge(0, 2, 6);
        g.addOrUpdateEdge(0, 3, 4);
        g.addOrUpdateEdge(1, 2, 3);
        g.addOrUpdateEdge(2, 0, 7);
        g.addOrUpdateEdge(2, 3, 1);
        g.addOrUpdateEdge(3, 0, 5);
        g.addOrUpdateEdge(3, 2, 12);
        List<List<Integer>> path = Collections.floyd(g);
        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < path.get(0).size(); j++) {
                if (path.get(i).get(j) != null) {
                    System.out.print(path.get(i).get(j));
                } else {
                    System.out.print("∞");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void testZeroOnePack() {
        List<ItemInPack> items = new ArrayList<>();
        items.add(new ItemInPack("a", 2, 6));
        items.add(new ItemInPack("b", 2, 3));
        items.add(new ItemInPack("c", 6, 5));
        items.add(new ItemInPack("d", 5, 4));
        items.add(new ItemInPack("e", 4, 6));
        List<Integer> valueList = DynamicProgramming.zeroOnePack(items, 10);
        for (int i = 0; i < valueList.size(); i++) {
            System.out.printf("%3s", valueList.get(i));
        }
        System.out.println();
        assert valueList.get(valueList.size() - 1) == 15;
    }

    @Test
    public void testCompletePack() {
        List<ItemInPack> items = new ArrayList<>();
        items.add(new ItemInPack("c", 6, 5));
        items.add(new ItemInPack("d", 5, 4));
        items.add(new ItemInPack("e", 4, 6));
        items.add(new ItemInPack("b", 2, 3));
        items.add(new ItemInPack("a", 2, 6));
        List<Integer> valueList = DynamicProgramming.completePack(items, 10);
        for (int i = 0; i < valueList.size(); i++) {
            System.out.printf("%3s", valueList.get(i));
        }
        System.out.println();
        assert valueList.get(valueList.size() - 1) == 30;
    }

    @Test
    public void testDijkstra() {
        Graph<Integer, Integer> g = new Graph<>(6);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1, 7);
        g.addOrUpdateDualEdge(0, 2, 9);
        g.addOrUpdateDualEdge(0, 5, 14);
        g.addOrUpdateDualEdge(1, 2, 10);
        g.addOrUpdateDualEdge(1, 3, 15);
        g.addOrUpdateDualEdge(2, 3, 11);
        g.addOrUpdateDualEdge(2, 5, 2);
        g.addOrUpdateDualEdge(3, 4, 6);
        g.addOrUpdateDualEdge(4, 5, 9);
        List<Integer> path = Collections.dijkstra(g, 0);
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) != null) {
                System.out.print(path.get(i));
            } else {
                System.out.print("∞");
            }
            System.out.println();
        }
        Integer[] assertArray = new Integer[]{0, 7, 9, 20, 20, 11};
        assert path.equals(new ArrayList<>(Arrays.asList(assertArray)));
    }

    @Test
    public void testKruskal() {
        Graph<Integer, Integer> g = new Graph<>(6);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1, 5);
        g.addOrUpdateDualEdge(0, 2, 8);
        g.addOrUpdateDualEdge(0, 3, 7);
        g.addOrUpdateDualEdge(0, 5, 3);
        g.addOrUpdateDualEdge(1, 2, 4);
        g.addOrUpdateDualEdge(2, 3, 5);
        g.addOrUpdateDualEdge(2, 5, 9);
        g.addOrUpdateDualEdge(3, 4, 5);
        g.addOrUpdateDualEdge(4, 5, 1);
        List<Graph.Edge<Integer>> edges = Collections.kruskal(g);
        int pathLength = 0;
        for (Graph.Edge<Integer> edge : edges) {
            System.out.println(edge);
            pathLength += edge.getWeight();
        }
        assert edges.size()==5;
        assert pathLength==18;
    }

    @Test
    public void testPrim() {
        Graph<Integer, Integer> g = new Graph<>(6);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1, 1);
        g.addOrUpdateDualEdge(0, 2, 2);
        g.addOrUpdateDualEdge(1, 2, 6);
        g.addOrUpdateDualEdge(1, 3, 11);
        g.addOrUpdateDualEdge(2, 3, 9);
        g.addOrUpdateDualEdge(2, 4, 13);
        g.addOrUpdateDualEdge(3, 4, 7);
        g.addOrUpdateDualEdge(3, 5, 3);
        g.addOrUpdateDualEdge(4, 5, 4);
        List<Graph.Edge<Integer>> edges = Collections.prim(g);
        int pathLength = 0;
        for (Graph.Edge<Integer> edge : edges) {
            System.out.println(edge);
            pathLength += edge.getWeight();
        }
        assert edges.size()==5;
        assert pathLength==19;
    }

}
