package com.f13mash.tcc05.q05;

import java.util.Arrays;
import java.util.Arrays;

public class NestedRandomness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N=10;
		int nestings=4;
		int target=0;
		System.out.println("ans is : "+probability(N, nestings, target));
	}
	
	public static double probability(int N, int nestings, int target) {
		double[] prob=new double[N];
		Arrays.fill(prob, (double)1/N);
		for(int i=1;i<nestings;i++){
			System.out.println(i+" : "+Arrays.toString(prob));
			double[] cp=new double[N];
			for(int j=1;j<N;j++){
				double a = prob[j]/(double)(j);
				for(int k=0;k<j;k++){
					cp[k]+=a;
				}
			}
			prob=cp;
		}
		System.out.println(nestings+" : "+Arrays.toString(prob));
		return prob[target];
	}
}