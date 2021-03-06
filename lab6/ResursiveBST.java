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

        // ?????????????? ?????????????????? ????????.
        current = conteins(root, element);

        if (current == null) {
            return false;
        }

        // ???????????? 1: ???????? ?????? ?????????? ????????????, ?????????? ?????????????? ???????????? ???? ?????????? ????????????????????.
        if (current.right == null) {
            if (root == null) {
                root = current.left;
            } else {
                int result = element.compareTo((T) current.element);
                if (result > 0) {
                    // ???????? ???????????????? ???????????????? ???????????? ????????????????,
                    // ?????????? ?????????????? ???????????????? ???????? ???????????????????? ?????????? ???????????????? ????????????????.
                    root.left = current.left;
                } else if (result < 0) { // ???????? ???????????????? ???????????????? ???????????? ????????????????, // ?????????? ?????????????? ???????????????? ???????? ???????????????????? ???????????? ???????????????? ????????????????.
                    root.right = current.left;
                }
            }  // ???????????? 2: ???????? ?? ?????????????? ?????????????? ?????? ?????????? ?????????? // ???? ???? ???????????????? ?????????? ???????????????????? ????????.
        }else if (current.right.left == null) {
                    current.right.left = current.left;
                    if (root == null) {
                            root = current.right;
                    } else { int result = element.compareTo((T) current.element);
                    if (result > 0)
                    {
                        // ???????? ???????????????? ???????????????? ???????????? ????????????????,
                        // ???????????? ?????????????? ???????????????? ???????? ???????????????????? ?????????? ???????????????? ????????????????.
                        root.left = current.left;
                    }
            else
                    if (result < 0) { // ???????? ???????????????? ???????????????? ???????????? ????????????????, // ???????????? ?????????????? ???????????????? ???????? ???????????????????? ???????????? ???????????????? ????????????????.
                        root.right = current.right; } } } // ???????????? 3: ???????? ?? ?????????????? ?????????????? ???????? ???????? ??????????, ?????????????? ?????????? ?????????????? // ???? ?????????????? ?????????????????? ???????????????? ?????????????????? ????????.
        else { // ???????????? ?????????????? ?????????? ????????.
            Node leftmost = current.right.left;
            Node leftmostParent = current.right;
            while (leftmost.left != null) {
                leftmostParent = leftmost;
                leftmost = leftmost.left; } // ?????????? ?????????????????? ???????????????? ???????????????????? ???????????? ???????????????????? ???????????????? ???????????? ????????.
            leftmostParent.left = leftmost.left; // ?????????? ?? ???????????? ?????????????? ???????????????? ???????? ???????????????????? ?????????? ?? ???????????? ???????????????? ???????????????? ????????????.
            leftmost.left = current.left; leftmost.right = current.right;
            if (root == null) {
                root = leftmost;
            } else {
                int result = element.compareTo((T) current.element);
                if (result > 0)
                        {
                            // ???????? ???????????????? ???????????????? ???????????? ????????????????,
                            // ?????????????? ?????????? ???????? ???????????????????? ?????????? ???????????????? ????????????????.
                            root.left = leftmost;
                        }
            else if (result < 0) {
                            // ???????? ???????????????? ???????????????? ???????????? ????????????????,
                            // ?????????????? ?????????? ???????? ???????????????????? ???????????? ???????????????? ????????????????.
                            root.right = leftmost;
                        }
                    }
                }

                return true;
    }
}