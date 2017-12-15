package com.flyingwang.collections;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Administrator on 2017/12/15, good luck.
 */
public class UnionFindSet<E> implements Collection<E> {
    /**
     * UnionFindSet is for checking whether two elements are related (directly or indirectly).
     * There are two kinds of methods: union, find
     */
    private ArrayList<TreeNode<E>> nodes = new ArrayList<>();

    public UnionFindSet(List<Collection<E>> separateElements) {
        for (Collection<E> collection : separateElements) {
            TreeNode<E> root = null;
            for (E element : collection) {
                if (root == null) {
                    root = new TreeNode<>(element);
                    nodes.add(root);
                } else {
                    nodes.add(new TreeNode<>(element, root));
                }
            }
        }
    }

    public UnionFindSet(Collection<E> elements) {
        for (E element : elements) {
            nodes.add(new TreeNode<>(element));
        }
    }

    public static void main(String[] args) {
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

    public TreeNode<E> findNode(E e) {
        for (TreeNode<E> node : nodes) {
            if (node.element.equals(e)) {
                return node;
            }
        }
        return null;
    }

    public E find(TreeNode<E> node) {
        return node.findRoot().element;
    }

    public void union(E e1, E e2) {
        TreeNode<E> node1 = this.findNode(e1);
        TreeNode<E> node2 = findNode(e2);
        if (node1 == null || node2 == null) {
            throw new NoSuchElementException();
        }
        TreeNode<E> root1 = node1.findRoot();
        TreeNode<E> root2 = node2.findRoot();
        if (root1.equals(root2)) {
            return;
        }
        if (root1.getSize() > root2.getSize()) {
            root2.setParent(root1.findRoot());
            root1.setSize(root1.getSize() + root2.getSize());
        } else {
            root1.setParent(root2.findRoot());
            root2.setSize(root1.getSize() + root2.getSize());
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return nodes.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        throw new NotImplementedException();
    }

    @Override
    public Object[] toArray() {
        throw new NotImplementedException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
    }

    @Override
    public boolean add(E e) {
        throw new NotImplementedException();
    }

    @Override
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        ArrayList<ArrayList<TreeNode<E>>> sets = new ArrayList<>();
        for (TreeNode<E> node : nodes) {
            boolean[] findTeam = {false};
            sets.forEach((set) -> {
                if (node.findRoot().equals(set.get(0).findRoot())) {
                    set.add(node);
                    findTeam[0] = true;
                }
            });
            if (!findTeam[0]) {
                ArrayList<TreeNode<E>> temp = new ArrayList<>();
                temp.add(node);
                sets.add(temp);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (ArrayList<TreeNode<E>> set : sets) {
            sb.append("[");
            for (TreeNode<E> treeNode : set) {
                sb.append(treeNode.element.toString());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("],");
        }
        sb.deleteCharAt(sb.length() - 1);
        return "UnionFindSet{" +
                sb.toString() +
                '}';
    }

    private static class TreeNode<T> {
        private TreeNode<T> parent;
        private T element;
        private int size;

        TreeNode(T element, TreeNode<T> parent) {
            this.element = element;
            this.parent = parent;
            TreeNode<T> root = this.findRoot();
            root.setSize(root.getSize() + 1);
        }

        TreeNode(T element) {
            this.element = element;
            this.parent = null;
            this.size = 1;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        int getSize() {
            if (this.parent == null) {
                return size;
            }
            return this.findRoot().getSize();
        }

        void setSize(int size) {
            if (this.parent == null) {
                this.size = size;
            } else {
                this.findRoot().setSize(size);
            }
        }

        TreeNode<T> findRoot() {
            TreeNode<T> current = this;
            if (parent == null) {
                return this;
            }
            ArrayList<TreeNode<T>> nodesInPath = new ArrayList<>();
            while (current.parent != null) {
                nodesInPath.add(current);
                current = current.parent;
            }
            for (TreeNode<T> node : nodesInPath) {
                node.parent = current;
            }
            return current;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TreeNode<?> treeNode = (TreeNode<?>) o;

            if (parent != null ? !parent.equals(treeNode.parent) : treeNode.parent != null)
                return false;
            return element != null ? element.equals(treeNode.element) : treeNode.element == null;
        }

        @Override
        public int hashCode() {
            int result = parent != null ? parent.hashCode() : 0;
            result = 31 * result + (element != null ? element.hashCode() : 0);
            return result;
        }
    }
}
