package com.f13mash.TCL02Semi2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Escape {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] harmful={"500 0 0 500"};
		String[] deadly={"0 0 0 0"};
		System.out.println("ans is : "+lowest(harmful, deadly));
	}
	
	public static int lowest(String[] harmful, String[] deadly){
		int MAX=501;
		
		int[][] map=new int[MAX][MAX];
		int[][] pathCost=new int[MAX][MAX];
		
		for(int i=0;i<MAX;i++)
			Arrays.fill(pathCost[i], -1);
		pathCost[0][0]=0;
		
		for(int i=0;i<harmful.length;i++){
			String[] splt=harmful[i].trim().split(" ");
			int x1=Integer.parseInt(splt[0]);
			int y1=Integer.parseInt(splt[1]);
			int x2=Integer.parseInt(splt[2]);
			int y2=Integer.parseInt(splt[3]);
			
			if(x1>x2){
				int temp=x1;
				x1=x2;
				x2=temp;
			}
			if(y1>y2){
				int temp=y1;
				y1=y2;
				y2=temp;
			}
			
			for(int x=x1;x<=x2;x++){
				for(int y=y1;y<=y2;y++){
					map[x][y]=1;
				}
			}
		}
		
		for(int i=0;i<deadly.length;i++){
			String[] splt=deadly[i].trim().split(" ");
			int x1=Integer.parseInt(splt[0]);
			int y1=Integer.parseInt(splt[1]);
			int x2=Integer.parseInt(splt[2]);
			int y2=Integer.parseInt(splt[3]);
			
			if(x1>x2){
				int temp=x1;
				x1=x2;
				x2=temp;
			}
			if(y1>y2){
				int temp=y1;
				y1=y2;
				y2=temp;
			}
			
			
			for(int x=x1;x<=x2;x++){
				for(int y=y1;y<=y2;y++){
					map[x][y]=2;
				}
			}
		}
		
		List<Integer> xL=new LinkedList<Integer>();
		List<Integer> yL=new LinkedList<Integer>();
		
		xL.add(0);
		yL.add(0);
		
		while(xL.size()>0){
			int x=xL.remove(0);
			int y=yL.remove(0);
			
			int dx, dy;
			dx=1;dy=0;
			if( (x+dx)<MAX && (x+dx)>=0 && (y+dy)<MAX && (y+dy)>=0 && map[x+dx][y+dy]!=2){
				
				int newCost=pathCost[x][y]+map[x+dx][y+dy];
				if(pathCost[x+dx][y+dy]==-1 || newCost<pathCost[x+dx][y+dy]){
					pathCost[x+dx][y+dy]=newCost;
					xL.add(x+dx);
					yL.add(y+dy);
				}
			}
			dx=0;dy=1;
			if( (x+dx)<MAX && (x+dx)>=0 && (y+dy)<MAX && (y+dy)>=0 && map[x+dx][y+dy]!=2){
				
				int newCost=pathCost[x][y]+map[x+dx][y+dy];
				if(pathCost[x+dx][y+dy]==-1 || newCost<pathCost[x+dx][y+dy]){
					pathCost[x+dx][y+dy]=newCost;
					xL.add(x+dx);
					yL.add(y+dy);
				}
			}
			dx=-1;dy=0;
			if( (x+dx)<MAX && (x+dx)>=0 && (y+dy)<MAX && (y+dy)>=0 && map[x+dx][y+dy]!=2){
				
				int newCost=pathCost[x][y]+map[x+dx][y+dy];
				if(pathCost[x+dx][y+dy]==-1 || newCost<pathCost[x+dx][y+dy]){
					pathCost[x+dx][y+dy]=newCost;
					xL.add(x+dx);
					yL.add(y+dy);
				}
			}
			dx=0;dy=-1;
			if( (x+dx)<MAX && (x+dx)>=0 && (y+dy)<MAX && (y+dy)>=0 && map[x+dx][y+dy]!=2){
				
				int newCost=pathCost[x][y]+map[x+dx][y+dy];
				if(pathCost[x+dx][y+dy]==-1 || newCost<pathCost[x+dx][y+dy]){
					pathCost[x+dx][y+dy]=newCost;
					xL.add(x+dx);
					yL.add(y+dy);
				}
			}
		}
		
		return pathCost[MAX-1][MAX-1];
	}
	

}
