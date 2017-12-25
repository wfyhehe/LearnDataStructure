package com.flyingwang.collections;

import java.util.*;

/**
 * Created by wfy on 17-12-24, good luck.
 */
public class HuffmanTree<E> extends BinaryTree<E> {
    public HuffmanTree(Stack<HuffmanNode<E>> nodes) {
        super.setSize(nodes.size());
        while (nodes.size() > 1) {
            nodes.sort((o1, o2) -> o2.weight - o1.weight); // 按权重从大到小
            HuffmanNode<E> left = nodes.pop();
            HuffmanNode<E> right = nodes.pop(); // 取出weight最小的两个
            HuffmanNode<E> parent = new HuffmanNode<>(null, left.weight + right.weight);
            left.setParent(parent); // 新建parent，weight为两孩子之和
            right.setParent(parent);
            parent.setLChild(left);
            parent.setRChild(right);
            nodes.push(parent); // 将其存入stack
        } // 仅有最后一个结点，退出循环
        HuffmanNode<E> root = nodes.pop();
        super.setRoot(root);
    }

    public void display() {
        Queue<HuffmanNode<E>> queue = new LinkedList<>();
        queue.offer((HuffmanNode<E>) this.getRoot());
        while (!queue.isEmpty()) {
            HuffmanNode<E> node = queue.poll();
            if(node.getData()!=null) {
                System.out.println(node);
            }
            List<Integer> leftCodeSequence = new ArrayList<>(node.codeSequence);
            leftCodeSequence.add(0);
            List<Integer> rightCodeSequence = new ArrayList<>(node.codeSequence);
            rightCodeSequence.add(1);
            HuffmanNode<E> left = (HuffmanNode<E>) node.getLChild();
            HuffmanNode<E> right = (HuffmanNode<E>) node.getRChild();
            if (left != null) {
                left.codeSequence = leftCodeSequence;
                queue.offer(left);
            }
            if (right != null) {
                right.codeSequence = rightCodeSequence;
                queue.offer(right);
            }
        }
    }

    public static class HuffmanNode<E> extends TreeNode<E> {
        private List<Integer> codeSequence = new ArrayList<>();
        private int weight;

        public HuffmanNode(E data, int weight, HuffmanNode<E> parent) {
            super(data, parent);
            this.weight = weight;
        }

        public HuffmanNode(E data, int weight) {
            this(data, weight, null);
        }

        @Override
        public String toString() {
            StringBuilder codeSequence = new StringBuilder();
            for (Integer code : this.codeSequence) {
                codeSequence.append(code);
            }
            return "HuffmanNode{" +
                    "data=" + getData() +
                    ", codeSequence=" + codeSequence.toString() +
                    ", weight=" + weight +
                    '}';
        }
    }
}
