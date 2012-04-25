package com.f13mash.srm233;

import java.util.Arrays;

public class PipeCuts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] weldLocations={25, 50, 75};
		int L=24;
		System.out.println("ans is : "+probability(weldLocations, L));
	}
	
	public static double probability(int[] weldLocations, int L) {
		double best=0;
		int fav=0, out=0;
		Arrays.sort(weldLocations);
		for (int i = 0; i < (weldLocations.length-1); i++) {
			for(int j=i+1; j<weldLocations.length;j++){
				out++;
				if(weldLocations[i]>L || (weldLocations[j]-weldLocations[i])>L || (100-weldLocations[j])>L)
					fav++;
			}
		}
		if(out>0)
			best=(double)fav/out;
		return best;
	}

}
