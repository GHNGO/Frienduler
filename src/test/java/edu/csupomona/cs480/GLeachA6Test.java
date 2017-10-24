package edu.csupomona.cs480;

import edu.csupomona.cs480.util.GLeachA6;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GLeachA6Test {

  @Test
  public void concatTest() {
    GLeachA6 a6 = new GLeachA6();
    String result = a6.concat("hello", "world");
    assertEquals("helloworld", result);
  }

  @Test
  public void concatTest2() {
    GLeachA6 a6 = new GLeachA6();
    String a = "I procrastinated ";
    String b = "and this is the result.";
    String result = a6.concat(a,b);
    assertEquals("I procrastinated and this is the result.", result);
  }

}
