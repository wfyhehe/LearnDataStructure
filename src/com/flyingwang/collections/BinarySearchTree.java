package com.flyingwang.collections;

import java.util.Iterator;

/**
 * Created by wfy on 17-12-24, good luck.
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    private TreeNode<E> hot;

    public BinarySearchTree(E rootData) {
        super(rootData);
        hot = getRoot();
    }


    public TreeNode<E> getSuccessor(TreeNode<E> node) {
        for (Iterator<TreeNode<E>> it = inOrderIterator(); it.hasNext(); ) {
            if (it.next().equals(node)) {
                return it.hasNext() ? it.next() : null;
            }
        }
        return null;
    }

    public TreeNode<E> insert(E e) {
        TreeNode<E> ret = search(e);
        if (ret == null) {
            if (e.compareTo(hot.getData()) < 0) {
                ret = insertAsLChild(hot, e);
            } else {
                ret = insertAsRChild(hot, e);
            }
        }
        return ret;
    }

    public TreeNode<E> search(E e) {
        return search(getRoot(), e);
    }

    public TreeNode<E> search(TreeNode<E> node, E e) {
        int compareResult = e.compareTo(node.getData());
        if (compareResult == 0) {
            return node;
        }
        if (node.getLChild() == null && node.getRChild() == null ||
                node.getLChild() == null && compareResult < 0 ||
                node.getRChild() == null && compareResult > 0) {
            hot = node;
            return null;
        }
        hot = compareResult < 0 ? node.getLChild() : node.getRChild();
        return search(hot, e);
    }

    public TreeNode<E> remove(E e) {
        return remove(search(e));
    }

    public TreeNode<E> remove(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        TreeNode<E> parent = node.getParent();
        TreeNode<E> lChild = node.getLChild();
        TreeNode<E> rChild = node.getRChild();
        TreeNode<E> candidate;
        boolean asLChild = parent != null &&
                parent.getLChild() != null &&
                parent.getLChild().equals(node);
        if (lChild == null || rChild == null) { // no child or single child
            candidate = lChild != null ? lChild : rChild;
            if (candidate != null) {
                candidate.setParent(parent);
            }
            if (asLChild) {
                parent.setLChild(candidate);
            } else if (parent != null) {
                parent.setRChild(candidate);
            } else {
                setRoot(candidate);
            }
            this.setSize(this.size() - 1);
            this.updateHeightAbove(node);
            return node;
        } else { // dual children
            candidate = getSuccessor(node);
            E temp = candidate.getData();
            candidate.setData(node.getData());
            node.setData(temp); // swap node and its successor
            return remove(candidate);
        }
    }
}
