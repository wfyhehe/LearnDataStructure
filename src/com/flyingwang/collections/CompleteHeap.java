package com.flyingwang.collections;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Administrator on 2017/12/14, good luck.
 */
public class CompleteHeap<E extends Comparable<? super E>> implements Collection<E> {
    /**
     * This DataStructure is for sorting tasks by its priority.
     * getMax in O(1), deleteMax in O(log(n)), add in O(log(n)).
     * However bulkCreate cost O(n) instead of O(n*log(n)).
     * Really useful in dispatching tasks with different priorities.
     */
    private ArrayList<E> organizedElements;

    public CompleteHeap(Collection<E> elements) {
        this.organizedElements = new ArrayList<>(elements);
        for (int i = this.getLastNonLeafIndex(); i >= 0; i--) {
            percolateDown(i);
        }
    }

    public CompleteHeap() {
        organizedElements = new ArrayList<>();
    }

    public static <E extends Comparable<? super E>> void heapSort(List<E> list) {
        for (int i = CompleteHeap.getLastNonLeafIndex(list.size()); i >= 0; i--) {
            percolateDown(list, i);
        }
        for (int i = list.size() - 1; i > 0; i--) {
            Collections.swap(list, 0, i);
            percolateDown(list, 0, i);
        }

    }

    private static int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private static int getLeftChildIndex(int index) {
        return (index + 1) * 2 - 1;
    }

    private static int getRightChildIndex(int index) {
        return (index + 1) * 2;
    }

    private static int getLastNonLeafIndex(int size) {
        if (size <= 1) {
            throw new NoSuchElementException();
        }
        return size / 2 - 1;
    }

    private static <E extends Comparable<? super E>> void percolateUp(List<E> list, int index) {
        while (index > 0) {
            E current = list.get(index);
            E parent = list.get(getParentIndex(index));
            if (current.compareTo(parent) > 0) {
                Collections.swap(list, index, getParentIndex(index));
                index = getParentIndex(index);
            } else {
                break;
            }
        }
    }

    private static <E extends Comparable<? super E>> void percolateDown(List<E> list, int index) {
        percolateDown(list, index, list.size());
    }

    private static <E extends Comparable<? super E>> void percolateDown(
            List<E> list, int index, int size) {
        while (getLeftChildIndex(index) < size) { // while not a leaf
            E current = list.get(index);
            E lChild = list.get(getLeftChildIndex(index));
            if (getRightChildIndex(index) < size) { // rChild exists
                E rChild = list.get(getRightChildIndex(index));
                int indexOfMaxChild = lChild.compareTo(rChild) > 0 ?
                        getLeftChildIndex(index) : getRightChildIndex(index);
                if (current.compareTo(list.get(indexOfMaxChild)) < 0) {
                    Collections.swap(list, index, indexOfMaxChild);
                    index = indexOfMaxChild;
                } else { // in proper position now
                    break;
                }
            } else { // lChild only
                if (current.compareTo(lChild) < 0) {
                    Collections.swap(list, index, getLeftChildIndex(index));
                    index = getLeftChildIndex(index);
                } else {
                    break;
                }
            }
        }
    }

    private int getLastNonLeafIndex() {
        return getLastNonLeafIndex(this.size());
    }

    @Override
    public int size() {
        return organizedElements.size();
    }

    @Override
    public boolean isEmpty() {
        return organizedElements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return organizedElements.contains(o);
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

    private void percolateUp(int index) {
        percolateUp(this.organizedElements, index);
    }

    private void percolateDown(int index) {
        percolateDown(organizedElements, index);
    }

    @Override
    public boolean add(E e) {
        organizedElements.add(e);
        percolateUp(this.size() - 1);
        return true;
    }

    public E getMax() {
        return organizedElements.get(0);
    }

    public E deleteMax() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        E ret = organizedElements.get(0);
        if (this.size() == 1) {
            organizedElements.remove(0);
            return ret;
        }
        organizedElements.set(0, organizedElements.get(this.size() - 1));
        organizedElements.remove(this.size() - 1);
        this.percolateDown(0);
        return ret;
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
        return "CompleteHeap{" +
                organizedElements +
                '}';
    }
}
