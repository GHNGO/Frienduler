package edu.csupomona.cs480;

import static org.junit.Assert.*;
import java.util.Collection;
import org.junit.Test;
import edu.csupomona.cs480.util.TestFunc;
//Jack Test func A6
public class JacksClassTest {

	@Test
	public void testFunc() {
		
		   TestFunc test= new TestFunc();
		 	int ad = test.add(5, 5);
		 	assertEquals(10,ad);
	}
	@Test
	public void testFunc1() {
		
		   TestFunc test= new TestFunc();
		 	int ad = test.add(3, 5);
		 	assertEquals(8,ad);
	}
	@Test
	public void testFunc2() {
		
		   TestFunc test= new TestFunc();
		 	int ad = test.add(3, 5);
		 	assertEquals(8,ad);
	}

}
