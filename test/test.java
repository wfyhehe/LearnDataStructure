import com.flyingwang.collections.*;
import com.flyingwang.utils.Collections;
import com.flyingwang.utils.*;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class test {
    @Test
    public void testForNothing() {
        Integer[] array = new Integer[]{
                8, 33, 17, 51, 57, 49, 35, 11, 25, 37, 14, 2, 3, 13, 52, 12, 6, 29, 32, 54, 5,
                16, 22, 23, 7
        };
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        java.util.Collections.sort(list);
        System.out.println(list);
        System.out.println(list.get(12));
        assert list.get(12) == 23;
    }

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
        Integer[] elements = {1, 2, 3, 4, 5, 6};
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000002; i++) {
            list.add(i);
        }
        java.util.Collections.shuffle(list);
        long startTime = System.currentTimeMillis();
        Integer target = Collections.findKthSmallest(list, 4000000);
        long endTime = System.currentTimeMillis();
        assert target == 3999999;
        System.out.println(target);
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void testFindKthSmallestUseSelect() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000002; i++) {
            list.add(i);
        }
        java.util.Collections.shuffle(list);
        long startTime = System.currentTimeMillis();
        Integer target = Collections.findKthSmallest(list, 400000, false);
        long endTime = System.currentTimeMillis();
        assert target == 399999;
        System.out.println(target);
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void testLCS() {
        String str1 = "xyxxzxyzxy";
        String str2 = "zxzyyzxxyxxz";
        CharSequence commonStr = DynamicProgramming.longestCommonSubSequence(str1, str2);
        System.out.println(commonStr);
        assert commonStr.length() == 6;
    }


    @Test
    public void testEditDistance() {
        String str1 = "kitten";
        String str2 = "sitting";
        assert DynamicProgramming.editDistance(str1, str2) == 3;
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
//        g.addOrUpdateEdge(0 , 1, 1);
//        g.addOrUpdateEdge(0 , 1, 1);
//        g.addOrUpdateEdge(0 , 2, 2);
//        g.addOrUpdateEdge(0 , 3, 3);
//        g.addOrUpdateEdge(1 , 0, 1);
//        g.addOrUpdateEdge(1 , 2, 4);
//        g.addOrUpdateEdge(1 , 3, 11);
//        g.addOrUpdateEdge(2 , 0, 2);
//        g.addOrUpdateEdge(2 , 1, 6);
//        g.addOrUpdateEdge(2 , 3, 2);
//        g.addOrUpdateEdge(3 , 0, 3);
//        g.addOrUpdateEdge(3 , 1, 3);
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
    public void testDijkstra2() {
        Graph<Integer, Integer> g = new Graph<>(14);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1, 2);
        g.addOrUpdateDualEdge(0, 2, 1);
        g.addOrUpdateDualEdge(0, 3, 4);
        g.addOrUpdateDualEdge(0, 4, 2);
        g.addOrUpdateDualEdge(1, 2, 1);
        g.addOrUpdateDualEdge(1, 5, 4);
        g.addOrUpdateDualEdge(1, 6, 2);
        g.addOrUpdateDualEdge(2, 3, 2);
        g.addOrUpdateDualEdge(3, 4, 2);
        g.addOrUpdateDualEdge(3, 6, 3);
        g.addOrUpdateDualEdge(3, 7, 4);
        g.addOrUpdateDualEdge(4, 8, 3);
        g.addOrUpdateDualEdge(5, 6, 2);
        g.addOrUpdateDualEdge(5, 9, 3);
        g.addOrUpdateDualEdge(5, 10, 4);
        g.addOrUpdateDualEdge(6, 7, 1);
        g.addOrUpdateDualEdge(6, 10, 5);
        g.addOrUpdateDualEdge(7, 8, 1);
        g.addOrUpdateDualEdge(7, 11, 3);
        g.addOrUpdateDualEdge(8, 11, 3);
        g.addOrUpdateDualEdge(8, 12, 4);
        g.addOrUpdateDualEdge(9, 10, 2);
        g.addOrUpdateDualEdge(9, 13, 2);
        g.addOrUpdateDualEdge(10, 11, 2);
        g.addOrUpdateDualEdge(10, 13, 1);
        g.addOrUpdateDualEdge(11, 12, 1);
        g.addOrUpdateDualEdge(11, 13, 3);
        g.addOrUpdateDualEdge(12, 13, 1);
        List<Integer> path = Collections.dijkstra(g, 0);
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) != null) {
                System.out.print(path.get(i));
            } else {
                System.out.print("∞");
            }
            System.out.println();
        }
        Integer[] assertArray = new Integer[]{0, 2, 1, 3, 2, 6, 4, 5, 5, 9, 9, 8, 9, 10};
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
        assert edges.size() == 5;
        assert pathLength == 18;
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
        assert edges.size() == 5;
        assert pathLength == 19;
    }

    @Test
    public void testTraverseBinTree() {
        BinaryTree<Integer> tree = new BinaryTree<>(5);
        BinaryTree.TreeNode<Integer> root = tree.getRoot();
        BinaryTree.TreeNode<Integer> l = tree.insertAsLChild(root, 2);
        BinaryTree.TreeNode<Integer> ll = tree.insertAsLChild(l, 1);
        BinaryTree.TreeNode<Integer> lr = tree.insertAsRChild(l, 4);
        BinaryTree.TreeNode<Integer> lrl = tree.insertAsLChild(lr, 3);
        BinaryTree.TreeNode<Integer> r = tree.insertAsRChild(root, 6);
        BinaryTree.TreeNode<Integer> rr = tree.insertAsRChild(r, 8);
        BinaryTree.TreeNode<Integer> rrl = tree.insertAsLChild(rr, 7);
        BinaryTree.TreeNode<Integer> rrr = tree.insertAsRChild(rr, 9);
        BinaryTree.TreeNode<Integer> rrrr = tree.insertAsRChild(rrr, 11);
        BinaryTree.TreeNode<Integer> rrrrl = tree.insertAsLChild(rrrr, 10);
        System.out.println(tree.size());
        assert tree.size() == 11;
        System.out.println();

        Integer[] preOrderSequenceSample = new Integer[]{5, 2, 1, 4, 3, 6, 8, 7, 9, 11, 10};
        List<Integer> preOrderSequenceTest = new ArrayList<>();
        System.out.println("Pre Order:");
        for (Iterator<BinaryTree.TreeNode<Integer>> it = tree.preOrderIterator(); it.hasNext(); ) {
            BinaryTree.TreeNode<Integer> node = it.next();
            System.out.println(node);
            preOrderSequenceTest.add(node.getData());
        }
        assert preOrderSequenceTest.equals(new ArrayList<>(Arrays.asList(preOrderSequenceSample)));
        System.out.println();

        Integer[] inOrderSequenceSample = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        List<Integer> inOrderSequenceTest = new ArrayList<>();
        System.out.println("In Order:");
        for (Iterator<BinaryTree.TreeNode<Integer>> it = tree.inOrderIterator(); it.hasNext(); ) {
            BinaryTree.TreeNode<Integer> node = it.next();
            System.out.println(node);
            inOrderSequenceTest.add(node.getData());
        }
        assert inOrderSequenceTest.equals(new ArrayList<>(Arrays.asList(inOrderSequenceSample)));
        System.out.println();

        Integer[] postOrderSequenceSample = new Integer[]{1, 3, 4, 2, 7, 10, 11, 9, 8, 6, 5};
        List<Integer> postOrderSequenceTest = new ArrayList<>();
        System.out.println("In Order:");
        for (Iterator<BinaryTree.TreeNode<Integer>> it = tree.postOrderIterator(); it.hasNext(); ) {
            BinaryTree.TreeNode<Integer> node = it.next();
            System.out.println(node);
            postOrderSequenceTest.add(node.getData());
        }
        assert postOrderSequenceTest.equals(new ArrayList<>(Arrays.asList(postOrderSequenceSample)));
        System.out.println();

        Integer[] levelSequenceSample = new Integer[]{5, 2, 6, 1, 4, 8, 3, 7, 9, 11, 10};
        List<Integer> levelSequenceTest = new ArrayList<>();
        System.out.println("In Order:");
        for (Iterator<BinaryTree.TreeNode<Integer>> it = tree.levelIterator(); it.hasNext(); ) {
            BinaryTree.TreeNode<Integer> node = it.next();
            System.out.println(node);
            levelSequenceTest.add(node.getData());
        }
        assert levelSequenceTest.equals(new ArrayList<>(Arrays.asList(levelSequenceSample)));
        System.out.println();
    }

    private BinarySearchTree<Integer> bstInsert() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(5);
        bst.insert(2);
        bst.insert(6);
        bst.insert(4);
        bst.insert(3);
        bst.insert(1);
        return bst;
    }

    @Test
    public void testBstInsert() {
        bstInsert();
    }

    @Test
    public void testBSTRemove() {
        BinarySearchTree<Integer> bst = bstInsert();
        bst.remove(2);
        bst.remove(5);
        bst.remove(6);
        bst.remove(1);
        bst.remove(4);
        assert bst.size() == 1;
    }

    @Test
    public void testHuffmanTree() {
        Stack<HuffmanTree.HuffmanNode<String>> nodes = new Stack<>();
        nodes.push(new HuffmanTree.HuffmanNode<>("the", 1192));
        nodes.push(new HuffmanTree.HuffmanNode<>("of", 667));
        nodes.push(new HuffmanTree.HuffmanNode<>("a", 541));
        nodes.push(new HuffmanTree.HuffmanNode<>("to", 518));
        nodes.push(new HuffmanTree.HuffmanNode<>("and", 462));
        nodes.push(new HuffmanTree.HuffmanNode<>("in", 450));
        nodes.push(new HuffmanTree.HuffmanNode<>("that", 242));
        nodes.push(new HuffmanTree.HuffmanNode<>("he", 195));
        nodes.push(new HuffmanTree.HuffmanNode<>("is", 190));
        nodes.push(new HuffmanTree.HuffmanNode<>("at", 181));
        nodes.push(new HuffmanTree.HuffmanNode<>("on", 174));
        nodes.push(new HuffmanTree.HuffmanNode<>("for", 157));
        nodes.push(new HuffmanTree.HuffmanNode<>("His", 138));
        nodes.push(new HuffmanTree.HuffmanNode<>("are", 124));
        nodes.push(new HuffmanTree.HuffmanNode<>("be", 123));
        HuffmanTree<String> tree = new HuffmanTree<>(nodes);
        tree.display();
    }

    @Test
    public void testHuffmanTree2() {
        Stack<HuffmanTree.HuffmanNode<String>> nodes = new Stack<>();
        nodes.push(new HuffmanTree.HuffmanNode<>("a", 11));
        nodes.push(new HuffmanTree.HuffmanNode<>("b", 12));
        nodes.push(new HuffmanTree.HuffmanNode<>("c", 13));
        nodes.push(new HuffmanTree.HuffmanNode<>("d", 14));
        nodes.push(new HuffmanTree.HuffmanNode<>("e", 15));
        nodes.push(new HuffmanTree.HuffmanNode<>("f", 16));
        HuffmanTree<String> tree = new HuffmanTree<>(nodes);
        tree.display();
    }

    @Test
    public void testColor() {
        Graph<Integer, Integer> g = new Graph<>(5);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateDualEdge(0, 1);
        g.addOrUpdateDualEdge(0, 2);
        g.addOrUpdateDualEdge(0, 3);
        g.addOrUpdateDualEdge(1, 2);
        g.addOrUpdateDualEdge(1, 3);
        g.addOrUpdateDualEdge(1, 4);
        g.addOrUpdateDualEdge(2, 3);
        g.addOrUpdateDualEdge(3, 4);
        ColorBackTrack cbt = new ColorBackTrack(g, 4, false);
        long start = System.currentTimeMillis();
        cbt.run();
        long end = System.currentTimeMillis();
        System.out.printf("Time span: %dms\n", (end - start));
    }

    @Test
    public void testQueen() {
        QueenBackTrack qbt = new QueenBackTrack(6);
        qbt.run();
    }

    @Test
    public void testHorseTraversal() {
        HorseTraversal ht;
        for (int i = 4; i < 16; i += 2) {
            ht = new HorseTraversal(i);
            long start = System.currentTimeMillis();
            System.out.printf("%d: %s\n", i, ht.canTraverse() ? "Succeeded" : "Failed");
            long end = System.currentTimeMillis();
            System.out.printf("Time span: %dms\n", (end - start));
        }
    }

    @Test
    public void testKmp() {
        String str1 = "csgyeuchinewhuiachinchillwfehuifchinchillafehufiheriug";
        String str2 = "chinchilla";
        String str3 = "abcdqabcdabcp";
        String str4 = "aaaaaaaaaaaabc";
        assert Collections.isSubstring(str2, str1);
        assert !Collections.isSubstring(str3, str1);
        assert !Collections.isSubstring(str4, str1);
    }

    private Graph<Integer, Integer> graphForTopologicalSort() {
        Graph<Integer, Integer> g = new Graph<>(13);
        for (int i = 0; i < g.getVertexes().size(); i++) {
            g.getVertexes().set(i, new Graph.Vertex<>(i));
        }
        g.addOrUpdateEdge(0, 1);
        g.addOrUpdateEdge(0, 5);
        g.addOrUpdateEdge(0, 6);
        g.addOrUpdateEdge(2, 0);
        g.addOrUpdateEdge(2, 3);
        g.addOrUpdateEdge(3, 5);
        g.addOrUpdateEdge(5, 4);
        g.addOrUpdateEdge(6, 4);
        g.addOrUpdateEdge(6, 9);
        g.addOrUpdateEdge(7, 6);
        g.addOrUpdateEdge(8, 7);
        g.addOrUpdateEdge(9, 10);
        g.addOrUpdateEdge(9, 11);
        g.addOrUpdateEdge(9, 12);
        g.addOrUpdateEdge(11, 12);
        return g;
    }

    @Test
    public void testKahn() {
        Graph<Integer, Integer> g = graphForTopologicalSort();
        try {
            System.out.println(Collections.topologicalSort(g));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFailedKahn() {
        Graph<Integer, Integer> g = graphForTopologicalSort();
        g.addOrUpdateEdge(9, 8);
        try {
            System.out.println(Collections.topologicalSort(g));
        } catch (RuntimeException | CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getRandomIntegers(int length) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            list.add(random.nextInt(10000)); // 10000 ~ 99999
        }
        return list;
    }

    @Test
    public void testInsertSort() {
        List<Integer> integers = getRandomIntegers(20);
        List<Integer> copyIntegers = new ArrayList<>(integers);
        java.util.Collections.sort(integers);
        Collections.insertSort(copyIntegers);
        System.out.println(copyIntegers);
        assert integers.equals(copyIntegers);
    }

    @Test
    public void testInsertSort2() {
        Integer[] integers = new Integer[]{
                98, 79, 15, 65, 186, 14, 22, 61, 48
        };
        Integer[] expectedArray = new Integer[]{
                14, 15, 22, 48, 61, 65, 79, 98, 186
        };
        Collections.insertSort(integers);
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));
        System.out.println(list);
        assert list.equals(Arrays.asList(expectedArray));
    }

    @Test
    public void testShellSort() {
        Integer[] integers = new Integer[]{
                98, 79, 15, 65, 186, 14, 22, 61, 48
        };
        Integer[] expectedArray = new Integer[]{
                14, 15, 22, 48, 61, 65, 79, 98, 186
        };
        Collections.shellSort(integers);
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));
        System.out.println(list);
        assert list.equals(Arrays.asList(expectedArray));
    }

    @Test
    public void testHeapSort() {
        Integer[] integers = new Integer[]{
                98, 79, 15, 65, 186, 14, 22, 61, 48
        };
        Integer[] expectedArray = new Integer[]{
                14, 15, 22, 48, 61, 65, 79, 98, 186
        };
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));
        Collections.heapSort(list);
        System.out.println(list);
        assert list.equals(Arrays.asList(expectedArray));
    }

    @Test
    public void testHamiltonianPath() {
        int[][] graph = new int[][]{
              // 1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 2
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 3
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 4
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 9
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 10
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0}, // 11
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 12
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, // 13
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, // 14
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0}, // 15
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, // 16
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0}, // 17
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0}, // 18
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, // 19
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 20
        };
        HamiltonianPath hp = new HamiltonianPath(graph);
        hp.run();
    }

    @Test
    public void testMaxIntervalSum() {
        Integer[] numbers1 = new Integer[]{-1, 4, -2, 3, -2, 3};
        Integer[] numbers2 = new Integer[]{-1, -2, -2};
        assert DynamicProgramming.maxIntervalSum(new ArrayList<>(Arrays.asList(numbers1))) == 6;
        assert DynamicProgramming.maxIntervalSum(new ArrayList<>(Arrays.asList(numbers2))) == -1;
    }

    @Test
    public void testMaxIntervalSum2() {
        Integer[] numbers1 = new Integer[]{-1, 4, -2, 3, -2, 3};
        Integer[] numbers2 = new Integer[]{-1, -2, -2};
        assert DynamicProgramming.maxIntervalSum2(new ArrayList<>(Arrays.asList(numbers1))) == 6;
        assert DynamicProgramming.maxIntervalSum2(new ArrayList<>(Arrays.asList(numbers2))) == -1;
    }

    @Test
    public void testMaxIntervalSumWithDivide() {
        Integer[] numbers1 = new Integer[]{-1, 4, -2, 3, -2, 3};
        Integer[] numbers2 = new Integer[]{-1, -2, -2};
        assert DynamicProgramming.maxIntervalSumWithDivide(new ArrayList<>(Arrays.asList(numbers1))) == 6;
        assert DynamicProgramming.maxIntervalSumWithDivide(new ArrayList<>(Arrays.asList(numbers2))) == -1;
    }

    @Test
    public void testSubListOfMaxIntervalSum() {
        Integer[] numbers1 = new Integer[]{-1, 4, -2, 3, -2, 3};
        Integer[] numbers2 = new Integer[]{-1, -2, -2};
        List<Integer> subList1 = new ArrayList<>(Arrays.asList(4, -2, 3, -2, 3));
        List<Integer> subList2 = new ArrayList<>(java.util.Collections.singletonList(-1));
        assert DynamicProgramming.subListOfMaxIntervalSum(
                new ArrayList<>(Arrays.asList(numbers1))).equals(subList1);
        assert DynamicProgramming.subListOfMaxIntervalSum(
                new ArrayList<>(Arrays.asList(numbers2))).equals(subList2);
    }

    @Test
    public void testPackBackTrack() {
        List<ItemInPack> items = new ArrayList<>();
        items.add(new ItemInPack("a", 2, 6));
        items.add(new ItemInPack("b", 2, 3));
        items.add(new ItemInPack("c", 6, 5));
        items.add(new ItemInPack("d", 5, 4));
        items.add(new ItemInPack("e", 4, 6));
        PackBackTrack pbt = new PackBackTrack(items, 10);
        pbt.run();
    }
}
