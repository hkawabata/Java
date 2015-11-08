package main;

import algorithm.Horner;

public class Main {
	public static void main (String[] args) {
		double a[] = {1,2,3};
		System.out.println(Horner.polynomialHorner(a, 10));
	}
}
