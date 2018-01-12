package com.flyingwang.collections;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by wfy on 17-12-24, good luck.
 */
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> {

    public AVLTree(E rootData) {
        super(rootData);
    }

    private boolean isBalanced(TreeNode<E> node) {
        int lHegiht = node.getLChild() != null ? node.getLChild().getHeight() : 0;
        int rHegiht = node.getRChild() != null ? node.getRChild().getHeight() : 0;
        return Math.abs(lHegiht - rHegiht) < 2;
    }

    @Override
    public TreeNode<E> insert(E e) {
        TreeNode<E> node = super.insert(e);
        TreeNode<E> current = node;
        while(current!=null) {
            if(!isBalanced(current)) {
                // TODO 不想写了
                throw new NotImplementedException();
            } else {
                this.updateHeight(current);
            }
            current=current.getParent();
        }
        return node;
    }


}
