package com.f13mash.tc.tcc2003;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Marketing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] compete={"1","2","3","0","0 5","1"};
		System.out.println("ans : "+howMany(compete));
	}
	
	public static long howMany(String[] compete){
		
		long ret=1;
		
		int size=compete.length;
		
		int[][] conn=new int[size+1][size+1];
		
		int[] asc=new int[size];
		
		for(int i=0;i<compete.length;i++){
			String[] splt=compete[i].trim().split(" ");
			for(int j=0;j<splt.length;j++){
				if(splt[j].length()==0)
					continue;
				int jy=Integer.parseInt(splt[j]);
				conn[i][++conn[i][0]]=jy;
				conn[jy][++conn[jy][0]]=i;
			}
		}
		
		for(int i=0;i<size;i++){
			if(asc[i]>0)
				continue;
			
			Stack<Integer> stck=new Stack<Integer>();
			stck.push(i);
			asc[i]=1;
			
			boolean noAns=false;
			
			while(stck.size()>0 && !noAns){
				int itm=stck.pop();
				for(int j=1;j<=conn[itm][0];j++){
					int itmj=conn[itm][j];
					if(asc[itmj]>0){
						if(asc[itm]==asc[itmj] && itm !=itmj){
							System.out.println(itm +" :::: "+itmj);
							noAns=true;
							break;
						}
							
					}
					else{
						stck.push(itmj);
						asc[itmj]=3+((-asc[itm])%3);
					}
				}
			}
			
			ret*=2;
			
			if(noAns){
				return -1;
			}
			
		}
		
		return ret;
	}

}
