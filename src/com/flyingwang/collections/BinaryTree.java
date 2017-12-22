package com.flyingwang.collections;

import java.util.ArrayList;
import java.util.Iterator;

import static com.flyingwang.collections.BinaryTree.Color.UNDETERMINED;

/**
 * Created by Administrator on 2017/12/21, good luck.
 */
public class BinaryTree<E> implements Iterable<BinaryTree.TreeNode<E>> {
    private TreeNode<E> root;
    private int size;

    public BinaryTree(E rootData) {
        this.root = new TreeNode<>(rootData);
        this.size = 1;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    private int updateHeight(TreeNode<E> node) {
        int lHeight = node.lChild != null ? node.lChild.height : 0;
        int rHeight = node.rChild != null ? node.rChild.height : 0;
        return node.height + Math.max(lHeight, rHeight);
    }

    private void updateHeightAbove(TreeNode<E> node) {
        node.height = updateHeight(node);
        if (node.parent != null) {
            updateHeightAbove(node.parent);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public TreeNode<E> insertAsLChild(TreeNode<E> node, E e) {
        node.insertAsLChild(e);
        size++;
        updateHeightAbove(node);
        return node.lChild;
    }

    public TreeNode<E> insertAsRChild(TreeNode<E> node, E e) {
        node.insertAsRChild(e);
        size++;
        updateHeightAbove(node);
        return node.rChild;
    }

    @Override
    public Iterator<TreeNode<E>> iterator() {
        return new PreOrderIterator();
    }

    public enum Color {
        UNDETERMINED,
        BLACK,
        RED
    }

    public static class TreeNode<E> {
        private E data;
        private TreeNode<E> lChild;
        private TreeNode<E> rChild;
        private TreeNode<E> parent;
        private int npl;
        private int height = 1;
        private Color color = UNDETERMINED;

        public TreeNode(E data) {
            this.data = data;
        }

        public TreeNode(E data, TreeNode<E> parent) {
            this.data = data;
            this.parent = parent;
        }

        public TreeNode<E> insertAsLChild(E e) {
            TreeNode<E> node = new TreeNode<>(e, this);
            this.lChild = node;
            return node;
        }

        public TreeNode<E> insertAsRChild(E e) {
            TreeNode<E> node = new TreeNode<>(e, this);
            this.rChild = node;
            return node;
        }

        int size() {
            int size = 1;
            if (lChild != null) {
                size += lChild.size();
            }
            if (rChild != null) {
                size += rChild.size();
            }
            return size;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", lChild=" + lChild +
                    ", rChild=" + rChild +
                    ", npl=" + npl +
                    ", height=" + height +
                    ", color=" + color +
                    '}';
        }
    }

    private class PreOrderIterator implements Iterator<TreeNode<E>> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public TreeNode<E> next() {
            return null;
        }
    }
}
