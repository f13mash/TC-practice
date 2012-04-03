package com.f13mash.tc.srm211;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class grafixMask {

	/**
	 * @param args
	 */
	
	static int w=400, h=600;
	static boolean[][] fill=new boolean[w][h];
	
	public static void main(String[] args) {
		
		String[] rectangles={"0 292 399 307"};
		
		int[] ans=sortedAreas(rectangles);
		System.out.println(Arrays.toString(ans));
		
	}
	
	public static int[] sortedAreas(String[] rectangles){
		
		
		for(String str : rectangles){
			String[] elems=str.trim().split(" ");
			int x1=Integer.parseInt(elems[0]);
			int y1=Integer.parseInt(elems[1]);
			int x2=Integer.parseInt(elems[2]);
			int y2=Integer.parseInt(elems[3]);
			
			for(int i=x1;i<=x2;i++){
				for(int j=y1;j<=y2;j++){
					fill[i][j]=true;
				}
			}
			
		}
		
		List<Integer> retList=new ArrayList<Integer>();
		
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				if(fill[i][j])
					continue;
				retList.add(doFill(i, j));
			}
		}
		
		Collections.sort(retList);
		int[] ret=new int[retList.size()];
		for(int i=0;i<ret.length;i++){
			ret[i]=retList.get(i);
		}
		
		return ret;
		
	}
	
	private static int doFill(int i, int j){
		if(fill[i][j])
			return 0;
		

		int sum=0;
		
		
		Stack<Integer> xs=new Stack<Integer>(), ys=new Stack<Integer>();
		
		fill[i][j]=true;
		xs.push(i);
		ys.push(j);
		
		while(xs.size()>0){
			sum++;
			
			if((i+1)<w && !fill[i+1][j] ){
				fill[i+1][j]=true;
				xs.push(i+1);
				ys.push(j);
			}
				
			if((i-1)>=0 && !fill[i-1][j] ){
				fill[i-1][j]=true;
				xs.push(i-1);
				ys.push(j);
			}
			
			if((j+1)<h && !fill[i][j+1]){
				fill[i][j+1]=true;
				xs.push(i);
				ys.push(j+1);
			}
			
			if((j-1)>=0 && !fill[i][j-1]){
				fill[i][j-1]=true;
				xs.push(i);
				ys.push(j-1);
			}
			i=xs.pop();
			j=ys.pop();
			
		}
		
		
		return sum;
	}
	

}
