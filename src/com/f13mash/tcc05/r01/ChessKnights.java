package com.f13mash.tcc05.r01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ChessKnights {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x=1;
		int y=1;
		int n=2;
		System.out.println("ans is : "+probability(x, y, n));
	}
	
	
	public static double probability(int x, int y, int n) {
		double[][] map=new double[9][9];
		for(int i=1;i<9;i++){
			for(int j=1;j<9;j++){
				int di,dj;
				double a=1/(double)8;
				di=1;dj=2;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=2;dj=1;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=2;dj=-1;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=1;dj=-2;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=-1;dj=-2;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=-2;dj=-1;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=-2;dj=1;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
				di=-1;dj=2;
				if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
					map[i][j]+=a;
				}
				
			}
			
		}
		double sum=0;
		
		double[][] vis=new double[9][9];
		vis[x][y]=1;
		for(int c=0;c<n;c++){
			double[][] nvis=new double[9][9];
			for(int i=1;i<9;i++){
				for(int j=1;j<9;j++){
					
					if(vis[i][j]==0)
						continue;
					
					
					sum+=vis[i][j]*(1-map[i][j]);
					
					
					int di,dj;
					double a=vis[i][j]/(double)8;
					di=1;dj=2;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=2;dj=1;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=2;dj=-1;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=1;dj=-2;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=-1;dj=-2;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=-2;dj=-1;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=-2;dj=1;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
					di=-1;dj=2;
					if(!((i+di)>8 || (i+di)<=0 || (j+dj)>8 ||(j+dj)<=0)){
						nvis[i+di][j+dj]+=a;
					}
					
				}
			}
			vis=nvis;
		}
		return 1-sum;
	}

}
