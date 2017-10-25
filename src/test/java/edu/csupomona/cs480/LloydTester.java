package edu.csupomona.cs480;

import edu.csupomona.cs480.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class LloydTester {
	@Test
	public void testStringReceived() {
		LloydZhangA6 test1 = new LloydZhangA6( "Hello" );
		String response = test1.getString();
		assertEquals( "Hello", response );
	}
	
	@Test
	public void testHash() {
		LloydZhangA6 test2 = new LloydZhangA6( "Hello" );
		int response = test2.getHash();
		assertEquals( test2.hashCode() , response );
	}
	
	@Test
	public void testArray() {
		LloydZhangA6 test3 = new LloydZhangA6( "Hello" );
		char[] response = test3.getArray();
		char[] proper = { 'H', 'e', 'l', 'l', 'o' };
		assertArrayEquals( proper , response );
	}
}
