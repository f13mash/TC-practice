package com.f13mash.srm150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StripePainter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String stripes = "BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD";
		//String stripes = "RGBGR";
		System.out.println("ans is : "+minStrokes(stripes));
	}
	
	public static int minStrokes(String stripes) {
		char[] arr = stripes.toCharArray();
		char[] arc = arr.clone();
		int len = stripes.length();
		
		Arrays.sort(arc);
		
		char[] clrs = new char[arr.length];
		clrs[0] = arc[0];
		int csz = 0;
		for(int i = 1; i < arc.length; i++){
			if(arc[i-1] != arc[i]){
				csz++;
				clrs[csz] = arc[i];
			}
		}
		csz++;
		
		//System.out.println(Arrays.toString(clrs));
		
		int[][] dp=new int[len][len];
		for(int i=0;i<len;i++){
			Arrays.fill(dp[i], -1);
			dp[i][0]=0;
		}
		
		int ans=recurs(arr, dp, 0, len-1, len);
		return ans;
	}
	
	public static int recurs(char[] arr, int[][] dp, int s, int e, int len){
		if(s==e){
			dp[s][e]=1;
			return 1;
		}
		
		if(dp[s][e]!=-1)
			return dp[s][e];
		
		int best=Integer.MAX_VALUE;
		for(int j=s; j<e;j++){
			int val = recurs(arr, dp, s, j, len) + recurs(arr, dp, j+1, e, len);
			if(arr[s]==arr[j+1])
				val-=1;
			best=Math.min(best, val);
		}
		dp[s][e]=best;
		return dp[s][e];
	}
	

}
