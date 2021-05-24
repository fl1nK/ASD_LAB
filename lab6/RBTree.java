package com.company;

public class RBTree<T extends Comparable<T>> {
	
	private Node <T> nil = new Node<T>();
	private Node<T> root = nil;
	
	public RBTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }
	
	private void leftRotate(Node<T> x){


		leftRotateComplete(x);

		Node<T> y;
		y = x.right;
		x.right = y.left;

		
		if (!isNil(y.left))
			y.left.parent = x;
		y.parent = x.parent;

		
		if (isNil(x.parent))
			root = y;

		
		else if (x.parent.left == x)
			x.parent.left = y;

		else
			x.parent.right = y;

		
		y.left = x;
		x.parent = y;
	}



	private void leftRotateComplete(Node x){

		
		if (isNil(x.left) && isNil(x.right.left)){
			x.numLeft = 0;
			x.numRight = 0;
			x.right.numLeft = 1;
		}

		
		else if (isNil(x.left) && !isNil(x.right.left)){
			x.numLeft = 0;
			x.numRight = 1 + x.right.left.numLeft +
					x.right.left.numRight;
			x.right.numLeft = 2 + x.right.left.numLeft +
					  x.right.left.numRight;
		}

		
		else if (!isNil(x.left) && isNil(x.right.left)){
			x.numRight = 0;
			x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

		}
		
		else{
			x.numRight = 1 + x.right.left.numLeft +
				     x.right.left.numRight;
			x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
			x.right.left.numLeft + x.right.left.numRight;
		}

	}


	private void rightRotate(Node<T> y){

		
		rightRotateComplete(y);

        
        Node<T> x = y.left;
        y.left = x.right;

        if (!isNil(x.right))
            x.right.parent = y;
        x.parent = y.parent;


        if (isNil(y.parent))
            root = x;


        else if (y.parent.right == y)
            y.parent.right = x;

      
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

	}


	private void rightRotateComplete(Node<T> y){

		
		if (isNil(y.right) && isNil(y.left.right)){
			y.numRight = 0;
			y.numLeft = 0;
			y.left.numRight = 1;
		}

		
		else if (isNil(y.right) && !isNil(y.left.right)){
			y.numRight = 0;
			y.numLeft = 1 + y.left.right.numRight +
				  y.left.right.numLeft;
			y.left.numRight = 2 + y.left.right.numRight +
				  y.left.right.numLeft;
		}

		
		else if (!isNil(y.right) && isNil(y.left.right)){
			y.numLeft = 0;
			y.left.numRight = 2 + y.right.numRight +y.right.numLeft;

		}

		
		else{
			y.numLeft = 1 + y.left.right.numRight +
				  y.left.right.numLeft;
			y.left.numRight = 3 + y.right.numRight +
				  y.right.numLeft +
			y.left.right.numRight + y.left.right.numLeft;
		}

	}
	public void insert(T key) {
        insert(new Node<T>(key));
    }

	private void insert(Node<T> z) {
		
			Node<T> y = nil;
			Node<T> x = root;

			while (!isNil(x)){
				y = x;

			
				if (z.key.compareTo(x.key) < 0){

					
					x.numLeft++;
					x = x.left;
				}
				else{

					
					x.numRight++;
					x = x.right;
				}
			}
			
			z.parent = y;

			if (isNil(y))
				root = z;
			else if (z.key.compareTo(y.key) < 0)
				y.left = z;
			else
				y.right = z;

			
			z.left = nil;
			z.right = nil;
			z.color = Node.RED;

			
			balance(z);

	}


	
	private void balance(Node<T> z){

		Node<T> y = nil;
		
		while (z.parent.color == Node.RED){

			
			if (z.parent == z.parent.parent.left){

				
				y = z.parent.parent.right;

				
				if (y.color == Node.RED){
					z.parent.color = Node.BLACK;
					y.color = Node.BLACK;
					z.parent.parent.color = Node.RED;
					z = z.parent.parent;
				}
				
				else if (z == z.parent.right){

					
					z = z.parent;
					leftRotate(z);
				}

				
				else{
					
					z.parent.color = Node.BLACK;
					z.parent.parent.color = Node.RED;
					rightRotate(z.parent.parent);
				}
			}

			
			else{

				
				y = z.parent.parent.left;

				
				if (y.color == Node.RED){
					z.parent.color = Node.BLACK;
					y.color = Node.BLACK;
					z.parent.parent.color = Node.RED;
					z = z.parent.parent;
				}
				else if (z == z.parent.left){
					
					z = z.parent;
					rightRotate(z);
				}
				
				else{
					
					z.parent.color = Node.BLACK;
					z.parent.parent.color = Node.RED;
					leftRotate(z.parent.parent);
				}
			}
		}

	root.color = Node.BLACK;

	}
	public Node<T> treeMinimum(Node<T> node){

		// while there is a smaller key, keep going left
		while (!isNil(node.left))
			node = node.left;
		return node;
	}
	public Node<T> treeSuccessor(Node<T> x){

		if (!isNil(x.left) )
			return treeMinimum(x.right);

		Node<T> y = x.parent;

		
		while (!isNil(y) && x == y.right){
			
			x = y;
			y = y.parent;
		}
		
		return y;
	}
	
	public void remove(Node <T> v) {
		Node<T> z = search(v.key);

		Node<T> x = nil;
		Node<T> y = nil;

		if (isNil(z.left) || isNil(z.right))
			y = z;

		else y = treeSuccessor(z);

		if (!isNil(y.left))
			x = y.left;
		else
			x = y.right;
		x.parent = y.parent;

		if (isNil(y.parent))
			root = x;

		else if (!isNil(y.parent.left) && y.parent.left == y)
			y.parent.left = x;

		else if (!isNil(y.parent.right) && y.parent.right == y)
			y.parent.right = x;

		if (y != z){
			z.key = y.key;
		}


		fixNodeData(x,y);

		if (y.color == Node.BLACK)
			removeFixup(x);
	}
	private void removeFixup(Node<T> x){

		Node<T> w;


		while (x != root && x.color == Node.BLACK){

			if (x == x.parent.left){


				w = x.parent.right;


				if (w.color == Node.RED){
					w.color = Node.BLACK;
					x.parent.color = Node.RED;
					leftRotate(x.parent);
					w = x.parent.right;
				}


				if (w.left.color == Node.BLACK &&
							w.right.color == Node.BLACK){
					w.color = Node.RED;
					x = x.parent;
				}

				else{

					if (w.right.color == Node.BLACK){
						w.left.color = Node.BLACK;
						w.color = Node.RED;
						rightRotate(w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = Node.BLACK;
					w.right.color = Node.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			}

			else{


				w = x.parent.left;

				if (w.color == Node.RED){
					w.color = Node.BLACK;
					x.parent.color = Node.RED;
					rightRotate(x.parent);
					w = x.parent.left;
				}

				if (w.right.color == Node.BLACK &&
							w.left.color == Node.BLACK){
					w.color = Node.RED;
					x = x.parent;
				}

				else{

					 if (w.left.color == Node.BLACK){
						w.right.color = Node.BLACK;
						w.color = Node.RED;
						leftRotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = Node.BLACK;
					w.left.color = Node.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = Node.BLACK;
	}
	private void fixNodeData(Node<T> x, Node<T> y){

		Node<T> current = nil;
		Node<T> track = nil;



		if (isNil(x)){
			current = y.parent;
			track = y;
		}

		else{
			current = x.parent;
			track = x;
		}


		while (!isNil(current)){

			if (y.key != current.key) {

				if (y.key.compareTo(current.key) > 0)
					current.numRight--;
				if (y.key.compareTo(current.key) < 0)
					current.numLeft--;
			}

			else{

				if (isNil(current.left))
					current.numLeft--;
				else if (isNil(current.right))
					current.numRight--;
				else if (track == current.right)
					current.numRight--;
				else if (track == current.left)
					current.numLeft--;
			}

			track = current;
			current = current.parent;

		}

	}
	
	
	private boolean isNil(Node<T> node){

		
		return node == nil;

	}
	
	public Node<T> search(T key) {
		Node<T> current = root;

		while (!isNil(current)){
			if (current.key.equals(key))
				return current;
			else if (current.key.compareTo(key) < 0)
				current = current.right;
			else
				current = current.left;
		}

		return null;
	}



}
