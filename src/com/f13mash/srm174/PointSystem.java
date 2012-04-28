package com.f13mash.srm174;

import java.util.Arrays;

public class PointSystem {

	/**
	 * @param args
	 */
	
	static int max=2001;
	
	public static void main(String[] args) {
		int pointsToWin=2, pointsToWinBy=1, skill=40;
		System.out.println("ans is : "+oddsOfWinning(pointsToWin, pointsToWinBy, skill));
	}
	
	public static double oddsOfWinning(int pointsToWin, int pointsToWinBy, int skill) {
		double[][] dp=new double[max][max];
		dp[0][0]=1;
		
		double s1=(double)skill/100;
		double s2=1-s1;
		
		double ans=0;
		
		for(int i=0;i<max;i++){
			for(int j=0;j<max;j++){
				if(i>=pointsToWin && (i-j)>=pointsToWinBy){
					ans+=dp[i][j];
				}
				if((i+1)<max && !(i>=pointsToWin && (i-j)>=pointsToWinBy) && !(j>=pointsToWin && (j-i)>=pointsToWinBy)){
					dp[i+1][j]+=(dp[i][j]*s1);
				}
				if((j+1)<max && !(j>=pointsToWin && (j-i)>=pointsToWinBy) && !(i>=pointsToWin && (i-j)>=pointsToWinBy) && !(j>=pointsToWin && (j-i)>=pointsToWinBy)){
					dp[i][j+1]+=(dp[i][j]*s2);
				}
			}
		}
		
		return ans;
	}

}
