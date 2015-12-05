package main;

import algorithm.LongNumber;
import algorithm.RandomMethods;

public class Main {
	public static void main (String[] args) {
		//Integration.integrationTest();
		//RandomMethods.gaussianRandomTest();
		//Interpolator.interpolationTest();
		//LongNumber ln = new LongNumber(123456789101112L);
		LongNumber ln1 = new LongNumber(new long[]{9999}, -1).refresh();
		LongNumber ln2 = new LongNumber(new long[]{9997}, 1).refresh();
		/*
		for (int i=0; i<ln.getDigit(); i++) {
			System.out.println(ln.value[i]);
		}
		*/
		System.out.println(ln1);
		System.out.println(ln2);
		System.out.println(ln1.plus(ln2));
		System.out.println(ln2.minus(ln1));
		System.out.println(ln2.multiple(ln1));
		System.out.println(ln2.multiple(-3));
		LongNumber ln = new LongNumber(1);
		for (int i=1; i<=100; i++) {
			ln = ln.multiple(new LongNumber(i));
		}
		System.out.println(ln);
	}
}
