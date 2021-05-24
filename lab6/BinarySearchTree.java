package com.company;

import java.util.function.Consumer;

public interface BinarySearchTree<T extends Comparable<T>>{

    boolean insert(T element);
    boolean remove(T element);
    boolean contains(T elenment);
    int sum();
    void inOrderTraversal(Consumer consumer);
}
