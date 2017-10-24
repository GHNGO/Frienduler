package edu.csupomona.cs480;

import edu.csupomona.cs480.util.BaskinTestFunc;
import edu.csupomona.cs480.util.TestFunc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Connor Baskin A6 JUnit Test Functions
public class BaskinClassTest {

	@Test
	public void testLog2() {
		BaskinTestFunc try1 = new BaskinTestFunc();
		double log2Of1024 = try1.log2(1024);
		double delta = 0;
		assertEquals(10, log2Of1024, delta);
	}

	@Test
	public void testAddToArrayList() {
		BaskinTestFunc try1 = new BaskinTestFunc();
		double objectToAdd = 123.123;
		try1.getDoubleArrayList().add(objectToAdd);
		assertTrue(try1.getDoubleArrayList().contains(objectToAdd));
	}

}
