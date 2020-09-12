
 /***************************************************************/
 /* Program Name: Lab 2                                         */
 /*                                                             */
 /* Student Name: Eduardo Guzmán                                */
 /* Semester: Fall 2019                                         */
 /* Class-Section: CoSc 20203 - 015                             */
 /* Instructor: Dr.Rinewalt                                     */
/****************************************************************/ 

import java.util.Scanner;

public class LinkedList<E extends Comparable <E>>  {
	//---------------- nested Node class ----------------	
	private static class Node<E>{

		private E data;				// reference to the data stored at this node
		private Node<E> next;			// reference to the subsequent node in the list
		public Node(E e, Node<E> n) {
			data = e;
			next = n;
		}
		public E getData( ) {
			return data; }
		public Node<E> getNext( ) {
			return next; }
		public void setNext(Node<E> n) {
			next = n; }
	}
	
	// instance variables of the LinkedList
	private Node<E> head = null;		// head node of the list (or null if empty)
	private Node<E> tail = null;		// last node of the list (or null if empty)
	private int size = 0;				// number of nodes in the list

	public LinkedList( ) { }			// constructs an initially empty list

	// access methods
	public int size( ) {
		return size; }
	
	public boolean isEmpty( ) {
		return size == 0; }
	
	public E first( ) {			// returns (but does not remove) the first data
		if (isEmpty( ))
			return null;
		return head.getData( );
	}
	
	public E last( ) {					// returns (but does not remove) the last data
		if (isEmpty( ))
			return null;
		return tail.getData( );
	}
	
	// update methods
	public void addFirst(E e) {		// adds data e to the front of the list
		head = new Node<>(e, head);	// create and link a new node
		if (size == 0)
			tail = head;				// special case: new node becomes tail also
		size++;
	}
	
	public void addLast(E e) {		// adds data e to the end of the list
		Node<E> newest = new Node<>(e, null); // node will eventually be the tail
		if (isEmpty( ))
			head = newest;				// special case: previously empty list
		else
			tail.setNext(newest);		// new node after existing tail
		tail = newest;					// new node becomes the tail
		size++;
	}
	
	public E removeFirst( ) {		// removes and returns the first data
		if (isEmpty( )) return null;	// nothing to remove
		E answer = head.getData( );
		head = head.getNext( );		// will become null if list had only one node
		size--;
		if (size == 0)
			tail = null;				// special case as list is now empty
		return answer;
	}
	
	public E removeLast( ) {			// removes and returns the last data
		if (isEmpty( )) return null;	// nothing to remove
		E answer = tail.getData( );
		if (head == tail) {				// check for only one item on the list
			head = null;
			tail = null;
			size = 0;
			return answer;
		}
		Node<E> p = head;				// find the next to last item
		Node<E> prev;
		do {
			prev = p;
			p = p.next;
		} while (p != tail);
		tail = prev;					// make the next to last item the last item
		prev.next = null;
		size--;
		return answer;
	}
	
	public int indexOf(E e) {			// return the position of a value in the list
		if (isEmpty()) return -1;
		Node<E> p = head;
		for (int i = 0; i<size; i++) {
			if (e.equals(p.data))
				return i;
			p = p.next;
		}
		return -1;
	}
		
	public boolean contains(E e) {
		return indexOf(e) >= 0;
	}
		
	
//###############################################################
//###############################################################
//###############################################################
//###############################################################
	
	
	public LinkedList<E> sort(LinkedList<E> ogList){
		//If less than 2 elements, the list is sorted
		if (size() < 2) return ogList; 
		
		//Create a reference pointer p
		Node<E> p;
		p = head.next;
		
		//Move The First item to the sorted list
		ogList.head.next = null;
		ogList.tail = head;
		
		
		Boolean more = p.next != null;
		
		while(more) {
			
			//If this is the last element then exit after finishing the sort
			if(p.next == null) more = false;
			
			//Handle CompareTo if new element is smaller than current head
			if (p.data.compareTo(head.data) <= 0) { 
				Node<E> temp = p;
				p = p.next;
				temp.next = head;
				head = temp;
				
			//Handle CompareTo if new element is larger than current tail
			} else if(p.data.compareTo(tail.data) >= 0) {
				Node<E> temp = p;
				p = p.next;
				temp.next = null;
				ogList.tail.next = temp;
				ogList.tail = temp;
			
			//Else the new element will be sorted somewhere in the middle
			}else for(Node<E> temp = head; temp.next == null; temp = temp.next){
				if(p.next == null) more = false;
				
				//Go down linked list until we find the correct position
				if(p.data.compareTo(temp.data) >= 0 && p.data.compareTo(temp.next.data) <= 0) {
					p.next = temp.next;
					temp.next = p;
				}	
			}	
		}
		
		return ogList;
		}
	
	
	public static void main(String[] args) {
			//Create a LinkedList to hold n Integers
			LinkedList<Integer> intList = new LinkedList<Integer>();
			Scanner scr = new Scanner(System.in);
		
			//Read from System.in a value for n
			int n = scr.nextInt();
			
			//Read n integers adding them to the LinkedList
			for(int i=0; i<n; i++) {
				intList.addLast(scr.nextInt());
			}
			
			//Use the sort method to sort the integers
			intList = intList.sort(intList);
			
			//Print the first and last values in the list
			System.out.println("\n" + "First: " + intList.first());
			System.out.println("Last: " + intList.last() + "\n");
			
			
			//Create a LinkedList that can hold Strings
			LinkedList<String> strList = new LinkedList<String>();
			
			//Read from System.in a value for m
			int m = scr.nextInt();
			
			//Read m Strings adding them to the LinkedList
			for(int i=0; i<m; i++) {
				strList.addLast(scr.next());
			}
			
			//Use the sort method to sort the Strings
			strList.sort(strList);
			
			//Print the first and last Strings in the list
			System.out.println("\n" + "First: " + strList.first());
			System.out.println("Last: " + strList.last());
	}
	
}

