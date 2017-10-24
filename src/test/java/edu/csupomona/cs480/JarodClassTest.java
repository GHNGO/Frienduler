package edu.csupomona.cs480;

import edu.csupomona.cs480.util.JarodClass;
import edu.csupomona.cs480.util.TestFunc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JarodClassTest {

	@Test
	public void testDifferentTypeFunc() {
		
		   JarodClass<Long, Integer> test= new JarodClass<Long, Integer>();
		 	long ad = test.add(new Long(5), new Integer(5));
		 	assertEquals(10,ad);
	}
	@Test
	public void testSameTypeFunc() {
		
		JarodClass<Integer, Integer> test= new JarodClass<Integer, Integer>();
	 	long ad = test.add(new Integer(7), new Integer(5));
	 	assertEquals(12,ad);
	}
	
	@Test
	public void testANotNumberFunc() {
		
		JarodClass<String, Integer> test= new JarodClass<String, Integer>();
	 	long ad = test.add("A is not a number", new Integer(5));
	 	assertEquals(Long.MIN_VALUE,ad);
	}
	
	@Test
	public void testBNotNumberFunc() {
		
		JarodClass<Integer, String> test= new JarodClass<Integer, String>();
	 	long ad = test.add(new Integer(5), "B is not a number");
	 	assertEquals(Long.MIN_VALUE,ad);
	}
	
	@Test
	public void testABNotNumberFunc() {
		
		JarodClass<String, String> test= new JarodClass<String, String>();
	 	long ad = test.add("A is not a number", "B is not a number");
	 	assertEquals(Long.MIN_VALUE,ad);
	}
}
