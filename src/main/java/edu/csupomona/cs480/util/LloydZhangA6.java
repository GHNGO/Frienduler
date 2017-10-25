package edu.csupomona.cs480.util;

public class LloydZhangA6 {
	private String sample;
	
	public LloydZhangA6(String sample){
		this.sample = sample;
	}
	
	public int getHash() {
		return sample.hashCode();
	}
	
	public String getString()
	{
		return sample;
	}
	
	public char[] getArray() {
		return sample.toCharArray();
	}
}
