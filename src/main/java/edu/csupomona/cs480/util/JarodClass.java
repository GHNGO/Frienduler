package edu.csupomona.cs480.util;

import java.lang.NumberFormatException;

public class JarodClass<E, F> {
	public long add(E a, F b) {
		try {
			long aLong = Long.parseLong(a.toString());
			long bLong = Long.parseLong(b.toString());
			return aLong + bLong;
		}
		catch(NumberFormatException e){
			System.out.println(e.toString());
			return Long.MIN_VALUE;
		}
	}
}
