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
        for (int i = this.getLastNonleafIndex(); i >= 0; i--) {
            percolateDown(i);
        }
    }

    public CompleteHeap() {
        organizedElements = new ArrayList<>();
    }

    public static void main(String[] args) {
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

    private static int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private static int getLeftChildIndex(int index) {
        return (index + 1) * 2 - 1;
    }

    private static int getRightChildIndex(int index) {
        return (index + 1) * 2;
    }

    private int getLastNonleafIndex() {
        if (this.size() <= 1) {
            throw new NoSuchElementException();
        }
        return this.size() / 2 - 1;
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

    private void swap(int index1, int index2) {
        if (index1 >= this.size() || index2 >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        E temp = this.organizedElements.get(index1);
        this.organizedElements.set(index1, this.organizedElements.get(index2));
        this.organizedElements.set(index2, temp);
    }

    private void percolateUp(int index) {
        while (index > 0) {
            E current = organizedElements.get(index);
            E parent = organizedElements.get(getParentIndex(index));
            if (current.compareTo(parent) > 0) {
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            } else {
                break;
            }
        }
    }

    private void percolateDown(int index) {
        while (getLeftChildIndex(index) < this.size()) { // while not a leaf
            E current = organizedElements.get(index);
            E lChild = organizedElements.get(getLeftChildIndex(index));
            if (getRightChildIndex(index) < this.size()) { // rChild exists
                E rChild = organizedElements.get(getRightChildIndex(index));
                int indexOfMaxChild = lChild.compareTo(rChild) > 0 ?
                        getLeftChildIndex(index) : getRightChildIndex(index);
                if (current.compareTo(organizedElements.get(indexOfMaxChild)) < 0) {
                    swap(index, indexOfMaxChild);
                    index = indexOfMaxChild;
                } else { // in proper position now
                    break;
                }
            } else { // lChild only
                if (current.compareTo(lChild) < 0) {
                    swap(index, getLeftChildIndex(index));
                    index = getLeftChildIndex(index);
                } else {
                    break;
                }
            }
        }
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
