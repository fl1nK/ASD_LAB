package com.company;

import java.util.function.Consumer;

public class ResursiveBST<T extends Comparable<T>> implements BinarySearchTree<T> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> root;


    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            return true;
        } else {
            return insert(root, element);
        }
    }


    private boolean insert(Node<T> current, T element) {
        if (element.compareTo(current.element) < 0) {
            if (current.left == null) {
                current.left = new Node<>(element);
                return true;
            } else {
                return insert(current.left, element);
            }
        } else if (element.compareTo(current.element) > 0) {
            if (current.right == null) {
                current.right = new Node<>(element);
                return true;
            } else {
                return insert(current.right, element);
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean contains(T elenment) {
        return conteins(root, elenment) != null;
    }

    private Node conteins(Node<T> current, T element) {
        if (current == null) {
            return current;
        } else if (element.compareTo(current.element) < 0) {
            return conteins(current.left, element);
        } else if (element.compareTo(current.element) > 0) {
            return conteins(current.right, element);
        } else {
            return current;
        }
    }


    @Override
    public void inOrderTraversal(Consumer consumer) {
        inOrderTraversal(consumer, root);
    }

    private void inOrderTraversal(Consumer consumer, Node current) {
        if (current != null) {
            inOrderTraversal(consumer, current.left);
            consumer.accept(current.element);
            inOrderTraversal(consumer, current.right);
        }
    }

    @Override
    public int sum() {
        return getSumElementsRecurse(this.root, 0);
    }

    private int getSumElementsRecurse(Node head, int result) {
        if (head != null) {
            result = getSumElementsRecurse(head.left, result) + (int) head.element + getSumElementsRecurse(head.right, result);
        }
        return result;
    }
    @Override
    public boolean remove(T element) {
        Node current;
        Node parent;

        // Находим удаляемый узел.
        current = conteins(root, element);

        if (current == null) {
            return false;
        }

        // Случай 1: Если нет детей справа, левый ребенок встает на место удаляемого.
        if (current.right == null) {
            if (root == null) {
                root = current.left;
            } else {
                int result = element.compareTo((T) current.element);
                if (result > 0) {
                    // Если значение родителя больше текущего,
                    // левый ребенок текущего узла становится левым ребенком родителя.
                    root.left = current.left;
                } else if (result < 0) { // Если значение родителя меньше текущего, // левый ребенок текущего узла становится правым ребенком родителя.
                    root.right = current.left;
                }
            }  // Случай 2: Если у правого ребенка нет детей слева // то он занимает место удаляемого узла.
        }else if (current.right.left == null) {
                    current.right.left = current.left;
                    if (root == null) {
                            root = current.right;
                    } else { int result = element.compareTo((T) current.element);
                    if (result > 0)
                    {
                        // Если значение родителя больше текущего,
                        // правый ребенок текущего узла становится левым ребенком родителя.
                        root.left = current.left;
                    }
            else
                    if (result < 0) { // Если значение родителя меньше текущего, // правый ребенок текущего узла становится правым ребенком родителя.
                        root.right = current.right; } } } // Случай 3: Если у правого ребенка есть дети слева, крайний левый ребенок // из правого поддерева заменяет удаляемый узел.
        else { // Найдем крайний левый узел.
            Node leftmost = current.right.left;
            Node leftmostParent = current.right;
            while (leftmost.left != null) {
                leftmostParent = leftmost;
                leftmost = leftmost.left; } // Левое поддерево родителя становится правым поддеревом крайнего левого узла.
            leftmostParent.left = leftmost.left; // Левый и правый ребенок текущего узла становится левым и правым ребенком крайнего левого.
            leftmost.left = current.left; leftmost.right = current.right;
            if (root == null) {
                root = leftmost;
            } else {
                int result = element.compareTo((T) current.element);
                if (result > 0)
                        {
                            // Если значение родителя больше текущего,
                            // крайний левый узел становится левым ребенком родителя.
                            root.left = leftmost;
                        }
            else if (result < 0) {
                            // Если значение родителя меньше текущего,
                            // крайний левый узел становится правым ребенком родителя.
                            root.right = leftmost;
                        }
                    }
                }

                return true;
    }
}