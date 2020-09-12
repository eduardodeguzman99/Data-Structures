 /***************************************************************/
 /* Program Name: Lab 3                                         */
 /*                                                             */
 /* Student Name: Eduardo Guzmán                                */
 /* Semester: Fall 2019                                         */
 /* Class-Section: CoSc 20203 - 015                             */
 /* Instructor: Dr.Rinewalt                                     */
/****************************************************************/ 
import java.util.*;

import LinkedList.Node;
public class BinarySearchTree<E extends Comparable<E>> {
		//---------------- nested Node class ----------------
		private static class Node<E> { 
			private E data; 				// reference to the data stored at this node
			private Node<E> left; 			// reference to the left child
			private Node<E> right; 			// reference to the right child
			private Node<E> parent; 		// reference to the parent
			public Node(E e) { 
				data = e;
				left = right = parent = null;
			} 
			E getData() { return data; }
			Node<E> getLeft() { return left; }
			Node<E> getRight() { return right; }
			Node<E> getParent() { return parent; }
		} 

		private Node<E> root = null; 		// root of the tree (or null if empty)

		public BinarySearchTree() { }
		
		public Node<E> getRoot() {
			return root;
		}
		
		// insert a new value into a binary search tree
		// if the value already exists, the tree is not changed
		public E insert(E data) {
			Node<E> p = new Node<E>(data);
			if (root == null)				// if the tree is empty, put this value in the root
				root = p;
			else {
				Node<E> q = find(data);
				if (data.compareTo(q.data) < 0) {		// it should be in the left subtree
					p.parent = q;
					q.left = p;
				}
				else if (data.compareTo(q.data) > 0) {	// it should be in the right subtree
					p.parent = q;
					q.right = p;
				}
				else ;						// duplicate data -- do nothing
			}
			return data;
		}
		
		// find a value in a binary search tree
		// if it doesn't exist, return the node
		// that should be its parent
		public Node<E> find(E data) {
			if (root == null) return null;
			Node<E> p = root;
			while (p != null) {
				if (data.compareTo(p.data) < 0)			// it should be in the left subtree
					if (p.left == null)
						return p;
					else
						p = p.left;
				else if (data.compareTo(p.data) > 0)	// it should be in the right subtree
					if (p.right == null)
						return p;
					else
						p = p.right;
				else
					return p;							// found it
			}
			return p;									// it should be in a subtree of this node
		}
		
		// perform an inorder traversal printing the values 
		public void inorder(Node<E> p) {
			if (p == null) return;
			inorder(p.left);
			System.out.println(p.data + " ");
			inorder(p.right);
		}
		
		// find the preorder successor of a given value
		public E preOrderNext(E data) {
			Node<E> p = find(data);
			if (p == null) return null;
			if (p.left != null) return p.left.data;
			if (p.right != null) return p.right.data;
			// if it is a leaf, use the parent link to move up the tree
			Node<E> q = p.parent;
			while (q != null && (q.right == p || q.left == p && q.right == null)) {
				p = q;
				q = p.parent;
			}
			if (q == null) return null;
			return q.right.data;
		}
		
		
//##################################################################################################
//##################################################################################################
		
		public void preOrder(Node<E> p) {
			if (p == null) return; 
			
			//PreOrder is: Root, Left Subtree, Right Subtree
			System.out.print(p.data + " ");
			preOrder(p.left);
			preOrder(p.right);
		}
		
		public void postOrder(Node<E> p) {
			if (p == null) return; 
			
			//PostOrder is: Left Subtree, Right Subtree, Root
			postOrder(p.left);
			postOrder(p.right);
			System.out.print(p.data + " ");
		}
		
	
			
		   public void levelOrder(){ 
		        int h = getHeight(root); 
		  
		        //Visit nodes on level i, then traverse level i+1
		        for (int i=0; i<=h; i++) 
		            printLevelOrder(root, i); 
		    } 
			
			
		   void printLevelOrder (Node<E> p ,int level) { 
		        if (root == null) return; 
		        
		        if (level == 0) 
		            System.out.print(p.data + " "); 
		        else if (level > 0) { 
		            printLevelOrder(p.left, level-1); 
		            printLevelOrder(p.right, level-1); 
		        } 
		    } 
		   
		   
				
		public Node<E> inOrderNext(E data) {
			Node<E> p = find(data);
			
			//We want to find the successor to p, so it must be on the right side of p
			Node<E> q = p.right;
			
			//Keep Traversing left until we can't anymore
			while(q.left != null) {
				q = q.left;
			}
			return q; // q is the in-order successor of p 
		}
		
		public E delete(Node<E> p) {
			
			E data = p.data;
			
			// Case 0: Leaf
			// Set The appropriate parent link to be null
			if(p.left==null && p.right==null) {
				if(p.parent.right == p) p.parent.right = null;	
				else p.parent.left = null;
				
			}
			
			// Case 1: One non-empty subtree
			// Set The appropriate parent link to skip over the integer we want to delete
			else if(p.left==null) {
				if(p.parent.right == p) p.parent.right = p.right;	
				else p.parent.left = p.right;
			}
			else if(p.right==null) {
				if(p.parent.left == p) p.parent.left = p.left;	
				else p.parent.right = p.left;
			}
			
			//Return The value we deleted for good practice 
			return data;
		}
		
		public int getHeight(Node<E> p) {
			if(p == null) return -1;
			
			//Consider The Maximum heights of the Left and Right Subtrees 
			int leftMax = getHeight(p.left);
			int rightMax = getHeight(p.right);
			
			if(leftMax >= rightMax) return leftMax + 1;
			return rightMax + 1;
		}
		
		
		
		
		public static void main(String[] args) {

			Scanner scr = new Scanner(System.in);
			BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
			Boolean bool = true;
			
			while(bool == true) {
				
				System.out.println("");
				String comm = scr.next();
			
				// Add [Integer] to the Binary Tree
				if(comm.equals("ADD")) {
					bst.insert(scr.nextInt());
				}
				
				//Delete [Integer] from the Binary Tree
				else if(comm.equals("DEL")) {			
					Node <Integer> p = bst.find(scr.nextInt());
					
					// Special Case where Both Subtrees non-empty
					if(p.left != null && p.right!=null) {
						//Set q to be the in order successor to p
						Node <Integer> q = bst.inOrderNext(p.data);
					
						//Copy q Data to p 
						p.data = q.data;
					
						//Delete q Instead
						bst.delete(q);
					}
					//Else Handle Case With Normal Delete Method for p
					else bst.delete(p);
				}
				
				//Return The Height of the Binary Tree 
				else if(comm.equals("HGT")) {
					Node<Integer> p = bst.getRoot();
					System.out.println(bst.getHeight(p) + "\n");
				}
				
				// Print Out PreOrder Traversal 
				else if(comm.equals("PRE")) {
					bst.preOrder(bst.getRoot());
					System.out.println();
				}
				
				//Print Out The PostOrder Traversal
				else if(comm.equals("POST")) {
					bst.postOrder(bst.getRoot());
					System.out.println();
				}
				
				//Print Out The InOrder Traversal
				else if(comm.equals("IN")) {
					bst.inorder(bst.getRoot());
				}
				
				//Print Out Level Order Traversal
				else if(comm.equals("LEVEL")) {
					bst.levelOrder();
					System.out.println();
				}
				
				//Clear The Entire Binary Tree 
				else if(comm.equals("CLR")) {
					bst.root = null;
				}		
				
				//End The Program
				else if (comm.equals("END")) {
					bool = false; //set while loop false
				}
				
			}
			scr.close();
			System.out.println("Program Has Been Terminated");
		
		}
}