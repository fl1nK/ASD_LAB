package com.company;

public class Node<T extends Comparable<T>> {
	public static final int BLACK = 0;
    public static final int RED = 1;
    
	public T key;
	
	public int color;
	public Node<T> left;
	public Node<T> right;
	public Node<T> parent;
	public int numLeft = 0;
    public int numRight = 0;
	

	Node(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

	Node(T key){
        this();
        this.key = key;
	}
}
