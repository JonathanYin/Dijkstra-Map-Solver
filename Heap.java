// Jonathan Yin
// A16283428
// joyin@ucsd.edu

/* This program is used to create Heaps through an implementation of PriorityQueue. It allows 
 * the user to min and max heaps for storing key/value entries, as well as an Entry class.
*/
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K, V> implements PriorityQueue<K, V> {

	/*
	 * This class contains methods for adding, removing, and retrieving entries from Heap objects.
	 * It has instance variables entries and comparator to store its data in a list, as well as
	 * compare keys based on their priority.
	 */
	public List<Entry<K, V>> entries;
	public Comparator<K> comparator;

	/*
	 * Heap constructor initializes instance variables
	 * @param comparator The comparator used for comparing key priority
	 */
	public Heap(Comparator<K> comparator) {
		// initializes instance variables
		this.comparator = comparator;
		entries = new ArrayList<Entry<K, V>>();

		// add null starting entry
		entries.add(new Entry(null, null));
	}

	/**
	 * Adds a key/value pair to the heap as a new Entry, calling bubbleUp to 
	 * maintain heap properties
	 * @param k The Entry's key
	 * @param v The Entry's value
	 * @return void
	 */
	public void add(K k, V v) {
		entries.add(new Entry<K, V>(k, v));
		// call bubbleUp to assure heap properties are not violated
		bubbleUp(entries.size() - 1);
	}

	/**
	 * Returns and removes the root element in the heap. Calls bubbleDown to 
	 * maintain heap properties.
	 * @return the Entry element that was removed
	 */
	public Entry<K, V> poll() {
		if (entries.size() <= 1) {
			throw new NoSuchElementException();
		}
		// set output to the root entry to be removed
		Entry<K, V> output = entries.get(1);

		entries.set(1, entries.get(entries.size() - 1));
		entries.remove(entries.size() - 1);

		// call bubbleDown to fix the heap after removal
		bubbleDown(1);
		return output;
	}

	/**
	 * Returns the root element in the heap
	 * @return the Entry element at the root
	 */
	public Entry<K, V> peek() {
		if (entries.size() <= 1) {
			throw new NoSuchElementException();
		}
		return entries.get(1);
	}

	/**
	 * Returns all of the entries as a list
	 * @return the List of entries 
	 */
	public List<Entry<K, V>> toArray() {
		if (entries.size() <= 1) {
			return null;
		}
		List<Entry<K, V>> output = new ArrayList<Entry<K, V>>();
		for (int i = 1; i < entries.size(); i++) {
			output.add(entries.get(i));
		}
		return output;
	}

	/**
	 * Returns a boolean for whether or not the heap is empty
	 * @return the boolean for whether or not the heap is empty
	 */
	public boolean isEmpty() {
		return (entries.size() <= 1);
	}
	
	/**
	 * Returns the size of the heap
	 * @return the int representing the number of elements within the heap
	 */
	public int size() {
		if (entries.size() <= 1) {
			return 0;
		}
		return (entries.size() - 1);
	}

	/**
	 * Recursively move the entry at specified index to a smaller index (up the tree)
	 * while maintaining heap structure.
	 * @param index The index of the entry
	 * @return void
	 */
	public void bubbleUp(int index) {
		/* while the current index is within bounds, and
		 * if the entry to bubble up has higher priority than its parent, swap them
		 */
		if (index > 1 && 
				comparator.compare((K) entries.get(index).key, (K) entries.get(parent(index)).key) > 0) {
			// swap entries with parent
			swap(index, parent(index));

			// call bubbleUp on the new index
			bubbleUp(parent(index));
		} else {
			return;
		}
	}

	/**
	 * Recursively move the entry at specified index to a larger index (down the tree)
	 * while maintaining heap structure.
	 * @param index The index of the entry
	 * @return void
	 */
	public void bubbleDown(int index) {
		// if index is within bounds
		
		if (index < entries.size() - 1) {

			if (right(index) > this.size() && left(index) <= this.size()) {
				/*
				 *  If the right child is out of bounds, but the left is not, 
				 *  only check with the left.
				 *  Since the right child does not exist, there is no need to check with it
				 */
				int leftPriority = comparator.compare((K) entries.get(index).key, 
						(K) entries.get(left(index)).key);
				if (leftPriority < 0) {
					swap(index, left(index));
					bubbleDown(left(index));
				}
				
			}
			
			else if (right(index) <= this.size()){
				/*
				 *  if the right child exists, then so must the left child,
				 *  and thus, we need to compare their priority levels
				 */
				int leftPriority = comparator.compare((K) entries.get(index).key, 
						(K) entries.get(left(index)).key);

				int rightPriority = comparator.compare((K) entries.get(index).key, 
						(K) entries.get(right(index)).key);

				if (leftPriority < 0 && leftPriority <= rightPriority) {
					// if left child has more or equal priority to right child, swap with left
					swap(index, left(index));
					bubbleDown(left(index));
				}
				else if (rightPriority < 0 && rightPriority < leftPriority) {
					// if right child has more priority than left child, swap with right
					swap(index, right(index));
					bubbleDown(right(index));
				}
				else {
					// if neither child needs to be swapped, return
					return;
				}
			}
			
			else {
				// if neither child exists, return
				return;
			}
			
		}
	}

	/**
	 * Helper method to swap elements at two indices
	 * @param i1 The index of the first entry
	 * @param i2 The index of the second entry
	 * @return void
	 */
	public void swap(int i1, int i2) {
		Entry<K, V> temp = entries.get(i1);

		entries.set(i1, entries.get(i2));
		entries.set(i2, temp);
	}

	/**
	 * Return the parent of the entry at the input index
	 * @param index The index of the entry
	 * @return the int index of the parent
	 */
	public int parent(int index) {
		return index / 2;
	}

	/**
	 * Return the left child of the entry at the input index
	 * @param index The index of the entry
	 * @return the int index of the left child
	 */
	public int left(int index) {
		return 2 * index;
	}

	/**
	 * Return the right child of the entry at the input index
	 * @param index The index of the entry
	 * @return the int index of the right child
	 */
	public int right(int index) {
		return 2 * index + 1;
	}

	/**
	 * Return the entries in the heap as a string
	 * @return the string representing the heap's elements
	 */
	public String toString() {
		String s = "";
		for (int i = 1; i < entries.size(); i++) {
			s = s + " " + entries.get(i) + " ";
		}
		return s;
	}

}

class Entry<K, V> {
	/*
	 * This Entry class is used as input elements for Heap. It has instance variables 
	 * key and value to store given key/value pairs
	 */
	K key; // aka the _priority_
	V value;

	/* 
	 * Constructor initializes instance variables to their input values
	 */
	public Entry(K k, V v) {
		this.key = k;
		this.value = v;
	}

	/**
	 * Returns the key and value of the Entry as a string
	 * @return the string containing the key and value as an output
	 */
	public String toString() {
		return key + ": " + value;
	}
}
