package com.flyingwang.collections;

/**
 * Created by Administrator on 2017/12/21, good luck.
 */
public class BinaryTree<E> {
    public enum Color {
        BLACK,
        RED
    }

    public class TreeNode<E> {
        private E data;
        private TreeNode<E> lChild;
        private TreeNode<E> rChild;
        private TreeNode<E> parent;
        private int npl;
        private int depth;
        private Color color;

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
    }
}
