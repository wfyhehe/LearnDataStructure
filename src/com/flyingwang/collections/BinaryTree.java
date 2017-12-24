package com.flyingwang.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.flyingwang.collections.BinaryTree.Color.UNDETERMINED;

/**
 * Created by Administrator on 2017/12/21, good luck.
 */
public class BinaryTree<E> implements Iterable<BinaryTree.TreeNode<E>> {
    private TreeNode<E> root;
    private int size;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode<E> root) {
        this.root = root;
        this.size = 1;
    }

    public BinaryTree(E rootData) {
        this.root = new TreeNode<>(rootData);
        this.size = 1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }

    int updateHeight(TreeNode<E> node) {
        int lHeight = node.lChild != null ? node.lChild.height : 0;
        int rHeight = node.rChild != null ? node.rChild.height : 0;
        return 1 + Math.max(lHeight, rHeight);
    }

    public void updateHeightAbove(TreeNode<E> node) {
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
        return preOrderIterator();
    }

    public Iterator<TreeNode<E>> preOrderIterator() {
        return new PreOrderIterator();
    }

    public Iterator<TreeNode<E>> inOrderIterator() {
        return new InOrderIterator();
    }

    public Iterator<TreeNode<E>> postOrderIterator() {
        return new PostOrderIterator();
    }

    public Iterator<TreeNode<E>> levelIterator() {
        return new LevelIterator();
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

        public void setlChild(TreeNode<E> lChild) {
            this.lChild = lChild;
        }

        public void setrChild(TreeNode<E> rChild) {
            this.rChild = rChild;
        }

        public TreeNode<E> getLChild() {
            return lChild;
        }

        public void setLChild(TreeNode<E> lChild) {
            this.lChild = lChild;
        }

        public TreeNode<E> getRChild() {
            return rChild;
        }

        public void setRChild(TreeNode<E> rChild) {
            this.rChild = rChild;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public void setParent(TreeNode<E> parent) {
            this.parent = parent;
        }

        public int getNpl() {
            return npl;
        }

        public void setNpl(int npl) {
            this.npl = npl;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
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
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> current;

        PreOrderIterator() {
            current = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public TreeNode<E> next() {
            if (current == null) {
                current = stack.pop();
            }
            TreeNode<E> ret = current;
            if (current.rChild != null) {
                stack.push(current.rChild);
            }
            current = current.lChild;
            return ret;
        }
    }

    private class InOrderIterator implements Iterator<TreeNode<E>> {
        Stack<TreeNode<E>> stack = new Stack<>();

        InOrderIterator() {
            goAlongLeftBranch(root);
        }

        private void goAlongLeftBranch(TreeNode<E> node) {
            TreeNode<E> current = node;
            while (current != null) {
                stack.push(current);
                current = current.lChild;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode<E> next() {
            TreeNode<E> node = stack.pop();
            if (node.rChild != null) {
                goAlongLeftBranch(node.rChild);
            }
            return node;
        }
    }

    private class PostOrderIterator implements Iterator<TreeNode<E>> {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> last;

        PostOrderIterator() {
            goAlongLeftBranch(root);
        }

        private void goAlongLeftBranch(TreeNode<E> node) {
            TreeNode<E> current = node;
            while (current != null) {
                stack.push(current);
                current = current.lChild;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode<E> next() {
            TreeNode<E> node = stack.peek();
            if (node.lChild == null && node.rChild == null ||
                    node.rChild == null && node.lChild.equals(last) ||
                    node.rChild != null && node.rChild.equals(last)) {
                stack.pop();
                last = node;
                return node;
            }
            if (node.rChild != null) {
                goAlongLeftBranch(node.rChild);
            }
            return next();
        }
    }

    private class LevelIterator implements Iterator<TreeNode<E>> {
        Queue<TreeNode<E>> queue = new LinkedList<>();

        LevelIterator() {
            queue.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public TreeNode<E> next() {
            TreeNode<E> node = queue.poll();
            if (node.lChild != null) {
                queue.offer(node.lChild);
            }
            if (node.rChild != null) {
                queue.offer(node.rChild);
            }
            return node;
        }
    }
}
