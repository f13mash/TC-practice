package com.f13mash.srm153;

import java.math.BigInteger;

public class Collision {

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) {
		int[] assignments = {20,20};
		int ids = 1000;
		System.out.println("ans is : "+probability(assignments, ids));
	}
	
	public static double probability(int[] assignments, int ids) {
		
		double p1=1, p2=1;
		int r1=ids;
		for(int i=0;i<assignments.length;i++){
			int r2=ids;
			for(int a=assignments[i];a>0;a--){
				p1=p1*r1/ids;
				p2=p2*r1/r2;
				r1--;
				r2--;
			}
		}
		System.out.println("p1 : "+p1+"   p2 : "+p2);
		return p2-p1;		
	}
	
}
