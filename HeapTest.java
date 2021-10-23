// Jonathan Yin
// A16283428
// joyin@ucsd.edu

/*
 * This program uses various test cases to check that the implementation of Heap is correct
 * and working as expected. It creates tests using min and max heaps and tests the methods
 * of Heap.java. 
 */
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class HeapTest {
	
	@Test
	public void testHeapSanity() {
		Heap<Integer, Integer> h = new Heap(new IntComparator());
		
		assertEquals(true, h.isEmpty());
		
		assertEquals(null, h.toArray());
		
	}
	
	@Test
	public void testHeapAdd() {
		Heap<Integer, Integer> h = new Heap(new IntComparator());
		// IntComparator ic = new IntComparator();
		// Heap<Integer, Integer> h2 = new Heap(ic.reversed());
		
		Heap<Integer, Integer> h2 = new Heap<Integer, Integer>(Integer::compare); 
		// Min heap testing
		h.add(4, 4);
		
		assertEquals(false, h.isEmpty());
		
		assertEquals((Integer) 4, h.peek().key);
		
		assertEquals((Integer) 4, h.poll().key);
		
		assertEquals(true, h.isEmpty());
		assertEquals(0, h.size());
		
		h.add(10, 10);
		h.add(5, 5);
		h.add(4, 4);
		
		assertEquals(3, h.size());
		
		System.out.println(h.toString());
		
		h.add(7, 7);
		h.add(3, 3);
		
		assertEquals(5, h.size());
		
		System.out.println(h.toString());
		
		h.poll();
		assertEquals(4, h.size());
		assertEquals((Integer) 4, h.peek().key);
		
		System.out.println(h.toString());
		
		h.poll();
		assertEquals(3, h.size());
		assertEquals((Integer) 5, h.peek().key);
		
		System.out.println(h.toString());
		
		h.poll();
		assertEquals(2, h.size());
		assertEquals((Integer) 7, h.peek().key);
		
		System.out.println(h.toString());
		
		h.add(5, 5);
		h.add(4, 4);
		
		assertEquals(4, h.size());
		assertEquals((Integer) 4, h.peek().key);
		
		System.out.println(h.toString());
		
		// Max heap testing
		h2.add(10, 10);
		h2.add(5, 5);
		h2.add(4, 4);
		
		assertEquals(3, h2.size());
		
		System.out.println(h2.toString());
		
		h2.add(6, 6);
		h2.add(8, 8);
		
		assertEquals(5, h2.size());
		
		System.out.println(h2.toString());
		
		h2.add(6, 6);
		h2.add(5, 5);
		
		assertEquals(7, h2.size());
		
		System.out.println(h2.toString());
		
		h2.poll();
		
		assertEquals((Integer) 8, h2.peek().key);
		
		System.out.println(h2.toString());
	}
	
	@Test
	public void testHeapFunctionality_toArray() {
		String[] s = {"Eric", "Nandini", "Rebecca", "Greg", "Juan"};
		int[] nums = {0, 1, 2, 3, 4};
		
		Heap<Integer, String> h = new Heap(new IntComparator());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals(5, h.toArray().size());
		System.out.println(h.toArray());
	}
	
	@Test
	public void testHeapFunctionality_addMin() {
		String[] s = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] nums = {20, 10, 30, 5, 1, 22};
		
		Heap<Integer, String> h = new Heap(new IntComparator());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals(6, h.toArray().size());
		System.out.println(h.toArray());
	}
	
	@Test
	public void testHeapFunctionality_addMax() {
		String[] s = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] nums = {20, 10, 30, 5, 1, 22};
		
		IntComparator ic = new IntComparator();
		Heap<Integer, String> h = new Heap(ic.reversed());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals(6, h.toArray().size());
		System.out.println(h.toArray());
	}
	
	@Test
	public void testHeapFunctionality_peek() {
		String[] s = {"Eric", "Nandini", "Rebecca", "Greg", "Juan"};
		int[] nums = {0,1,2,3,4};
		
		Heap<Integer, String> h = new Heap(new IntComparator());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals("Eric", h.peek().value);
		assertEquals((Integer) 0, h.peek().key);
	}
	
	@Test
	public void testHeapFunctionality_removeMin() {
		String[] s = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] nums = {20, 10, 30, 5, 1, 22};
		
		Heap<Integer, String> h = new Heap(new IntComparator());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals("Greg", h.poll().value);
		assertEquals("Paul", h.poll().value);
		assertEquals("Eric", h.poll().value);
		assertEquals("Garo", h.poll().value);
		assertEquals("Rebecca", h.poll().value);
		assertEquals("Nandini", h.poll().value);
	}
	
	@Test
	public void testHeapFunctionality_removeMax() {
		String[] s = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] nums = {20, 10, 30, 5, 1, 22};
		
		IntComparator ic = new IntComparator();
		Heap<Integer, String> h = new Heap(ic.reversed());
		for (int i = 0; i < s.length; i++) {
			h.add(nums[i], s[i]);
		}
		
		assertEquals("Nandini", h.poll().value);
		assertEquals("Rebecca", h.poll().value);
		assertEquals("Garo", h.poll().value);
		assertEquals("Eric", h.poll().value);
		assertEquals("Paul", h.poll().value);
		assertEquals("Greg", h.poll().value);
	}
	
	
}
