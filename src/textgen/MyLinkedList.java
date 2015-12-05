package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new  LLNode<E>();
		tail = new  LLNode<E>();
		LLNode<E> sentinel1 = new  LLNode<E>();
		LLNode<E> sentinel2 = new  LLNode<E>();
		head = sentinel1;
//		sentinel1.prev = head;
		sentinel1.next = sentinel2;
		sentinel2.prev = sentinel1;
//		sentinel2.next = tail;
		tail = sentinel2;
	
		
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null) throw new NullPointerException();
		
		LLNode<E> node = new LLNode(element);
		node.next = tail;
		node.prev = node.next.prev;
		node.next.prev = node;
		node.prev.next = node;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> node = head;
		if (index > size -1 || index < 0 || (size == 0 && index == 0 )) throw new IndexOutOfBoundsException();
    //    if (size == 0) return null;
		for (int i = 0 ; i<= index ; i++){
		  node = node.next;	
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) throw new NullPointerException() ;
		if ((size > 0 && index > size - 1) || index < 0) throw new IndexOutOfBoundsException();
		 
		LLNode<E> node = new LLNode(element);
		LLNode<E> previous = new LLNode();
		previous = head;
		for (int i = 0 ; i< index ; i++){
			previous = previous.next;
		}
		node.next = previous.next;
		node.prev = node.next.prev;
		node.next.prev = node;
		node.prev.next = node;
		size++;
		return ;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index > size -1 || index < 0 || (size == 0 && index == 0 )) throw new IndexOutOfBoundsException();
		 
	
		LLNode<E> nodeToRemove = new LLNode();
		nodeToRemove = head;
		for (int i = 0 ; i<= index ; i++){
			nodeToRemove = nodeToRemove.next;
		}
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		size--;
		
		return nodeToRemove.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) throw new NullPointerException();
		if (index > size -1 || index < 0 || (size == 0 && index == 0 )) throw new IndexOutOfBoundsException();
		 
		LLNode<E> node = new LLNode();
		
		LLNode<E> newNode = new LLNode(element);
		node = head;
		for (int i = 0 ; i <= index ; i++){
			node = node.next;
		}
		E oldData = node.data;
		newNode.next = node.next;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.next.prev = newNode;
//		node.next = null;
//		node.prev  = null;
		
		return oldData;	
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode () {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
